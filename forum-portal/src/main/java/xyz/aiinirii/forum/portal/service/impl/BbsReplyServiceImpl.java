package xyz.aiinirii.forum.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import xyz.aiinirii.forum.mapper.BbsReplyMapper;
import xyz.aiinirii.forum.mapper.BbsReplyMultiMapper;
import xyz.aiinirii.forum.model.BbsReply;
import xyz.aiinirii.forum.model.BbsReplyExample;
import xyz.aiinirii.forum.model.BbsReplyMulti;
import xyz.aiinirii.forum.model.BbsReplyMultiExample;
import xyz.aiinirii.forum.portal.domain.BbsMultiReplyParam;
import xyz.aiinirii.forum.portal.domain.BbsReplyParam;
import xyz.aiinirii.forum.portal.service.BbsReplyService;

import java.util.Date;
import java.util.List;

/**
 * @author AIINIRII
 */
@Service
public class BbsReplyServiceImpl implements BbsReplyService {

    BbsReplyMapper bbsReplyMapper;
    BbsReplyMultiMapper bbsReplyMultiMapper;

    @Autowired
    public void setBbsReplyMapper(BbsReplyMapper bbsReplyMapper) {
        this.bbsReplyMapper = bbsReplyMapper;
    }

    @Autowired
    public void setBbsReplyMultiMapper(BbsReplyMultiMapper bbsReplyMultiMapper) {
        this.bbsReplyMultiMapper = bbsReplyMultiMapper;
    }

    @Override
    public List<BbsReply> getPostReplies(Long id, Integer pageSize, Integer pageNum) {
        BbsReplyExample bbsReplyExample = new BbsReplyExample();
        bbsReplyExample.createCriteria()
                .andParentIdEqualTo(id)
                .andVisibleEqualTo(1);
        bbsReplyExample.setOrderByClause("publish_time DESC");
        List<BbsReply> bbsReplies = bbsReplyMapper.selectByExampleWithBLOBs(bbsReplyExample);
        bbsReplies.forEach(bbsReply -> {
            if (bbsReply.getAnonymous() == 1) {
                bbsReply.setUserId(null);
            }
        });
        return bbsReplies;
    }

    @Override
    public int createPostReply(Long id, Long uid, BbsReplyParam bbsReplyParam) {
        BbsReply bbsReply = new BbsReply();
        bbsReply.setParentId(id);
        bbsReply.setUserId(uid);
        bbsReply.setPublishTime(new Date());
        bbsReply.setModifyTime(new Date());
        bbsReply.setVisible(bbsReplyParam.getVisible());
        bbsReply.setAnonymous(bbsReplyParam.getAnonymous());
        bbsReply.setContent(bbsReplyParam.getContent());
        return bbsReplyMapper.insert(bbsReply);
    }

    @Override
    public List<BbsReplyMulti> getReplyReplies(Long id, Integer pageSize, Integer pageNum) {
        BbsReplyMultiExample bbsReplyMultiExample = new BbsReplyMultiExample();
        bbsReplyMultiExample.createCriteria()
                .andParentIdEqualTo(id)
                .andVisibleEqualTo(1);
        bbsReplyMultiExample.setOrderByClause("publish_time DESC");
        List<BbsReplyMulti> bbsReplyMultis = bbsReplyMultiMapper.selectByExampleWithBLOBs(bbsReplyMultiExample);
        bbsReplyMultis.forEach(bbsReply -> {
            if (bbsReply.getAnonymous() == 1) {
                bbsReply.setUserId(null);
            }
        });
        return bbsReplyMultis;
    }

    @Override
    public int createPostMultiReply(Long id, Long userId, BbsMultiReplyParam bbsMultiReplyParam) {
        BbsReplyMulti bbsReplyMulti = new BbsReplyMulti();
        bbsReplyMulti.setParentId(id);
        bbsReplyMulti.setUserId(userId);
        bbsReplyMulti.setParentReplyId(bbsMultiReplyParam.getParentReplyId());
        bbsReplyMulti.setPublishTime(new Date());
        bbsReplyMulti.setModifyTime(new Date());
        bbsReplyMulti.setVisible(bbsMultiReplyParam.getVisible());
        bbsReplyMulti.setAnonymous(bbsMultiReplyParam.getAnonymous());
        bbsReplyMulti.setContent(bbsMultiReplyParam.getContent());
        return bbsReplyMultiMapper.insert(bbsReplyMulti);
    }

    @Override
    public int updateReply(Long id, Long userId, BbsReplyParam bbsReplyParam) {
        BbsReply bbsReply = bbsReplyMapper.selectByPrimaryKey(id);

        if (bbsReply != null) {
            if (bbsReply.getUserId().equals(userId)) {
                bbsReply.setModifyTime(new Date());
                bbsReply.setVisible(bbsReplyParam.getVisible());
                bbsReply.setAnonymous(bbsReplyParam.getAnonymous());
                bbsReply.setContent(bbsReplyParam.getContent());
                return bbsReplyMapper.updateByPrimaryKeyWithBLOBs(bbsReply);
            } else {
                throw new AuthorizationServiceException("该用户没有更改权限");
            }
        }
        return 0;
    }

    @Override
    public int updateMultiReply(Long id, Long userId, BbsMultiReplyParam bbsMultiReplyParam) {
        BbsReplyMulti bbsReplyMulti = bbsReplyMultiMapper.selectByPrimaryKey(id);

        if (bbsReplyMulti != null) {
            if (bbsReplyMulti.getUserId().equals(userId)) {
                bbsReplyMulti.setVisible(bbsMultiReplyParam.getVisible());
                bbsReplyMulti.setAnonymous(bbsMultiReplyParam.getAnonymous());
                bbsReplyMulti.setContent(bbsMultiReplyParam.getContent());
                bbsReplyMulti.setModifyTime(new Date());
                return bbsReplyMultiMapper.updateByPrimaryKeyWithBLOBs(bbsReplyMulti);
            } else {
                throw new AuthorizationServiceException("该用户没有更改权限");
            }
        }
        return 0;
    }

    @Override
    public int deleteReply(Long id, Long userId) {
        BbsReply bbsReply = bbsReplyMapper.selectByPrimaryKey(id);

        if (bbsReply != null) {
            if (bbsReply.getUserId().equals(userId)) {
                return bbsReplyMapper.deleteByPrimaryKey(id);
            } else {
                throw new AuthorizationServiceException("该用户没有更改权限");
            }
        }
        return 0;
    }

    @Override
    public int deleteMultiReply(Long id, Long userId) {
        BbsReplyMulti bbsReplyMulti = bbsReplyMultiMapper.selectByPrimaryKey(id);

        if (bbsReplyMulti != null) {
            if (bbsReplyMulti.getUserId().equals(userId)) {
                return bbsReplyMultiMapper.deleteByPrimaryKey(id);
            } else {
                throw new AuthorizationServiceException("该用户没有更改权限");
            }
        }
        return 0;
    }
}