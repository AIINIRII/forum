package xyz.aiinirii.forum.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建话题时的封装类
 *
 * @author AIINIRII
 */
@Data
public class BbsTopicParam {

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
}