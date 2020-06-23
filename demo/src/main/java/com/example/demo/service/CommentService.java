package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.CommentUserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface CommentService extends IService<Comment> {

    public List<CommentUserVO> getCommentUserVO(int answer_id);

}
