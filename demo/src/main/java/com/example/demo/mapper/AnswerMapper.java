package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Answer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.AnswerUserVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lucas
 * @since 2020-06-18
 */
public interface AnswerMapper extends BaseMapper<Answer> {
    @Select("SELECT answer.*,user.`username`,user.`introduction`,user.`avatar_url` FROM answer,user WHERE answer.question_id=#{question_id} and answer.user_id=user.id")
    List<AnswerUserVO> getAnswerUser(Page page, int question_id) ;

}
