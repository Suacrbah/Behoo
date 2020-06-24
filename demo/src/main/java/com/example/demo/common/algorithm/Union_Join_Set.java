package com.example.demo.common.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Union_Join_Set {
    private int [] par;
    private List<Integer> list;
    private Map<Integer, Integer> mp = new HashMap<>();

    public Union_Join_Set(int cnt, List<Integer> _list){

        this.list = _list;
        par = new int[cnt];

        for(int i = 0 ; i < list.size(); i++){
            mp.put(list.get(i), i);
        }

        for(int i = 0; i<cnt; i++){
            par[i]=i;
        }
    }



    public int find(int x){

        if(x==par[x]) return par[x];
        else return find(par[x]);
    }

    public void unite(int x, int y){
        x = find(mp.get(x));
        y = find(mp.get(y));

        if(x==y) return;


        par[y]=x;

    }

    public int get_old_id(int _id){
        return list.get(_id);
    }

    public  int get_new_id(int _id){
        return mp.get(_id);
    }


}
