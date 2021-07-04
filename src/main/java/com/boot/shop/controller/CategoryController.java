package com.boot.shop.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.shop.bean.CategoryBean;
import com.boot.shop.mapper.CategoryMapper;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
    //new Mappper层
    @Autowired
    private CategoryMapper categoryMapper;
    //查询函数
    @RequestMapping("/list")
    public String list(HttpServletRequest req) {
        System.out.println("正在执行查询函数");
        //select * from tbl_category
        req.setAttribute("retList",categoryMapper.selectList(null));
        return "/category/list";//自动拼接上.html
    }
    //<a>标签按钮打开新页面，是get请求
    //<form>表单提交数据，指定method="post"
    //添加函数，修改函数都使用add函数
    @GetMapping("/add")//get请求
    public String add(Integer id, HttpServletRequest req) {//打来页面
        //条件 ？ 情况1 ：情况2;
        req.setAttribute("bean", id != null ? categoryMapper.selectById(id) : null);
        System.out.println("正在执行添加函数");
        return "/category/add";//自动拼接.html,没有写redirect就是转发
    }
    @PostMapping("/add")//post请求
    public String add(CategoryBean bean, HttpServletResponse resp) {//表单提交
        //判空校验
        if (StringUtils.isBlank(bean.getCategory())){
            //弹出错误提示
            return jsAlert("请输入类别名称",
                    ("/category/add"+(bean.getId() != null ? ("?id="+bean.getId()) : "")),
                    resp);
        }
        try {
            if (bean.getId() == null) {
                categoryMapper.insert(bean);
            }
            else {
                categoryMapper.updateById(bean);
            }
            } catch (Exception e) {
                return jsAlert(bean.getCategory() + "已经存在了",
                        ("/category/add"+(bean.getId() != null ? ("?id="+bean.getId()) : "")),
                        resp);
        }
        return "redirect:/category/list";
    }
    //转发和重定向的区别
    //转发，浏览器地址栏不会发生改变
        //可以通过HTTPServletRequest带参数到页面中
        //转发末尾不要带.html
        //转发打开的页面要放在templates里面
    //重定向，浏览器地址栏会改变，变成另一个地址
        //重定向末尾如果是页面，就要写.html
        //打来新页面嚯使用<a>标签href跳转页面就是重定向
        //重定向如果要打开页面，要把页面放在static里面
    //删除函数
    @RequestMapping("/del")
    public String del(int id) {
        System.out.println("正在执行删除函数");
        categoryMapper.deleteById(id);
        //delete from tbl_category where id=#{id}
        return "redirect:/category/list";//重定向到查询
    }
}
