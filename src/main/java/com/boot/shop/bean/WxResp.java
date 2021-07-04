package com.boot.shop.bean;

import java.util.List;

//给小程序返回的数据内容
public class WxResp {
    public int code = 1;
    public String msg = "";//响应提示语
    //失败函数
    public void fail(String msg) {
        this.code = 0;//让那个响应失败
        this.msg = msg;
    }
    public List<CategoryBean> category;//类别数组
    public List<ProductBean> product;//商品数组
    public List<ProductBean> hot;//热卖数组
}
