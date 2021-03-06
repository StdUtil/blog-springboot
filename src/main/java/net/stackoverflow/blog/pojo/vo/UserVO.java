package net.stackoverflow.blog.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/**
 * 用户信息VO
 *
 * @author 凉衫薄
 */
@ApiModel(value = "用户")
public class UserVO {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "邮箱")
    @NotNull(message = "缺少邮箱字段", groups = {UserVO.RegisterGroup.class, UserVO.UpdateBaseGroup.class})
    @Size(min = 1, max = 100, message = "邮箱长度只能在1到100之间", groups = {UserVO.RegisterGroup.class, UserVO.UpdateBaseGroup.class})
    @Email(message = "邮箱格式错误", groups = {UserVO.RegisterGroup.class, UserVO.UpdateBaseGroup.class})
    private String email;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "缺少密码字段", groups = {UserVO.RegisterGroup.class, UserVO.UpdatePasswordGroup.class})
    @Size(min = 6, message = "密码长度必须大于等于6", groups = {UserVO.RegisterGroup.class, UserVO.UpdatePasswordGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "密码只能为数字字母下划线", groups = {UserVO.RegisterGroup.class, UserVO.UpdatePasswordGroup.class})
    private String password;

    @ApiModelProperty(value = "昵称")
    @NotNull(message = "缺少昵称字段", groups = {UserVO.RegisterGroup.class, UserVO.UpdateBaseGroup.class})
    @Size(min = 1, max = 100, message = "昵称长度只能在1到100之间", groups = {UserVO.RegisterGroup.class, UserVO.UpdateBaseGroup.class})
    private String nickname;

    @ApiModelProperty(value = "盐")
    private String salt;
    @ApiModelProperty(value = "是否可删除标志")
    private Integer deleteAble;

    //以下为扩展字段
    @ApiModelProperty(value = "旧密码")
    @NotNull(message = "缺少旧密码字段", groups = {UserVO.UpdatePasswordGroup.class})
    @NotBlank(message = "旧密码不能为空", groups = {UserVO.UpdatePasswordGroup.class})
    private String oldPassword;

    @ApiModelProperty(value = "验证码")
    @NotNull(message = "缺少验证码字段", groups = {UserVO.RegisterGroup.class})
    @NotBlank(message = "验证码不能为空", groups = {UserVO.RegisterGroup.class})
    private String vcode;

    public interface RegisterGroup {
    }

    public interface UpdateBaseGroup {
    }

    public interface UpdatePasswordGroup {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
}
