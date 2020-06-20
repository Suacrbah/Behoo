package com.example.demo.shrio;



import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    public Integer id;

    public String username;

    public String introduction;

    public String email;

}

