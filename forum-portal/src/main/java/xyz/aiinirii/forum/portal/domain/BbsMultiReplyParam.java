package xyz.aiinirii.forum.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建楼中楼回复时的封装类
 *
 * @author AIINIRII
 */
@Data
public class BbsMultiReplyParam {

    /**
     * 该字段若为null，则该回复为正常楼中楼回复；该字段若不为null，则该回复为回复楼中楼回复的回复。
     */
    @ApiModelProperty(value = "该字段若为null，则该回复为正常楼中楼回复；该字段若不为null，则该回复为回复楼中楼回复的回复。")
    private Long parentReplyId;

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
}