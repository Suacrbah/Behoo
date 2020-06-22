package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.CollectionVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface CollectionService extends IService<Collection> {

    public Page<CollectionVO> getCollectionVO(Page<CollectionVO> page,int userId);
}
