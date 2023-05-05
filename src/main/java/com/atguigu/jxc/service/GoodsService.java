package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;


import java.util.HashMap;
import java.util.Map;

public interface GoodsService {

    /**
     *
     * @return
     */

    ServiceVO getCode();


    /**
     * 分页查询商品库存信息
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    HashMap<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);


    /**
     * 查询商品单位
     * @return
     */
    Map<String, Object> getGoodsUnit();

    /**
     * 获取商品展示数据
     * @param page
     * @param rows
     * @param goodsName
     * @param goodsTypeId
     * @return
     */
    Map<String, Object> getGoodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    /**
     * 添加或修改商品信息
     * @param goods 商品信息实体
     * @return
     */
    ServiceVO saveOrUpdateGoods(Goods goods);

    /**
     * 删除商品信息
     * @param goodsId 商品ID
     * @return
     */
    ServiceVO delGoods(Integer goodsId);

    /**
     * 分页查询无库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    /**
     * 分页查询有库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    /**
     * 修改库存和成本价
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */
    ServiceVO saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice);


    ServiceVO deleteStock(Integer goodsId);

    /**
     * 查询库存报警商品信息
     * @return
     */
    Map<String, Object> getListAlarm();

}
