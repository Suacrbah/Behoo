package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public Result answers(Integer currentPage) {

        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        IPage pageData = questionService.page(page, new QueryWrapper<Question>().orderByDesc("view_count"));

        pageData.getRecords();
        return Result.succ(pageData);
    }

    @GetMapping("/{id}")
    public Result answerDetail(@PathVariable(name = "id") Long id) {
        Question answer = questionService.getById(id);
        Assert.notNull(answer, "该问题已删除！");
        return Result.succ(answer);
    }

}
