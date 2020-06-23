package com.example.demo.controller;


import com.example.demo.common.lang.Result;
import com.example.demo.entity.CommentUserVO;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/answer/{id}")
    public Result detail(@PathVariable(name = "id") Integer id) {
        List<CommentUserVO> commentUserVOs = commentService.getCommentUserVO(id);
        return Result.succ(commentUserVOs);
    }

}
