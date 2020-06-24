package com.example.demo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentUserVO extends Comment{
    private String username;
    private String avatarUrl;


}
