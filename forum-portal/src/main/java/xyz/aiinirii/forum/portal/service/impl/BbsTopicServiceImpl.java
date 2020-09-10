package xyz.aiinirii.forum.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.aiinirii.forum.mapper.BbsTopicMapper;
import xyz.aiinirii.forum.model.BbsTopic;
import xyz.aiinirii.forum.model.BbsTopicExample;
import xyz.aiinirii.forum.portal.domain.BbsTopicParam;
import xyz.aiinirii.forum.portal.service.BbsTopicService;

import java.util.List;

/**
 * @author AIINIRII
 */
@Service
public class BbsTopicServiceImpl implements BbsTopicService {

    BbsTopicMapper bbsTopicMapper;

    @Autowired
    public void setBbsTopicMapper(BbsTopicMapper bbsTopicMapper) {
        this.bbsTopicMapper = bbsTopicMapper;
    }

    @Override
    public int createTopic(BbsTopicParam bbsTopicParam) {
        BbsTopic bbsTopic = new BbsTopic();
        bbsTopic.setTitle(bbsTopicParam.getTitle());
        bbsTopic.setVisible(bbsTopicParam.getVisible());
        bbsTopic.setDescription(bbsTopicParam.getDescription());
        return bbsTopicMapper.insert(bbsTopic);
    }

    @Override
    public List<BbsTopic> getTopics() {
        return bbsTopicMapper.selectByExampleWithBLOBs(new BbsTopicExample());
    }
}