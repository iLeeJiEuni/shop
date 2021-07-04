package com.boot.shop.controller;


import com.boot.shop.mapper.OrderMapper;
import com.boot.shop.mapper.ShoppingMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping")
public class ShoppingController extends BaseController{
    @Autowired
    private ShoppingMapper shoppingMapper;
    @GetMapping("/list")
    public String list(int oid, HttpServletRequest req) {
        req.setAttribute("oid",oid);
        req.setAttribute("retList",shoppingMapper.getProduct(oid));
        return "/shopping/list";//.html
    }
}
