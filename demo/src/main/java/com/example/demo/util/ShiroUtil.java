package com.example.demo.util;


import com.example.demo.shrio.AccountProfile;
import com.example.demo.shrio.AccountRealm;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile getProfile() {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        return accountProfile;
    }

}
