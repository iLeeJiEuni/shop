package com.boot.shop.controller;

import com.boot.shop.bean.UserBean;
import com.boot.shop.mapper.UserMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller//这个普通类就变成了控制器，遵循springMvc编码规则
public class UserController {
    @Autowired//这个相当于 new UserMapper()
    private UserMapper userMapper;//new UserMapper()
    //SpringMvc用法
    //使用浏览器调用登录函数，给登录函数配置一个可以访问到的网址
    //在浏览器中输入这个网址，查看结果
    //localhost:8080/login?username=张三&password=123456
    @RequestMapping("/login")
    public String login(String username, String password) {

        System.out.println(username);
        System.out.println(password);
        UserBean user = userMapper.getUserByUsernamePassword(username, password);
        if (user == null) {
            //这个人没找到，用户名或密码不对
            try {
                return "redirect:/index.html?msg= " + URLEncoder.encode("用户名或密码错误","UTF-8");
            } catch (UnsupportedEncodingException e) {
                //TOOD Auto-generator catch block
                e.printStackTrace();
                //根本就不会出现异常，也不会走到这一行
                return null;
            }
        }
        else {
            //登陆成功，跳转到主页面
            return "redirect:/main?uid="+user.getId();//跳转到/main，带上当前用户的uid
        }
    }

    //访问这个localhost:8080/main，就进入到了main()里面去了，打开maon.html页面
    @RequestMapping("/main")
    public String main(int uid, HttpServletRequest req) {
        System.out.println("当前登录的用户id为："+uid);
        //Select * from tbi_user where uid =#{uid}
        //把admain带入到main.html中展示出来
        //跳转页面怎么带参数？你会看到一个HTTPServletRequest
        req.setAttribute("bean",userMapper.selectById(uid));
        return "/main";//意思就是，跳转到/main.html
    }
}
