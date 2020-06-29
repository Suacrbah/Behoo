package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Answer;
import com.example.demo.entity.LikeAnswer;
import com.example.demo.service.AnswerService;
import com.example.demo.service.LikeAnswerService;
import com.example.demo.service.UserService;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/like_answer")
public class LikeAnswerController {
    @Autowired
    LikeAnswerService likeAnswerService;
    @Autowired
    AnswerService answerService;

    @Autowired
    UserService userService;

    @RequiresAuthentication
    @GetMapping("/{answerId}")
    public Result addLikeAnswer(@PathVariable(name = "answerId") Integer answerId) {

        LikeAnswer likeAnswer=likeAnswerService.getOne(new QueryWrapper<LikeAnswer>()
                .eq("user_id",ShiroUtil.getAccountID())
                .eq("answer_id",answerId));

        if(likeAnswer == null){
            likeAnswerService.addLikeAnswer(answerId,ShiroUtil.getAccountID());
            Integer user_id=answerService.addAnswerLike(answerId);
            userService.addAnswerLike(user_id);

            System.out.println("[like answer] answer like!");
            return Result.succ(200,"点赞成功",likeAnswer);
        }else{
            likeAnswerService.delecteLikeAnswer(answerId,ShiroUtil.getAccountID());
            Integer user_id=answerService.cacelAnswerLike(answerId);
            userService.cacelAnswerLike(user_id);

            System.out.println("[like answer] answer like cancel!");
            return Result.fail(400,"取消点赞",likeAnswer);
        }
    }

}
