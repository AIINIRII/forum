package xyz.aiinirii.forum.portal.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.aiinirii.forum.mapper.BbsPostMapper;
import xyz.aiinirii.forum.mapper.UmsMemberMapper;
import xyz.aiinirii.forum.model.BbsPost;
import xyz.aiinirii.forum.model.BbsPostExample;
import xyz.aiinirii.forum.model.UmsMember;
import xyz.aiinirii.forum.model.UmsMemberExample;
import xyz.aiinirii.forum.portal.domain.BbsPostParam;
import xyz.aiinirii.forum.portal.service.BbsPostService;

import java.util.Date;
import java.util.List;

/**
 * @author AIINIRII
 */
@Service
public class BbsPostServiceImpl implements BbsPostService {

    private UmsMemberMapper umsMemberMapper;
    private BbsPostMapper bbsPostMapper;

    @Autowired
    public void setUmsMemberMapper(UmsMemberMapper umsMemberMapper) {
        this.umsMemberMapper = umsMemberMapper;
    }

    @Autowired
    public void setBbsPostMapper(BbsPostMapper bbsPostMapper) {
        this.bbsPostMapper = bbsPostMapper;
    }

    @Override
    public List<BbsPost> getPostsByUsername(String username, Integer pageSize, Integer pageNum) {
        UmsMemberExample umsMemberExample = new UmsMemberExample();
        umsMemberExample.createCriteria()
                .andUsernameEqualTo(username);
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(umsMemberExample);
        if (CollectionUtils.isEmpty(umsMembers)) {
            throw new UsernameNotFoundException("没能找到对应当前登录的user对应的信息");
        } else {
            UmsMember umsMember = umsMembers.get(0);
            Long id = umsMember.getId();
            PageHelper.startPage(pageNum, pageSize);
            BbsPostExample bbsPostExample = new BbsPostExample();
            bbsPostExample.createCriteria()
                    .andUserIdEqualTo(id);
            return bbsPostMapper.selectByExampleWithBLOBs(bbsPostExample);
        }
    }

    @Override
    public List<BbsPost> getPostsByUserId(Long id, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        BbsPostExample bbsPostExample = new BbsPostExample();
        bbsPostExample.createCriteria()
                .andUserIdEqualTo(id);
        bbsPostExample.setOrderByClause("publish_time DESC");
        return bbsPostMapper.selectByExampleWithBLOBs(bbsPostExample);
    }

    @Override
    public List<BbsPost> getPosts(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        BbsPostExample bbsPostExample = new BbsPostExample();
        bbsPostExample.createCriteria()
                .andVisibleEqualTo(1);
        bbsPostExample.setOrderByClause("publish_time DESC");
        List<BbsPost> posts = bbsPostMapper.selectByExampleWithBLOBs(bbsPostExample);
        posts.forEach(bbsPost -> {
            if (bbsPost.getAnonymous() == 1) {
                bbsPost.setUserId(null);
            }
        });
        return posts;
    }

    @Override
    public int createPost(Long id, BbsPostParam createPostParam) {
        BbsPost bbsPost = new BbsPost();
        bbsPost.setTopicId(createPostParam.getTopicId());
        bbsPost.setUserId(id);
        bbsPost.setPublishTime(new Date());
        bbsPost.setModifyTime(new Date());
        bbsPost.setTitle(createPostParam.getTitle());
        bbsPost.setVisible(createPostParam.getVisible());
        bbsPost.setAnonymous(createPostParam.getAnonymous());
        bbsPost.setContent(createPostParam.getContent());
        return bbsPostMapper.insert(bbsPost);
    }

    @Override
    public BbsPost getPostById(Long id) {
        return bbsPostMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePost(Long id, Long userId, BbsPostParam bbsPostParam) {
        BbsPost bbsPost = bbsPostMapper.selectByPrimaryKey(id);

        if (bbsPost != null) {
            if (bbsPost.getUserId().equals(userId)) {
                bbsPost.setTopicId(bbsPostParam.getTopicId());
                bbsPost.setModifyTime(new Date());
                bbsPost.setTitle(bbsPostParam.getTitle());
                bbsPost.setVisible(bbsPostParam.getVisible());
                bbsPost.setAnonymous(bbsPostParam.getAnonymous());
                bbsPost.setContent(bbsPostParam.getContent());
                return bbsPostMapper.updateByPrimaryKeyWithBLOBs(bbsPost);
            } else {
                throw new AuthorizationServiceException("该用户没有更改此帖子的权限");
            }
        } else {
            return 0;
        }
    }

    @Override
    public int deletePost(Long id, Long userId) {
        BbsPost bbsPost = bbsPostMapper.selectByPrimaryKey(id);

        if (bbsPost != null) {
            if (bbsPost.getUserId().equals(userId)) {
                return bbsPostMapper.deleteByPrimaryKey(id);
            } else {
                throw new AuthorizationServiceException("该用户没有更改此帖子的权限");
            }
        } else {
            return 0;
        }
    }
}