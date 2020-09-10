package xyz.aiinirii.forum.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

public class BbsTopic implements Serializable {
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long id;

    /**
     * 话题题目
     */
    @ApiModelProperty(value = "话题题目")
    private String title;

    /**
     * 是否可见（0 -> 不可见，1 -> 可见）
     */
    @ApiModelProperty(value = "是否可见（0 -> 不可见，1 -> 可见）")
    private Integer visible;

    /**
     * 话题的描述
     */
    @ApiModelProperty(value = "话题的描述")
    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", visible=").append(visible);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}