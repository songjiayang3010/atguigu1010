package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;

import java.util.ArrayList;

/**
 * @description
 */
public interface GoodsTypeService {
    ArrayList<Object> loadGoodsType();

    /**
     * 商品分类新增
     * @param goodsTypeName 商品名称
     * @param pId
     * @return
     */
    ServiceVO saveGoodsType(String goodsTypeName, Integer pId);

    /**
     * 删除商品
     * @param goodsTypeId 分类 ID
     * @return
     */
    ServiceVO delGoodsType(Integer goodsTypeId);

}
