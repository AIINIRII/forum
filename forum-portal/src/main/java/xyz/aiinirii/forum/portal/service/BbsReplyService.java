package xyz.aiinirii.forum.portal.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.forum.model.BbsReply;
import xyz.aiinirii.forum.model.BbsReplyMulti;
import xyz.aiinirii.forum.portal.domain.BbsMultiReplyParam;
import xyz.aiinirii.forum.portal.domain.BbsReplyParam;

import java.util.List;

/**
 * 论坛回复的业务类
 *
 * @author AIINIRII
 */
public interface BbsReplyService {
    /**
     * 根据帖子id和页码信息获取回复
     *
     * @param id       帖子id
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 回复链表
     */
    List<BbsReply> getPostReplies(Long id, Integer pageSize, Integer pageNum);

    /**
     * 根据帖子id创建回复
     *
     * @param id            回复的帖子的id
     * @param uid           回复的用户id
     * @param bbsReplyParam 创建回复的封装对象
     * @return 加入的回复数量
     */
    @Transactional
    int createPostReply(Long id, Long uid, BbsReplyParam bbsReplyParam);

    /**
     * 根据回复id和页码信息获取回复
     *
     * @param id       回复id
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 回复链表
     */
    List<BbsReplyMulti> getReplyReplies(Long id, Integer pageSize, Integer pageNum);

    /**
     * 根据帖子id创建回复的回复
     *
     * @param id                 回复的回复的id
     * @param userId             回复的用户的id
     * @param bbsMultiReplyParam 创建回复的封装对象
     * @return 加入的回复数量
     */
    @Transactional
    int createPostMultiReply(Long id, Long userId, BbsMultiReplyParam bbsMultiReplyParam);

    /**
     * 更新回复内容
     *
     * @param id            回复id
     * @param userId        用户id（用作验证）
     * @param bbsReplyParam 回复参数
     * @return 更新的数量
     */
    @Transactional
    int updateReply(Long id, Long userId, BbsReplyParam bbsReplyParam);

    /**
     * 更新回复的回复内容
     *
     * @param id                 回复的回复id
     * @param userId             用户id（用作验证）
     * @param bbsMultiReplyParam 回复参数
     * @return 更新的数量
     */
    @Transactional
    int updateMultiReply(Long id, Long userId, BbsMultiReplyParam bbsMultiReplyParam);

    /**
     * 删除回复内容
     *
     * @param id     回复id
     * @param userId 用户id（用作验证）
     * @return 删除的数量
     */
    @Transactional
    int deleteReply(Long id, Long userId);

    /**
     * 删除回复的回复
     *
     * @param id     回复的回复的id
     * @param userId 用户id（用作验证）
     * @return 删除的数量
     */
    @Transactional
    int deleteMultiReply(Long id, Long userId);
}