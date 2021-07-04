package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
//BaseMapper是MyBatisPlus提供的父类，里面封装丰富的的函数，
//很多增删改查就不用自己写了，直接调用
public interface UserMapper extends BaseMapper<UserBean> {

    //java接口有什么特点？
    //java接口，里面所有的函数都是抽象函数，没有函数体的函数叫抽象函数
    //Mapper有什么特点，是专门写sql语句的地方
    //MyBatis管理Mapper层，提供了很多注解用来实现增删改查
    //S调用Mapper层的函数，就相当于是执行了这个函数对应的SQL语句
    @Select("select * from tbl_user where username =#{username} and password =#{password}")
    UserBean getUserByUsernamePassword(@Param("username")String username,
                                       @Param("password") String password);
}
