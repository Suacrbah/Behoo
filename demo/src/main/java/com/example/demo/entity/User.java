package com.example.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "pwd不能为空")
    private String hashedPassword;

    private String avatarUrl;

    private String gender;

    private String introduction;

    private String email;

    private String phonenumber;

    private String industry;

    private String postition;

    private String career;

    private String education;

    private BigDecimal likeCount;

    private BigDecimal collectCount;

    private LocalDateTime createTime;


}
