package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Answer;
import com.example.demo.entity.AnswerUserVO;
import com.example.demo.entity.Question;
import com.example.demo.entity.QuestionUserVO;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import com.example.demo.shrio.AccountProfile;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;


    @GetMapping("")
    public Result questions(Integer currentPage) {
        System.out.println("[question] questions");
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = questionService.page(page, new QueryWrapper<Question>().orderByDesc("view_count"));

        return Result.succ(pageData);
    }

    @GetMapping("/{id}")
    public Result questionDetail(@PathVariable(name = "id") Integer id) {
        System.out.println("[question] question detail");
        QuestionUserVO questionUserVO= questionService.getQuestionUserVO(id);
        Question question=questionService.getById(id);
        BigDecimal before=question.getViewCount();
        BigDecimal after=before.add(BigDecimal.valueOf(1));
        question.setViewCount(after);
        questionService.saveOrUpdate(question);
        return Result.succ(questionUserVO);
    }

    @GetMapping("/{id}/answers")
    public Result questionAnswerDetail(@PathVariable(name = "id") Integer id,Integer currentPage) {
        System.out.println("[question] question detail answer");
        if(currentPage==null) currentPage=1;
//        Question question = questionService.getById(id);
        Page page = new Page(currentPage, 10);
        IPage pageData = answerService.getAnswerUser(page,id);
        return Result.succ(pageData);
    }


    @RequiresAuthentication
    @GetMapping("/my_question/")
    public Result getMyquestion(Integer currentPage) {
        System.out.println("[questionl] personal question request");

        Integer id=ShiroUtil.getAccountID();

        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = questionService.page(page, new QueryWrapper<Question>().eq("user_id",id).orderByDesc("view_count"));

        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @PostMapping("/search")
    public Result questionSearch(Integer currentPage,@RequestParam("key_word") String keyWord) {
        System.out.println("[question] search: keyword");
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 10);
        IPage pageData = questionService.page(page, new QueryWrapper<Question>().like("title",keyWord).orderByDesc("view_count"));

        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @PostMapping("/add")
    public Result edit(@RequestParam("title") String title,@RequestParam("content") String content) {
        System.out.println("[question] question add!");

        Question question=new Question();
        question.setUserId(ShiroUtil.getAccountID());
        question.setTitle(title);
        question.setContent(content);
        question.setCreateTime(LocalDateTime.now());
        question.setViewCount(BigDecimal.valueOf(0));
        System.out.println(question.toString());

        questionService.saveOrUpdate(question);
        return Result.succ(200,"添加问题成功",question);
    }

}
