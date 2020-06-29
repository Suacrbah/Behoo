package com.example.demo.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.dto.LoginDto;
import com.example.demo.common.dto.RegistryDto;
import com.example.demo.common.dto.UserDetailDto;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.User;
import com.example.demo.service.GraphService;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtils;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    MailService mailService;

    @Autowired
    GraphService graphService;

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
                .put("avatar_url", user.getAvatarUrl())
                .put("email", user.getEmail())
                .put("introduction",user.getIntroduction())
                .map());
    }
    @PostMapping("/registry")
    public Result registry(@Validated @RequestBody RegistryDto registryDto) {
        System.out.println("=====================registry aciton");
        User user=new User();
        user.setUsername(registryDto.getUsername());
        user.setHashedPassword(registryDto.getHashedPassword());
        user.setEmail(registryDto.getEmail());
        user.setIntroduction("快乐每一天");
        user.setCreateTime(LocalDateTime.now());
        user.setCollectCount(BigDecimal.valueOf(0));
        user.setLikeCount(BigDecimal.valueOf(0));
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

    @RequiresAuthentication
    @GetMapping("/user/getInfo")
    public Result getInfo() {
        User user=userService.getById(ShiroUtil.getAccountID());
        return Result.succ(200,"succ",user);
    }

    @RequiresAuthentication
    @PostMapping("/user/update")
    public Result updatePersonalInfo(@Validated @RequestBody UserDetailDto userDetailDto){
        Integer userId=ShiroUtil.getAccountID();
        User user=userService.getById(userId);

        user.setUsername(userDetailDto.getUsername());
        user.setHashedPassword(userDetailDto.getHashedPassword());
        user.setGender(userDetailDto.getGender());
        user.setEmail(userDetailDto.getEmail());

        user.setAvatarUrl(userDetailDto.getAvatarUrl());
        user.setEducation(userDetailDto.getEducation());
        user.setIntroduction(userDetailDto.getIntroduction());
        user.setPhonenumber(userDetailDto.getPhonenumber());
        user.setIndustry(userDetailDto.getIndustry());
        user.setPostition(userDetailDto.getPostition());
        user.setCareer(userDetailDto.getCareer());
        user.setEducation(userDetailDto.getEducation());

        return Result.succ(200,"suc",user);

    }

    @RequiresAuthentication
    @PostMapping("/avatar/upload")
    public Result imageUpload(MultipartFile[] files){
        System.out.println("[user]avatar upload");
        MultipartFile file=files[0];


        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String filename = file.getOriginalFilename();
        filename=uuid.substring(0,4)+filename;
        String username=ShiroUtil.getProfile().getUsername();
        filename=username+"_"+filename;

        String uploadDir = "D:/nginx-1.18.0/html/images/avatar";
        System.out.println("[user]user  avatar upload "+filename);
        System.out.println();

        String result=graphService.executeUpload(filename,uploadDir,file);
        String ret="http://120.25.212.67:8205/images/avatar/"+filename;

        User user=userService.getById(ShiroUtil.getAccountID());
        user.setAvatarUrl(ret);
        userService.saveOrUpdate(user);

        return Result.succ(200,ret,null);
    }



}
