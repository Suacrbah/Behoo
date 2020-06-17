package cn.edu.demo.service;

import cn.edu.demo.domain.User;
import cn.edu.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Sel(int id){
        return userMapper.Sel1(id);
    }

    public int validateByPassword(String username, String hashed_password)
    {
        User user = userMapper.selByUsernameAndPassword(username, hashed_password);
        if(user == null)
            return 0;
        else
            return 1;
    }

    public int registerUser(String username, String hashed_password)
    {
        userMapper.insertUser(username, hashed_password);
        return 1;
    }
}