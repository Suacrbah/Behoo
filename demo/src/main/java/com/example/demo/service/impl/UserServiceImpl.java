package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public int addAnswerLike(Integer user_id){
        User user=this.getById(user_id);

        BigDecimal beforeLikeCount=user.getLikeCount();
        BigDecimal tmp=new BigDecimal("1");

        BigDecimal currentLikeCount=beforeLikeCount.add(tmp);
        user.setLikeCount(currentLikeCount);

        this.saveOrUpdate(user);
        return 1;
    }

    @Override
    public int cacelAnswerLike(Integer user_id){
        User user=this.getById(user_id);

        BigDecimal beforeLikeCount=user.getLikeCount();
        BigDecimal tmp=new BigDecimal("1");

        BigDecimal currentLikeCount=beforeLikeCount.subtract(tmp);
        user.setLikeCount(currentLikeCount);

        this.saveOrUpdate(user);
        return 1;

    }

}
