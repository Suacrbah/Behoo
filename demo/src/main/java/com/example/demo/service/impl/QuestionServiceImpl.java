package com.example.demo.service.impl;

import com.example.demo.entity.Question;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
