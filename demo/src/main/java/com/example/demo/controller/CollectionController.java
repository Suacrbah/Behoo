package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Collection;
import com.example.demo.entity.Question;
import com.example.demo.service.AnswerService;
import com.example.demo.service.CollectionService;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    AnswerService answerService;

    @RequiresAuthentication
    @GetMapping("/my_collection/")
    public Result getMyCollection(Integer currentPage){
        if(currentPage==null || currentPage<1){
            currentPage=1;
        }

        Integer id=ShiroUtil.getAccountID();
        Page page = new Page(currentPage, 5);
        IPage pageData = collectionService.getCollectionVO(page,id);
        return Result.succ(pageData);
    }

    @RequiresAuthentication
    @GetMapping("/{answerId}")
    public Result addLikeAnswer(@PathVariable(name = "answerId") Integer answerId) {

        Collection collection=collectionService.getOne(new QueryWrapper<Collection>()
                .eq("user_id",ShiroUtil.getAccountID())
                .eq("answer_id",answerId));


        if(collection == null){
            collection=new Collection();


            collection.setUserId(ShiroUtil.getAccountID());
            collection.setAnswerId(answerId);
            collectionService.save(collection);

            Answer answer=answerService.getById(answerId);
            BigDecimal collectCount=answer.getCollectCount();
            collectCount=collectCount.add(BigDecimal.valueOf(1));
            answer.setCollectCount(collectCount);
            answerService.saveOrUpdate(answer);

            System.out.println("[collect answer] answer collected!");

            return Result.succ(200,"收藏成功",collection);
        }else{
            collectionService.delecteCollection(answerId,ShiroUtil.getAccountID());
            Answer answer=answerService.getById(answerId);
            BigDecimal collectCount=answer.getCollectCount();
            collectCount=collectCount.subtract(BigDecimal.valueOf(1));
            answer.setCollectCount(collectCount);
            answerService.saveOrUpdate(answer);
            System.out.println("[collect answer] answer collection canceled!");

            return Result.fail(400,"取消收藏",collection);

        }
    }




}
