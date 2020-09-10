package xyz.aiinirii.forum.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.forum.model.BbsTopic;
import xyz.aiinirii.forum.model.BbsTopicExample;

public interface BbsTopicMapper {
    long countByExample(BbsTopicExample example);

    int deleteByExample(BbsTopicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BbsTopic record);

    int insertSelective(BbsTopic record);

    List<BbsTopic> selectByExampleWithBLOBs(BbsTopicExample example);

    List<BbsTopic> selectByExample(BbsTopicExample example);

    BbsTopic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BbsTopic record, @Param("example") BbsTopicExample example);

    int updateByExampleWithBLOBs(@Param("record") BbsTopic record, @Param("example") BbsTopicExample example);

    int updateByExample(@Param("record") BbsTopic record, @Param("example") BbsTopicExample example);

    int updateByPrimaryKeySelective(BbsTopic record);

    int updateByPrimaryKeyWithBLOBs(BbsTopic record);

    int updateByPrimaryKey(BbsTopic record);
}