package com.example.demo.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserDetailDto implements Serializable{

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String hashedPassword;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    private String avatarUrl;
    private String gender;
    private String introduction;
    private String phonenumber;
    private String industry;
    private String postition;
    private String career;
    private String education;
}