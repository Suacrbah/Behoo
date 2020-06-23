package com.example.demo.mapper;


import com.example.demo.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.QuestionUserVO;
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
public interface QuestionMapper extends BaseMapper<Question> {

    @Select("SELECT question.*,user.`username`,user.`introduction`,user.`avatar_url` FROM question,user WHERE question.id=#{question_id} and question.user_id=user.id")
    QuestionUserVO getQuestionUser(int question_id) ;

}
