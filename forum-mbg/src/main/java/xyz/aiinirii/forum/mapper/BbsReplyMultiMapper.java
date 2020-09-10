package xyz.aiinirii.forum.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.forum.model.BbsReplyMulti;
import xyz.aiinirii.forum.model.BbsReplyMultiExample;

public interface BbsReplyMultiMapper {
    long countByExample(BbsReplyMultiExample example);

    int deleteByExample(BbsReplyMultiExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BbsReplyMulti record);

    int insertSelective(BbsReplyMulti record);

    List<BbsReplyMulti> selectByExampleWithBLOBs(BbsReplyMultiExample example);

    List<BbsReplyMulti> selectByExample(BbsReplyMultiExample example);

    BbsReplyMulti selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BbsReplyMulti record, @Param("example") BbsReplyMultiExample example);

    int updateByExampleWithBLOBs(@Param("record") BbsReplyMulti record, @Param("example") BbsReplyMultiExample example);

    int updateByExample(@Param("record") BbsReplyMulti record, @Param("example") BbsReplyMultiExample example);

    int updateByPrimaryKeySelective(BbsReplyMulti record);

    int updateByPrimaryKeyWithBLOBs(BbsReplyMulti record);

    int updateByPrimaryKey(BbsReplyMulti record);
}