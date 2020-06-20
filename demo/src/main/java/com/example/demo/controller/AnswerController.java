package com.example.demo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Answer;
import com.example.demo.service.AnswerService;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

        @Autowired
        AnswerService answerService;

        @GetMapping("/")
        public Result answers(Integer currentPage) {

            if(currentPage == null || currentPage < 1) currentPage = 1;
            Page page = new Page(currentPage, 5);
            IPage pageData = answerService.page(page, new QueryWrapper<Answer>().orderByDesc("create_time"));
            return Result.succ(pageData);
        }

        @GetMapping("/{id}")
        public Result detail(@PathVariable(name = "id") Long id) {
            Answer answer = answerService.getById(id);
            Assert.notNull(answer, "该回答已删除！");
            return Result.succ(answer);
        }

        @RequiresAuthentication
        @PostMapping("/edit")
        public Result edit(@Validated @RequestBody Answer answer) {
            System.out.println(answer.toString());
            Answer temp = null;
            if(answer.getId() != null) {
                temp = answerService.getById(answer.getId());
                Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()), "没有权限编辑");
            } else {
                temp = new Answer();
                temp.setUserId(ShiroUtil.getProfile().getId());
                temp.setCreateTime(LocalDateTime.now());
                temp.setQuestionId(0);
            }
            BeanUtil.copyProperties(answer, temp, "id", "userId", "create_time");
            answerService.saveOrUpdate(temp);
            return Result.succ(200,"operation success",null);
        }



}
