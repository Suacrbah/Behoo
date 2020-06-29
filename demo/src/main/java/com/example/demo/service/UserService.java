package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface UserService extends IService<User> {

    public int addAnswerLike(Integer user_id);

    public int cacelAnswerLike(Integer user_id);

}
