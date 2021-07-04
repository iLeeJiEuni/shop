package com.boot.shop.controller;


import com.boot.shop.bean.ProductBean;
import com.boot.shop.mapper.ProductMapper;
import com.boot.shop.util.NotNull;
import com.boot.shop.util.NotNullUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductMapper productMapper;
    @GetMapping("/list")
    public String list(int cid, HttpServletRequest req) {
        req.setAttribute("cid",cid);
        req.setAttribute("retList",productMapper.getProduct(cid));
        return "/product/list";//.html
    }
    @GetMapping("/add")
    public String add(int cid, Integer id, HttpServletRequest req) {
        req.setAttribute("cid",cid);
        req.setAttribute("bean",id != null ? productMapper.selectById(id) : null);
        return "/product/add";//.html
    }
    @PostMapping("/add")
    public String add(ProductBean bean,HttpServletResponse resp) {
        if (NotNullUtil.isBlank(bean)) {
            return jsAlert("请完善信息",
                    ("/product/add?cid="+bean.getCid() + (bean.getId() != null ? ("&id="+bean.getId()) : "")),
                    resp);
        }
        if (bean.getId() == null) {
            productMapper.insert(bean);
        }
        else {
            productMapper.updateById(bean);
        }
        return "redirect:/product/list?cid="+bean.getCid();
    }
    @GetMapping("/del")
    public String del(int id, int cid) {
        productMapper.deleteById(id);
        return "redirect:/product/list?cid="+cid;//.html
    }
    @RequestMapping("/logo")
    public void logo(MultipartFile file, HttpServletResponse resp) {
        String fileName=file.getOriginalFilename();//获取文件原文件名
        System.out.println(fileName);
        try {
            file.transferTo(new File("F:/image/shop/file/"+fileName));
        } catch (IOException e) {
            System.out.println("你文件的保存路径写的不对");
            e.printStackTrace();
        }
        outRespJson("/shop/file/"+fileName,resp);//输出图片保存路径
    }
}
