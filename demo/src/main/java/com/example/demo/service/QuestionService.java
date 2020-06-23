package com.example.demo.service;


import com.example.demo.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.QuestionUserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface QuestionService extends IService<Question> {

    public QuestionUserVO getQuestionUserVO(int question_id);

}
