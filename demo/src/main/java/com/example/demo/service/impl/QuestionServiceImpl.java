package com.example.demo.service.impl;

import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionUserVO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Override
    public QuestionUserVO getQuestionUserVO(int question_id){
        return this.baseMapper.getQuestionUser(question_id);
    }

}
