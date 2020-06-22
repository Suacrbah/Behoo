package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.lang.Result;
import com.example.demo.entity.Collection;
import com.example.demo.entity.Question;
import com.example.demo.service.CollectionService;
import com.example.demo.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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




}
