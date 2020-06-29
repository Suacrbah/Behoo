package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Answer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.AnswerUserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface AnswerService extends IService<Answer> {
    public Page<AnswerUserVO> getAnswerUser(Page<AnswerUserVO> page, int question_id);

    public int addAnswerLike(Integer answer_id);

    public int cacelAnswerLike(Integer answer_id);

}
