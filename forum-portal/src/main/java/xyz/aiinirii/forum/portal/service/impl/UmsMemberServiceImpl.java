package xyz.aiinirii.forum.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.aiinirii.forum.common.exception.Asserts;
import xyz.aiinirii.forum.mapper.UmsMemberMapper;
import xyz.aiinirii.forum.model.UmsMember;
import xyz.aiinirii.forum.model.UmsMemberExample;
import xyz.aiinirii.forum.portal.domain.PortalUserDetails;
import xyz.aiinirii.forum.portal.domain.UmsMemberParam;
import xyz.aiinirii.forum.portal.domain.UpdateMemberPasswordParam;
import xyz.aiinirii.forum.portal.service.UmsMemberCacheService;
import xyz.aiinirii.forum.portal.service.UmsMemberService;
import xyz.aiinirii.forum.security.util.JwtTokenUtil;

import java.util.Date;
import java.util.List;

/**
 * @author AIINIRII
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);
    private PasswordEncoder passwordEncoder;
    private UmsMemberMapper memberMapper;
    private UmsMemberCacheService memberCacheService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setMemberCacheService(UmsMemberCacheService memberCacheService) {
        this.memberCacheService = memberCacheService;
    }

    @Autowired
    public void setMemberMapper(UmsMemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UmsMember getMemberByUsername(String username) {
        // 可加入缓存
        UmsMember member = memberCacheService.getMember(username);
        if (member != null) {
            return member;
        }
        UmsMemberExample umsMemberExample = new UmsMemberExample();
        umsMemberExample.createCriteria().andUsernameLike(username);
        List<UmsMember> umsMembers = memberMapper.selectByExample(umsMemberExample);
        if (umsMembers != null && umsMembers.size() > 0) {
            member = umsMembers.get(0);
            memberCacheService.setMember(member);
            return member;
        }
        return null;
    }

    @Override
    public UmsMember register(UmsMemberParam umsMemberParam) {
        // 查询是否已有此用户
        UmsMemberExample memberExample = new UmsMemberExample();
        memberExample.createCriteria().andUsernameLike(umsMemberParam.getUsername());
        List<UmsMember> umsMembers = memberMapper.selectByExample(memberExample);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            Asserts.fail("该用户已存在");
        }
        memberExample.clear();
        if (umsMemberParam.getPhone() != null) {
            memberExample.createCriteria().andPhoneEqualTo(umsMemberParam.getPhone());
            umsMembers = memberMapper.selectByExample(memberExample);
            if (!CollectionUtils.isEmpty(umsMembers)) {
                Asserts.fail("该手机号码已被注册");
            }
        }
        memberExample.clear();
        if (umsMemberParam.getEmail() != null) {
            memberExample.createCriteria().andEmailEqualTo(umsMemberParam.getEmail());
            umsMembers = memberMapper.selectByExample(memberExample);
            if (!CollectionUtils.isEmpty(umsMembers)) {
                Asserts.fail("该邮箱已被注册");
            }
        }
        // 如果没有该用户则进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(umsMemberParam.getUsername());
        umsMember.setPassword(passwordEncoder.encode(umsMemberParam.getPassword()));
        umsMember.setPhone(umsMemberParam.getPhone());
        umsMember.setEmail(umsMemberParam.getEmail());
        umsMember.setGender(umsMemberParam.getGender());
        umsMember.setNickname(umsMemberParam.getNickName());
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        // 获取默认会员等级并设置
        // 添加操作
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
        return umsMember;
    }

    @Override
    public String login(String username, String password) throws AuthenticationException {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常：{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsMember getItem(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(UmsMember member) {
        return memberMapper.updateByPrimaryKey(member);
    }

    @Override
    public int delete(Long id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updatePassword(UpdateMemberPasswordParam updateMemberPasswordParam) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(updateMemberPasswordParam.getUsername());
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(umsMembers)) {
            UmsMember member = umsMembers.get(0);
            if (passwordEncoder.matches(updateMemberPasswordParam.getOldPassword(), member.getPassword())) {
                // 如果密码正确则设置新密码并存入数据库
                member.setPassword(passwordEncoder.encode(updateMemberPasswordParam.getNewPassword()));
                memberMapper.updateByPrimaryKey(member);
                return 1;
            } else {
                Asserts.fail("密码错误");
            }
        } else {
            Asserts.fail("该账号不存在");
        }
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取用户信息
        UmsMember umsMember = getMemberByUsername(username);
        if (umsMember != null) {
            return new PortalUserDetails(umsMember);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}