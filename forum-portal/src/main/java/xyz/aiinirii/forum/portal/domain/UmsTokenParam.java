package xyz.aiinirii.forum.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户Token封装参数
 *
 * @author AIINIRII
 */
@Data
public class UmsTokenParam {

    @ApiModelProperty("生成的token字符串")
    private String token;

    @ApiModelProperty("服务器上的token头")
    private String tokenHead;

}