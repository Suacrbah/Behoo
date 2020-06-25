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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        @GetMapping("/checkExist/{questionId}")
        public Result checkAnswerExist(@PathVariable(name = "questionId") Integer questionId){
            System.out.println("[answer]check exist!");
            Answer answer=answerService.getOne(new QueryWrapper<Answer>()
                    .eq("user_id",ShiroUtil.getAccountID())
                    .eq("question_id",questionId));
            if(answer==null){
                return Result.succ(200,"answer not exist",null);
            }else{
                return Result.succ(200,"answer exist",answer);
            }
        }

        @RequiresAuthentication
        @PostMapping("/add/{questionId}")
        public Result edit(@PathVariable(name = "questionId") Integer questionId,
                           @RequestParam("content") String content) {
            System.out.println("[answer] answer add!");
            Answer answer=answerService.getOne(new QueryWrapper<Answer>()
                    .eq("user_id",ShiroUtil.getAccountID())
                    .eq("question_id",questionId));
            if(answer==null){
                answer=new Answer();
                answer.setQuestionId(questionId);
                answer.setUserId(ShiroUtil.getAccountID());
                answer.setCreateTime(LocalDateTime.now());
                answer.setCreateTime(LocalDateTime.now());

                answer.setCollectCount(BigDecimal.valueOf(0));
                answer.setLikeCount(BigDecimal.valueOf(0));
            }
            answer.setContent(content);
            answer.setLastUpdateTime(LocalDateTime.now());

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
                    String uuid = UUID.randomUUID().toString().replaceAll("-","");
                    filename=uuid.substring(0,3)+filename;
                    String username=ShiroUtil.getProfile().getUsername();
                    filename=username+"_"+filename;

                    String uploadDir = "D:/nginx-1.18.0/html/images";
                    System.out.println("[image upload]user upload "+filename);
                    System.out.println();

                    String result=graphService.executeUpload(filename,uploadDir,file);
                    urlRet.put(i+1,"http://120.25.212.67:8205/images/"+filename);
                }
            }
            System.out.println(urlRet.toString());

            return Result.succ(200,
                    "图片 upload 成功",
                    urlRet);
        }




}
