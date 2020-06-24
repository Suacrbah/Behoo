package com.example.demo.service;

import com.example.demo.entity.LikeAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface LikeAnswerService extends IService<LikeAnswer> {

    public int delecteLikeAnswer(Integer answer_id,Integer user_id);

}
