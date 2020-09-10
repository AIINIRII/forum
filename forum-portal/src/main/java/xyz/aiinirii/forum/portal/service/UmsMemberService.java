package xyz.aiinirii.forum.portal.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.forum.model.UmsMember;
import xyz.aiinirii.forum.portal.domain.UmsMemberParam;
import xyz.aiinirii.forum.portal.domain.UpdateMemberPasswordParam;

/**
 * user member类服务
 *
 * @author AIINIRII
 */
public interface UmsMemberService {

    /**
     * 根据用户名获取后台用户
     *
     * @param username 用户名
     * @return 用户
     */
    UmsMember getMemberByUsername(String username);

    /**
     * 用户注册
     *
     * @param umsMemberParam 用户注册参数
     * @return 注册成功用户
     */
    @Transactional
    UmsMember register(UmsMemberParam umsMemberParam);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT token
     */
    String login(String username, String password) throws org.springframework.security.core.AuthenticationException;

    /**
     * 刷新Token
     *
     * @param oldToken 旧的token
     * @return 新的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户
     */
    UmsMember getItem(Long id);

    /**
     * 根据用户id更改用户数据
     *
     * @param member 用户数据
     * @return 是否成功
     */
    int update(UmsMember member);

    /**
     * 删除指定用户
     *
     * @param id 删除用户id
     * @return 是否成功
     */
    int delete(Long id);

    /**
     * 修改用户密码
     *
     * @param updateMemberPasswordParam 修改用户密码时的参数
     * @return 是否成功
     */
    @Transactional
    int updatePassword(UpdateMemberPasswordParam updateMemberPasswordParam);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDetails loadUserByUsername(String username);

}