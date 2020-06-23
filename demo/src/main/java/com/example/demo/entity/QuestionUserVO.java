package com.example.demo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionUserVO  extends Question{

    private String username;

    private String introduction;

    private String avatarUrl;

}
