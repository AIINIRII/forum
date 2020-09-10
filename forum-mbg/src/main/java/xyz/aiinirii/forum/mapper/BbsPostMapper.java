package xyz.aiinirii.forum.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.forum.model.BbsPost;
import xyz.aiinirii.forum.model.BbsPostExample;

public interface BbsPostMapper {
    long countByExample(BbsPostExample example);

    int deleteByExample(BbsPostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BbsPost record);

    int insertSelective(BbsPost record);

    List<BbsPost> selectByExampleWithBLOBs(BbsPostExample example);

    List<BbsPost> selectByExample(BbsPostExample example);

    BbsPost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BbsPost record, @Param("example") BbsPostExample example);

    int updateByExampleWithBLOBs(@Param("record") BbsPost record, @Param("example") BbsPostExample example);

    int updateByExample(@Param("record") BbsPost record, @Param("example") BbsPostExample example);

    int updateByPrimaryKeySelective(BbsPost record);

    int updateByPrimaryKeyWithBLOBs(BbsPost record);

    int updateByPrimaryKey(BbsPost record);
}