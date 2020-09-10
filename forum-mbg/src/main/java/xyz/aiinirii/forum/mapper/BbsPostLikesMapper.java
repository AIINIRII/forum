package xyz.aiinirii.forum.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.forum.model.BbsPostLikes;
import xyz.aiinirii.forum.model.BbsPostLikesExample;

public interface BbsPostLikesMapper {
    long countByExample(BbsPostLikesExample example);

    int deleteByExample(BbsPostLikesExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("postId") Long postId);

    int insert(BbsPostLikes record);

    int insertSelective(BbsPostLikes record);

    List<BbsPostLikes> selectByExample(BbsPostLikesExample example);

    BbsPostLikes selectByPrimaryKey(@Param("userId") Long userId, @Param("postId") Long postId);

    int updateByExampleSelective(@Param("record") BbsPostLikes record, @Param("example") BbsPostLikesExample example);

    int updateByExample(@Param("record") BbsPostLikes record, @Param("example") BbsPostLikesExample example);

    int updateByPrimaryKeySelective(BbsPostLikes record);

    int updateByPrimaryKey(BbsPostLikes record);
}