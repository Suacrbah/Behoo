package com.example.demo.service.impl;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentUserVO;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<CommentUserVO> getCommentUserVO(int answer_id){
        return this.baseMapper.getCommentUser(answer_id);
    }

}
