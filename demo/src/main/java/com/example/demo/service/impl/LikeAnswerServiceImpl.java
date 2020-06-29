package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.LikeAnswer;
import com.example.demo.mapper.LikeAnswerMapper;
import com.example.demo.service.LikeAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.ShiroUtil;
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
public class LikeAnswerServiceImpl extends ServiceImpl<LikeAnswerMapper, LikeAnswer> implements LikeAnswerService {
    @Override
    public int delecteLikeAnswer(Integer answer_id,Integer user_id){
        return  this.baseMapper.delete(new QueryWrapper<LikeAnswer>()
                .eq("user_id",user_id )
                .eq("answer_id",answer_id));
    }
    @Override
    public int addLikeAnswer(Integer answer_id,Integer user_id){
        LikeAnswer likeAnswer=new LikeAnswer();
        likeAnswer.setAnswerId(answer_id);
        likeAnswer.setUserId(user_id);
        this.save(likeAnswer);
        return 1;
    }

}
