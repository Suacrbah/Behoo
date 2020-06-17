package cn.edu.demo.mapper;


import cn.edu.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Mapper
public interface UserMapper {
    User Sel1(int id);
    User selByUsernameAndPassword(String username,String hashed_password);
    void insertUser(String username,String hashed_password);
}