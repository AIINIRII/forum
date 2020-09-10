package xyz.aiinirii.forum.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Repository;

public class BbsPostLikes implements Serializable {
    /**
     * 用户序号
     */
    @ApiModelProperty(value = "用户序号")
    private Long userId;

    /**
     * 帖子序号
     */
    @ApiModelProperty(value = "帖子序号")
    private Long postId;

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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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
        sb.append(", postId=").append(postId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}