package com.boot.shop.controller;


import com.boot.shop.bean.OrderBean;
import com.boot.shop.bean.ProductBean;
import com.boot.shop.bean.ShoppingBean;
import com.boot.shop.mapper.CategoryMapper;
import com.boot.shop.mapper.OrderMapper;
import com.boot.shop.bean.WxResp;
import com.boot.shop.mapper.ProductMapper;
import com.boot.shop.mapper.ShoppingMapper;
import com.boot.shop.util.NotNullUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;
    //在浏览器中输入网址localhost：8080/wx/index
    @GetMapping("/index")
    public void index(HttpServletResponse resp) {
        WxResp r = new WxResp();
        r.category = categoryMapper.selectList(null);
        if (!r.category.isEmpty()) {
            int cid = r.category.get(0).getId();
            r.product = productMapper.getProduct(cid);
        }
        r.hot = productMapper.getHot();
        outRespJson(r,resp);//输出到小程序中或浏览器中
    }
    @GetMapping("/list")
    public void getProductById(int id , HttpServletResponse resp) {
        WxResp r = new WxResp();
        List<ProductBean> product = productMapper.getProductById(id);
        r.product=product;
        outRespJson(r, resp);
    }
    @GetMapping("/product")
    public void product(int cid, HttpServletResponse resp) {
        WxResp r = new WxResp();
        r.product= productMapper.getProduct(cid);
        outRespJson(r,resp);
    }
    @PostMapping("/order")
    public void order(OrderBean bean, HttpServletResponse resp) {
        //JSON字符串解析成数组
        //Gson:谷歌开发，专门用于生成解析json字符串的工具类
        // fromJson()第一个参数是json字符串,第二个参数是解析成什么
        List<ProductBean> product = new Gson().fromJson(bean.getJson(), new TypeToken<List<ProductBean>> () {}.getType());

        WxResp r = new WxResp();
        if(NotNullUtil.isBlank(bean)) {
            r.fail("请检查姓名、手机、地址是否填写完整");
        }
        else if (product.isEmpty()) {
            r.fail("购物车里空空如也");
        }
        else {//不为空才能提交成功，生成订单
            bean.setCtime(new Date());//ctime赋值当前时间
            orderMapper.insert(bean);
            System.out.println(bean.getId());//新增数据的主键id
            //订单表处理完之后，要把这一单哪些商品添加到shopping表中
            for (ProductBean p : product) {
                ShoppingBean s = new ShoppingBean();
                s.setOid(bean.getId());
                s.setPid(p.getId());
                s.setCount(p.getCount());
                shoppingMapper.insert(s);
            }
        }

        outRespJson(r,resp);
    }
}
