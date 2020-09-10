package xyz.aiinirii.forum.portal.service;

import org.springframework.transaction.annotation.Transactional;
import xyz.aiinirii.forum.model.BbsPost;
import xyz.aiinirii.forum.portal.domain.BbsPostParam;

import java.util.List;

/**
 * 论坛信息业务类
 *
 * @author AIINIRII
 */
public interface BbsPostService {
    /**
     * 根据用户名分页获取所有帖子
     *
     * @param username 用户名
     * @param pageSize 一页的大小
     * @param pageNum  页数
     * @return 所有该用户名所匹配的帖子的对应页
     */
    List<BbsPost> getPostsByUsername(String username, Integer pageSize, Integer pageNum);

    /**
     * 根据用户id获取所有帖子
     *
     * @param id       用户id
     * @param pageSize 一页的大小
     * @param pageNum  页数
     * @return 所有该用户名所匹配的帖子的对应页
     */
    List<BbsPost> getPostsByUserId(Long id, Integer pageSize, Integer pageNum);

    /**
     * 分页获取所有帖子
     *
     * @param pageSize 一页的大小
     * @param pageNum  页数
     * @return 所有帖子的对应页
     */
    List<BbsPost> getPosts(Integer pageSize, Integer pageNum);

    /**
     * 新建帖子
     *
     * @param id              用户id
     * @param createPostParam 新建帖子的封装类
     * @return 创建成功的帖子的信息
     */
    @Transactional
    int createPost(Long id, BbsPostParam createPostParam);

    /**
     * 根据post的序号获取post
     *
     * @param id 帖子的序号
     * @return 帖子
     */
    BbsPost getPostById(Long id);

    /**
     * 根据帖子的序号更新帖子
     *
     * @param id           帖子序号
     * @param userId       当前用户id（用作验证）
     * @param bbsPostParam 更新内容
     * @return 更新的条目数量
     */
    @Transactional
    int updatePost(Long id, Long userId, BbsPostParam bbsPostParam);

    /**
     * 根据帖子的序号删除帖子
     *
     * @param id     帖子序号
     * @param userId 当前用户id（用作验证）
     * @return 删除的条目数量
     */
    @Transactional
    int deletePost(Long id, Long userId);
}
