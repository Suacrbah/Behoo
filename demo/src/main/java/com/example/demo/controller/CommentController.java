package com.example.demo.controller;


import com.example.demo.common.algorithm.Comment_list_transfer;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentUserVO;
import com.example.demo.entity.LikeAnswer;
import com.example.demo.service.CommentService;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        Comment_list_transfer clt = new Comment_list_transfer(commentUserVOs);
        Map<Integer,List<Integer>> ret = clt.get_new();
        return Result.succ(ret);
    }

    @RequiresAuthentication
    @PostMapping("/add")
    public Result addComment( @RequestParam("answer_id") Integer answerId,
                              @RequestParam("reply_to_id") Integer reply_to_id,
                              @RequestParam("content") String content){
        Comment comment=new Comment();

        comment.setAnswerId(answerId);
        comment.setReplyToId(reply_to_id);
        comment.setUserId(ShiroUtil.getAccountID());
        comment.setContent(content);

        return Result.succ(200,"添加评论成功",null);

    }

}
