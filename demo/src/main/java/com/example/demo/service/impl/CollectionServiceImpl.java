package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Collection;
import com.example.demo.entity.CollectionVO;
import com.example.demo.mapper.CollectionMapper;
import com.example.demo.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

    @Override
    public Page<CollectionVO> getCollectionVO(Page<CollectionVO> page,int userId) {
        return page.setRecords(this.baseMapper.getCollectionAnswer(page,userId));
    }
}
