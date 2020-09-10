package xyz.aiinirii.forum.portal.service;

import xyz.aiinirii.forum.model.BbsTopic;
import xyz.aiinirii.forum.portal.domain.BbsTopicParam;

import java.util.List;

/**
 * 论坛话题服务
 *
 * @author AIINIRII
 */
public interface BbsTopicService {

    /**
     * 创建话题
     *
     * @param bbsTopicParam 创建话题所用参数
     * @return 是否创建成功
     */
    int createTopic(BbsTopicParam bbsTopicParam);

    /**
     * 获取所有话题
     *
     * @return 所有话题组成的列表
     */
    List<BbsTopic> getTopics();
}