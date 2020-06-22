package com.example.demo.util;


import com.example.demo.shrio.AccountProfile;
import com.example.demo.shrio.AccountRealm;
import org.apache.shiro.SecurityUtils;

import java.lang.reflect.Method;

public class ShiroUtil {


    //这个函数暂时废了，有时候抽风无法转化
    public static AccountProfile getProfile() {
        Object obj=SecurityUtils.getSubject().getPrincipal();
        AccountProfile accountProfile=(AccountProfile) obj;
        return accountProfile;
    }
    public static Integer getAccountID() {
        Object obj=SecurityUtils.getSubject().getPrincipal();
        Integer id=(Integer) getFieldValueByName("id",obj);
        return id;

    }
    public static String getAccountName() {
        Object obj=SecurityUtils.getSubject().getPrincipal();
        String username=(String) getFieldValueByName("username",obj);
        return username;

    }
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
