package com.example.demo.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Answer;
import com.example.demo.service.AnswerService;
import com.example.demo.service.GraphService;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
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
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    GraphService graphService;

        @GetMapping("/question/{questionId}")
        public Result answers(@PathVariable(name = "questionId") Long questionId,Integer currentPage) {
            if(currentPage == null || currentPage < 1) currentPage = 1;
            Page page = new Page(currentPage, 5);
            IPage pageData = answerService.page(page,
                    new QueryWrapper<Answer>().
                            eq("question_id",questionId).
                            orderByDesc("create_time")
            );
            return Result.succ(pageData);
        }

        @RequiresAuthentication
        @GetMapping("/my_answer/")
        public Result getMyquestion(Integer currentPage) {
            System.out.println("[answer] personal answer request");
            Integer id=ShiroUtil.getAccountID();
            if(currentPage == null || currentPage < 1) {
                currentPage = 1;
            }

            Page page = new Page(currentPage, 5);
            IPage pageData = answerService.page(page, new QueryWrapper<Answer>()
                    .eq("user_id",id)
                    .orderByDesc("create_time")
            );

            return Result.succ(pageData);
        }


        @GetMapping("/{id}")
        public Result detail(@PathVariable(name = "id") Long id) {
            Answer answer = answerService.getById(id);
            Assert.notNull(answer, "该回答已删除！");
            return Result.succ(answer);
        }

        @RequiresAuthentication
        @PostMapping("/add/{questionId}")
        public Result edit(@PathVariable(name = "questionId") Integer questionId,
                           @RequestParam("content") String content) {
            System.out.println("[answer] answer add!");
            Answer answer=new Answer();
            answer.setQuestionId(questionId);
            answer.setUserId(ShiroUtil.getAccountID());
            answer.setContent(content);
            answer.setCreateTime(LocalDateTime.now());
            System.out.println(answer.toString());

            answerService.saveOrUpdate(answer);
            return Result.succ(200,"添加回答成功",answer);
        }

        @RequiresAuthentication
        @PostMapping("/image/upload")
        public Result imageUpload(MultipartFile[] files){
            System.out.println("[images]images upload");
            Map<Integer,String> urlRet=new HashMap<>();
            if(files.length>0){
                for(int i=0;i<files.length;i++){
                    MultipartFile file=files[i];
                    String filename = file.getOriginalFilename();
                    String username=ShiroUtil.getProfile().getUsername();
                    filename=username+"_"+filename;

                    String uploadDir = "D:/nginx-1.18.0/html/images";
                    System.out.println("[image upload]user upload "+filename);
                    System.out.println();

                    String result=graphService.executeUpload(filename,uploadDir,file);
                    urlRet.put(i,"http://192.168.43.233:89/images/"+filename);
                }
            }

            return Result.succ(200,
                    "图片 upload 成功",
                    urlRet);
        }




}
