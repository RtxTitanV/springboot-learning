package com.rtxtitanv.model;

import com.rtxtitanv.annotation.Password;
import com.rtxtitanv.annotation.Rank;
import com.rtxtitanv.group.AddUserGroup;
import com.rtxtitanv.group.ModifyUserGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author rtxtitanv
 * @version 1.0.0
 * @name com.rtxtitanv.model.User
 * @description 用户实体类
 * @date 2021/8/15 16:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @NotNull(message = "id不能为空", groups = ModifyUserGroup.class)
    @Null(message = "id必须为空", groups = AddUserGroup.class)
    @NotNull(message = "id不能为空")
    private Long id;
    @Length(min = 6, max = 20, message = "用户名长度不小于6，不超过20")
    @NotNull(message = "用户名不能为空")
    private String username;
//    @Pattern(regexp = "^[A-Z][A-Za-z0-9_]{5,19}$", message = "密码以大写英文字母开头，只包含英文字母、数字、下划线，长度在6到20之间")
    @Password(message = "密码必须以大写英文字母开头，只包含英文字母、数字、下划线，长度在6到20之间")
    @NotNull(message = "密码不能为空")
    private String password;
    @Max(value = 60, message = "年龄最大为60")
    @Min(value = 18, message = "年龄最小为18")
    @NotNull(message = "年龄不能为空")
    private Integer age;
    @Email(message = "邮箱格式不正确")
    @NotEmpty(message = "邮箱不能为空")
    private String email;
    @Rank(message = "段位必须为无段位、青铜、白银、黄金、铂金、钻石之一")
    private String rank;
}