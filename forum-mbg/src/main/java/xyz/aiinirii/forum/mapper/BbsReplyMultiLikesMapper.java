package xyz.aiinirii.forum.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.forum.model.BbsReplyMultiLikes;
import xyz.aiinirii.forum.model.BbsReplyMultiLikesExample;

public interface BbsReplyMultiLikesMapper {
    long countByExample(BbsReplyMultiLikesExample example);

    int deleteByExample(BbsReplyMultiLikesExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("replyMultiId") Long replyMultiId);

    int insert(BbsReplyMultiLikes record);

    int insertSelective(BbsReplyMultiLikes record);

    List<BbsReplyMultiLikes> selectByExample(BbsReplyMultiLikesExample example);

    BbsReplyMultiLikes selectByPrimaryKey(@Param("userId") Long userId, @Param("replyMultiId") Long replyMultiId);

    int updateByExampleSelective(@Param("record") BbsReplyMultiLikes record, @Param("example") BbsReplyMultiLikesExample example);

    int updateByExample(@Param("record") BbsReplyMultiLikes record, @Param("example") BbsReplyMultiLikesExample example);

    int updateByPrimaryKeySelective(BbsReplyMultiLikes record);

    int updateByPrimaryKey(BbsReplyMultiLikes record);
}