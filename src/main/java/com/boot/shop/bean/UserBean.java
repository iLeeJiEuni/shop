package com.boot.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//一个Bean对应一张表，在springboot中是一种规则
@TableName("tbl_user")
public class UserBean {
    //主键id是自动增长的，auto_increament
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;
    private String username;
    private String password;

    //生成set()、get()方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
