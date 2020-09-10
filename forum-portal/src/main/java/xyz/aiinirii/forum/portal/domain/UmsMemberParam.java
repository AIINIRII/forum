package xyz.aiinirii.forum.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 用户注册数据封装类
 *
 * @author AIINIRII
 */
@Data
public class UmsMemberParam {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不合法")
    private String email;

    @ApiModelProperty(value = "性别（0 -> 男, 1 -> 女）")
    @NotEmpty(message = "性别不能为空")
    private Integer gender;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;
}