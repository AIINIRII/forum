package xyz.aiinirii.forum.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.aiinirii.forum.mapper.BbsPostLikesMapper;
import xyz.aiinirii.forum.mapper.BbsReplyLikesMapper;
import xyz.aiinirii.forum.mapper.BbsReplyMultiLikesMapper;
import xyz.aiinirii.forum.model.*;
import xyz.aiinirii.forum.portal.service.BbsLikeService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AIINIRII
 */
@Service
public class BbsLikeServiceImpl implements BbsLikeService {
    private BbsPostLikesMapper bbsPostLikesMapper;
    private BbsReplyLikesMapper bbsReplyLikesMapper;
    private BbsReplyMultiLikesMapper bbsReplyMultiLikesMapper;

    @Autowired
    public void setBbsPostLikesMapper(BbsPostLikesMapper bbsPostLikesMapper) {
        this.bbsPostLikesMapper = bbsPostLikesMapper;
    }

    @Autowired
    public void setBbsReplyLikesMapper(BbsReplyLikesMapper bbsReplyLikesMapper) {
        this.bbsReplyLikesMapper = bbsReplyLikesMapper;
    }

    @Autowired
    public void setBbsReplyMultiLikesMapper(BbsReplyMultiLikesMapper bbsReplyMultiLikesMapper) {
        this.bbsReplyMultiLikesMapper = bbsReplyMultiLikesMapper;
    }

    @Override
    public int likePost(Long userId, Long postId) {
        BbsPostLikes bbsPostLikes = new BbsPostLikes();
        bbsPostLikes.setUserId(userId);
        bbsPostLikes.setPostId(postId);
        bbsPostLikes.setCreateTime(new Date());
        return bbsPostLikesMapper.insert(bbsPostLikes);
    }

    @Override
    public int likeReply(Long userId, Long replyId) {
        BbsReplyLikes bbsReplyLikes = new BbsReplyLikes();
        bbsReplyLikes.setUserId(userId);
        bbsReplyLikes.setReplyId(replyId);
        bbsReplyLikes.setCreateTime(new Date());
        return bbsReplyLikesMapper.insert(bbsReplyLikes);
    }

    @Override
    public int likeReplyMulti(Long userId, Long replyMultiId) {
        BbsReplyMultiLikes bbsReplyMultiLikes = new BbsReplyMultiLikes();
        bbsReplyMultiLikes.setUserId(userId);
        bbsReplyMultiLikes.setReplyMultiId(replyMultiId);
        bbsReplyMultiLikes.setCreateTime(new Date());
        return bbsReplyMultiLikesMapper.insert(bbsReplyMultiLikes);
    }

    @Override
    public int cancelLikePost(Long userId, Long postId) {
        return bbsPostLikesMapper.deleteByPrimaryKey(userId, postId);
    }

    @Override
    public int cancelLikeReply(Long userId, Long replyId) {
        return bbsReplyLikesMapper.deleteByPrimaryKey(userId, replyId);
    }

    @Override
    public int cancelLikeReplyMulti(Long userId, Long replyMultiId) {
        return bbsReplyMultiLikesMapper.deleteByPrimaryKey(userId, replyMultiId);
    }

    @Override
    public List<Long> getPostLikesById(Long postId) {
        BbsPostLikesExample bbsPostLikesExample = new BbsPostLikesExample();
        bbsPostLikesExample.createCriteria()
                .andPostIdEqualTo(postId);
        bbsPostLikesExample.setOrderByClause("create_time DESC");
        return bbsPostLikesMapper
                .selectByExample(bbsPostLikesExample)
                .stream()
                .map(BbsPostLikes::getUserId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getReplyLikesById(Long replyId) {
        BbsReplyLikesExample bbsReplyLikesExample = new BbsReplyLikesExample();
        bbsReplyLikesExample.createCriteria()
                .andReplyIdEqualTo(replyId);
        bbsReplyLikesExample.setOrderByClause("create_time DESC");
        return bbsReplyLikesMapper
                .selectByExample(bbsReplyLikesExample)
                .stream()
                .map(BbsReplyLikes::getUserId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getReplyMultiLikesById(Long replyMultiId) {
        BbsReplyMultiLikesExample bbsReplyMultiLikesExample = new BbsReplyMultiLikesExample();
        bbsReplyMultiLikesExample.createCriteria()
                .andReplyMultiIdEqualTo(replyMultiId);
        bbsReplyMultiLikesExample.setOrderByClause("create_time DESC");
        return bbsReplyMultiLikesMapper
                .selectByExample(bbsReplyMultiLikesExample)
                .stream()
                .map(BbsReplyMultiLikes::getUserId)
                .collect(Collectors.toList());
    }

    @Override
    public long countPostLikesById(Long postId) {
        BbsPostLikesExample bbsPostLikesExample = new BbsPostLikesExample();
        bbsPostLikesExample.createCriteria()
                .andPostIdEqualTo(postId);
        return bbsPostLikesMapper.countByExample(bbsPostLikesExample);
    }

    @Override
    public long countReplyLikesById(Long replyId) {
        BbsReplyLikesExample bbsReplyLikesExample = new BbsReplyLikesExample();
        bbsReplyLikesExample.createCriteria()
                .andReplyIdEqualTo(replyId);
        return bbsReplyLikesMapper.countByExample(bbsReplyLikesExample);
    }

    @Override
    public long countReplyMultiLikesById(Long replyMultiId) {
        BbsReplyMultiLikesExample bbsReplyMultiLikesExample = new BbsReplyMultiLikesExample();
        bbsReplyMultiLikesExample.createCriteria()
                .andReplyMultiIdEqualTo(replyMultiId);
        return bbsReplyMultiLikesMapper.countByExample(bbsReplyMultiLikesExample);
    }
}