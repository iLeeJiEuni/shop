package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.ProductBean;
import com.boot.shop.bean.ShoppingBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoppingMapper extends BaseMapper<ShoppingBean>  {
    /*@Select("select tbl_product.*,tbl_shopping.count from tbl_shopping left join tbl_product on tbl_shopping.pid = tbl_product.id where oid=#{oid}")
    List<ProductBean> getProduct(@Param("oid")int oid);*/
    @Select("select tbl_product.*,tbl_shopping.count ,tbl_category.category from tbl_product,tbl_shopping,tbl_category where tbl_shopping.pid = tbl_product.id and tbl_product.cid=tbl_category.id and oid = #{oid}")
    List<ProductBean> getProduct(@Param("oid")int oid);
}
