package xyz.aiinirii.forum.portal.service;

import xyz.aiinirii.forum.model.BbsPostLikes;
import xyz.aiinirii.forum.model.BbsReplyLikes;
import xyz.aiinirii.forum.model.BbsReplyMultiLikes;

import java.util.List;

/**
 * 有关Like的业务类
 *
 * @author AIINIRII
 */
public interface BbsLikeService {

    /**
     * 喜欢帖子
     *
     * @param userId 用户序号
     * @param postId 帖子序号
     * @return 插入的数量
     */
    int likePost(Long userId, Long postId);

    /**
     * 喜欢回复
     *
     * @param userId  用户序号
     * @param replyId 回复序号
     * @return 插入的数量
     */
    int likeReply(Long userId, Long replyId);

    /**
     * 喜欢回复的回复
     *
     * @param userId       用户序号
     * @param replyMultiId 回复的回复序号
     * @return 插入的数量
     */
    int likeReplyMulti(Long userId, Long replyMultiId);

    /**
     * 取消喜欢帖子
     *
     * @param userId 用户序号
     * @param postId 帖子序号
     * @return 删除的数量
     */
    int cancelLikePost(Long userId, Long postId);

    /**
     * 取消喜欢回复
     *
     * @param userId  用户序号
     * @param replyId 回复序号
     * @return 删除的数量
     */
    int cancelLikeReply(Long userId, Long replyId);

    /**
     * 取消喜欢回复的回复
     *
     * @param userId       用户序号
     * @param replyMultiId 回复的回复序号
     * @return 删除的数量
     */
    int cancelLikeReplyMulti(Long userId, Long replyMultiId);

    /**
     * 获取喜欢帖子的关系列表
     *
     * @param postId 帖子序号
     * @return 返回的喜欢的人id
     */
    List<Long> getPostLikesById(Long postId);

    /**
     * 获取喜欢回复的关系列表
     *
     * @param replyId 回复序号
     * @return 返回的喜欢的人id
     */
    List<Long> getReplyLikesById(Long replyId);

    /**
     * 获取喜欢回复的回复的关系列表
     *
     * @param replyMultiId 回复的回复序号
     * @return 返回的喜欢的人id
     */
    List<Long> getReplyMultiLikesById(Long replyMultiId);

    /**
     * 计算喜欢帖子的人数
     *
     * @param postId 帖子序号
     * @return 喜欢数量
     */
    long countPostLikesById(Long postId);

    /**
     * 计算喜欢回复的人数
     *
     * @param replyId 回复序号
     * @return 喜欢数量
     */
    long countReplyLikesById(Long replyId);

    /**
     * 计算喜欢回复的回复的人数
     *
     * @param replyMultiId 回复的回复序号
     * @return 喜欢数量
     */
    long countReplyMultiLikesById(Long replyMultiId);
}