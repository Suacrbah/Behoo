package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Answer;
import com.example.demo.entity.AnswerUserVO;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.service.AnswerService;
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
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Override
    public Page<AnswerUserVO> getAnswerUser(Page<AnswerUserVO> page, int question_id){
        return page.setRecords(this.baseMapper.getAnswerUser(page,question_id));
    }
    @Override
    public int addAnswerLike(Integer answer_id){
        BigDecimal tmp=new BigDecimal("1");
        Answer answer=this.getById(answer_id);
        BigDecimal likeCount=answer.getLikeCount();
        BigDecimal newlikeCount=likeCount.add(tmp);
        answer.setLikeCount(newlikeCount);
        this.saveOrUpdate(answer);
        return answer.getUserId();
    }

    @Override
    public int cacelAnswerLike(Integer answer_id){
        Answer answer=this.getById(answer_id);
        BigDecimal subnum=new BigDecimal("1");
        BigDecimal likeCount=answer.getLikeCount();
        BigDecimal newlikeCount=likeCount.subtract(subnum);
        answer.setLikeCount(newlikeCount);
        this.saveOrUpdate(answer);
        return answer.getUserId();
    }
}
