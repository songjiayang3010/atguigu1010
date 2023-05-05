package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品类别
 */
public interface GoodsTypeDao {

    List<GoodsType> getAllGoodsTypeByParentId(Integer pId);

    Integer updateGoodsTypeState(GoodsType parentGoodsType);


    /**
     * 商品分类新增
     * @param goodsTypeName 商品名称
     * @param pId
     * @return
     */
    void saveGoodsType(@Param("goodsTypeName") String goodsTypeName,@Param("pId") Integer pId);

    /**
     * 删除商品
     * @param goodsTypeId 分类 ID
     * @return
     */
    void delGoodsType(@Param("goodsTypeId") Integer goodsTypeId);
}
