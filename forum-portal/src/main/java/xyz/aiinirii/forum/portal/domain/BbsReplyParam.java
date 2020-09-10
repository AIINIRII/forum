package xyz.aiinirii.forum.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建回复时的封装类
 *
 * @author AIINIRII
 */
@Data
public class BbsReplyParam {

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
    @NotBlank(message = "内容不能为空")
    private String content;

}