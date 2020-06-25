package com.example.demo.common.algorithm;

import com.example.demo.entity.Comment;
import com.example.demo.entity.CommentUserVO;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comment_list_transfer {
    private List<CommentUserVO> commentUserVOs;

    public Comment_list_transfer(List<CommentUserVO> _commentUserVOS){
        this.commentUserVOs = _commentUserVOS;
    }

    public Map<Integer,List<CommentUserVO> >  get_new(){
        int length = commentUserVOs.size();

        List<Integer> list = new ArrayList<>(length);
        for(int i =0 ; i< length; i++){
            int tmp = commentUserVOs.get(i).getId();
            list.add(tmp);
        }
        Union_Join_Set ujs = new Union_Join_Set(length, list);
        Map<Integer, List<CommentUserVO > > mp = new HashMap<>();

        for(int i = 0; i < length; i++){
            if(commentUserVOs.get(i).getReplyToId()==null){
                List<CommentUserVO> tmp_list = new ArrayList<>();
                mp.put(commentUserVOs.get(i).getId(), tmp_list);
                continue;
            }

            int child_id = commentUserVOs.get(i).getId();
            int par_id = commentUserVOs.get(i).getReplyToId();

            ujs.unite(par_id, child_id);

        }

        for(int i = 0; i<length; i++){
            int comment_id = commentUserVOs.get(i).getId();
            int ancester_id = ujs.find(ujs.get_new_id(comment_id));
            ancester_id = ujs.get_old_id(ancester_id);
            if(mp.containsKey(ancester_id)){
                mp.get(ancester_id).add(commentUserVOs.get(i));
            }
        }
        return mp;
    }
}
