package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.CollectionVO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口x
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    @Select("SELECT collection.*,answer.`content` FROM collection,answer WHERE collection.answer_id=answer.id and collection.user_id=#{user_id}")
    List<CollectionVO> getCollectionAnswer(Page page, int user_id) ;

}
