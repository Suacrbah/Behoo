package cn.edu.demo.controller;

import cn.edu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @RequestMapping(value="/login",method= RequestMethod.POST)
//    public int login(@RequestParam(value="username", required = true, defaultValue = "root") String username,
//                        @RequestParam(value="hashed_password", required = true, defaultValue = "admin") String hashed_password){
//        int validateResult = userService.validateByPassword(username, hashed_password);
////        if(validateResult==1){
////            return "Login Success";
////        }else{
////            return "Login Faild";
////        }
//        return validateResult;
//    }

    @RequestMapping(value="/api/login/{username}/{hashed_password}",method= RequestMethod.POST)
    public int login(@PathVariable String username,
                     @PathVariable  String hashed_password){
        int validateResult = userService.validateByPassword(username, hashed_password);

        System.out.println("hello");
        System.out.println(validateResult);

        return validateResult;
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String register(@RequestParam(value="username", required = true, defaultValue = "root") String username,
                        @RequestParam(value="hashed_password", required = true, defaultValue = "admin") String hashed_password){
        System.out.println("hello"+ username + hashed_password);

        int registerResult = userService.registerUser(username, hashed_password);
        if(registerResult==1){
            return "Register Success";
        }else{
            return "Register Faild";
        }
    }
}
