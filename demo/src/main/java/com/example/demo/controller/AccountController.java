package com.example.demo.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.dto.LoginDto;
import com.example.demo.common.dto.RegistryDto;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.User;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MailService mailService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        System.out.println("=====================login aciton");
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        //本来应该是assert返回，但是刘博就是不干
        //Assert.notNull(user, "用户不存在，请更换");
        if(user==null){
            return Result.fail("用户不存在");
        }

        String truepwd=user.getHashedPassword();
        String inputpwd=loginDto.getHashedPassword();

//        if(!turepwd.equals(SecureUtil.md5(inputpwd))){
//            return Result.fail("密码不正确");
//        }

        if(!truepwd.equals(inputpwd)){
            return Result.fail("密码不正确");
        }else{
            System.out.println("密码正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(200,"登录成功",MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar-url", user.getAvatarUrl())
                .put("email", user.getEmail())
                .put("introduction",user.getIntroduction())
                .map());
    }
    @PostMapping("/registry")
    public Result registry(@Validated @RequestBody RegistryDto registryDto, HttpServletResponse response) {
        System.out.println("=====================registry aciton");
        User user=new User();
        user.setUsername(registryDto.getUsername());
        user.setHashedPassword(registryDto.getHashedPassword());
        user.setEmail(registryDto.getEmail());
        user.setIntroduction("快乐每一天");
        userService.save(user);

        return Result.succ(200,"registry success",null);


    }


    @GetMapping("/registryValid/{email}")
    public Result registry(@PathVariable String email, HttpServletResponse response) {
       Assert.notNull(email, "请填写正确格式邮箱");
       String [] rec=new String[1];
       rec[0]=email;
        int randomCode=1000+(int)(Math.random()*(9999-1000+1));
        System.out.println("【】发送邮件："+email);
        try {
            mailService.sendMail("behoo",
                    "[behoo验证码]"+String.valueOf(randomCode),"2775153204@qq.com",rec);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return Result.succ(200,String.valueOf(randomCode),null);
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

}
