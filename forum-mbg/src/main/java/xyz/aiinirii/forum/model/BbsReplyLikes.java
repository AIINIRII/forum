package xyz.aiinirii.forum.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Repository;

public class BbsReplyLikes implements Serializable {
    /**
     * 用户序号
     */
    @ApiModelProperty(value = "用户序号")
    private Long userId;

    /**
     * 回复序号
     */
    @ApiModelProperty(value = "回复序号")
    private Long replyId;

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

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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
        sb.append(", replyId=").append(replyId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}