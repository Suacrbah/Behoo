package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.CommentUserVO;
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
public interface CommentMapper extends BaseMapper<Comment> {

    //这句sql待解决
    @Select("SELECT comment.*,user.`username`,user.`avatar_url` FROM comment,user WHERE comment.answer_id=#{answer_id} and comment.user_id=user.id")
    List<CommentUserVO> getCommentUser(int answer_id) ;

}
