package com.boot.shop.controller;


import com.boot.shop.mapper.HotMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hot")
public class HotController {
    @Autowired
    private HotMapper hotMapper;
    @GetMapping("/list")
    public String list(HttpServletRequest req) {
        req.setAttribute("retList",hotMapper.getHot());
        return "/hot/list";//.html
    }
}
