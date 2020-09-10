package xyz.aiinirii.forum.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建Post时传入的参数
 *
 * @author AIINIRII
 */
@Data
public class BbsPostParam {

    /**
     * 论坛话题的序号
     */
    @ApiModelProperty(value = "论坛话题的序号", required = true)
    @NotBlank(message = "话题序号不能为空")
    private Long topicId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 是否可见（0 -> 不可见，1 -> 可见）
     */
    @ApiModelProperty(value = "是否可见（0 -> 不可见，1 -> 可见）", required = true)
    private Integer visible;

    /**
     * 是否匿名（0 -> 不匿名，1 -> 匿名）
     */
    @ApiModelProperty(value = "是否匿名（0 -> 不匿名，1 -> 匿名）", required = true)
    private Integer anonymous;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容", required = true)
    @NotBlank(message = "内容不能为空")
    private String content;
}