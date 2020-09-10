package xyz.aiinirii.forum.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.forum.model.BbsReply;
import xyz.aiinirii.forum.model.BbsReplyExample;

public interface BbsReplyMapper {
    long countByExample(BbsReplyExample example);

    int deleteByExample(BbsReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BbsReply record);

    int insertSelective(BbsReply record);

    List<BbsReply> selectByExampleWithBLOBs(BbsReplyExample example);

    List<BbsReply> selectByExample(BbsReplyExample example);

    BbsReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BbsReply record, @Param("example") BbsReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") BbsReply record, @Param("example") BbsReplyExample example);

    int updateByExample(@Param("record") BbsReply record, @Param("example") BbsReplyExample example);

    int updateByPrimaryKeySelective(BbsReply record);

    int updateByPrimaryKeyWithBLOBs(BbsReply record);

    int updateByPrimaryKey(BbsReply record);
}