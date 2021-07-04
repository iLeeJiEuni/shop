package com.boot.shop.controller;


import com.boot.shop.mapper.OrderMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    private OrderMapper orderMapper;
    @GetMapping("/list")
    public String list(HttpServletRequest req) {
        req.setAttribute("retList",orderMapper.getOrder());
        return "/order/list";//.html
    }
}
