package xyz.aiinirii.forum.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Repository;

public class BbsReplyMultiLikes implements Serializable {
    /**
     * 用户序号
     */
    @ApiModelProperty(value = "用户序号")
    private Long userId;

    /**
     * 回复的回复序号
     */
    @ApiModelProperty(value = "回复的回复序号")
    private Long replyMultiId;

    /**
     * 点赞时间
     */
    @ApiModelProperty(value = "点赞时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReplyMultiId() {
        return replyMultiId;
    }

    public void setReplyMultiId(Long replyMultiId) {
        this.replyMultiId = replyMultiId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", replyMultiId=").append(replyMultiId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}