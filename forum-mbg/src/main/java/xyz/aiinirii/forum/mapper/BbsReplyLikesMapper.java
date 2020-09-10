package xyz.aiinirii.forum.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.forum.model.BbsReplyLikes;
import xyz.aiinirii.forum.model.BbsReplyLikesExample;

public interface BbsReplyLikesMapper {
    long countByExample(BbsReplyLikesExample example);

    int deleteByExample(BbsReplyLikesExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("replyId") Long replyId);

    int insert(BbsReplyLikes record);

    int insertSelective(BbsReplyLikes record);

    List<BbsReplyLikes> selectByExample(BbsReplyLikesExample example);

    BbsReplyLikes selectByPrimaryKey(@Param("userId") Long userId, @Param("replyId") Long replyId);

    int updateByExampleSelective(@Param("record") BbsReplyLikes record, @Param("example") BbsReplyLikesExample example);

    int updateByExample(@Param("record") BbsReplyLikes record, @Param("example") BbsReplyLikesExample example);

    int updateByPrimaryKeySelective(BbsReplyLikes record);

    int updateByPrimaryKey(BbsReplyLikes record);
}