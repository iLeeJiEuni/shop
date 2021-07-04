package com.boot.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.shop.bean.ProductBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper extends BaseMapper<ProductBean> {
    @Select("select tbl_product.*,tbl_category.category from tbl_product left join tbl_category on tbl_product.cid = tbl_category.id where cid=#{cid}")
    List<ProductBean> getProduct(@Param("cid")int cid);

    @Select("select * from tbl_product where hot = 1" )
    List<ProductBean> getHot();

    @Select("select tbl_product.*,tbl_category.category from tbl_product left join tbl_category on tbl_product.cid = tbl_category.id where tbl_product.id = #{cid}")
    List<ProductBean> getProductById(@Param("cid") int cid );

}
