package xyz.aiinirii.forum.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Repository;

public class BbsReplyMulti implements Serializable {
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long id;

    /**
     * 回复的回复的序号
     */
    @ApiModelProperty(value = "回复的回复的序号")
    private Long parentId;

    /**
     * 用户序号
     */
    @ApiModelProperty(value = "用户序号")
    private Long userId;

    /**
     * 该字段若为null，则该回复为正常楼中楼回复。该字段若不为null，则该回复为回复楼中楼回复的回复。
     */
    @ApiModelProperty(value = "该字段若为null，则该回复为正常楼中楼回复。该字段若不为null，则该回复为回复楼中楼回复的回复。")
    private Long parentReplyId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date publishTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    /**
     * 是否可见（0 -> 不可见，1 -> 可见）
     */
    @ApiModelProperty(value = "是否可见（0 -> 不可见，1 -> 可见）")
    private Integer visible;

    /**
     * 是否匿名（0 -> 不匿名，1 -> 匿名）
     */
    @ApiModelProperty(value = "是否匿名（0 -> 不匿名，1 -> 匿名）")
    private Integer anonymous;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentReplyId() {
        return parentReplyId;
    }

    public void setParentReplyId(Long parentReplyId) {
        this.parentReplyId = parentReplyId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", userId=").append(userId);
        sb.append(", parentReplyId=").append(parentReplyId);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", visible=").append(visible);
        sb.append(", anonymous=").append(anonymous);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}