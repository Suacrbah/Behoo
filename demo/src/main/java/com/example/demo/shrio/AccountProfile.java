package com.example.demo.shrio;



import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String introduction;

    private String email;

}

