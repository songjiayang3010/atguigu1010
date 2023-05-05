package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;

import com.atguigu.jxc.entity.Unit;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();



    List<Goods> listInventory(@Param("offSet") Integer offSet, @Param("pageRow") Integer pageRow,@Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    int gettotal(@Param("codeOrName") String codeOrName,@Param("goodsTypeId") Integer goodsTypeId);

    /**
     * 商品单位
     */
    List<Unit> getGoodsUnits();

    /**
     * 新增商品
     * @param goods
     */
    void saveGoods(Goods goods);

    /**
     * 修改商品
     * @param goods
     */
    void upDateGoods(Goods goods);

    /**
     * 查询单条商品
     * @param goodsId
     * @return
     */
    Goods getGoods(Integer goodsId);

    /**
     * 删除商品
     * @param goodsId
     */
    void delGoods(Integer goodsId);

    /**
     * 分页查询无库存商品信息
     * @param pageSet 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    List<Goods> getNoInventoryQuantity(@Param("pageSet") Integer pageSet, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    /**
     * 获取无库存总条数
     * @param nameOrCode
     * @return
     */
    int getNOInventoryTotal(@Param("nameOrCode") String nameOrCode);

    /**
     * 分页查询有库存商品信息
     * @param pageSet 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    List<Goods> getHasInventoryQuantity(@Param("pageSet") Integer pageSet,@Param("rows") Integer rows,@Param("nameOrCode") String nameOrCode);

    /**
     * 获取有库存总条数
     * @param nameOrCode
     * @return
     */
    Integer getasInventoryTotal(@Param("nameOrCode") String nameOrCode);

    /**
     * 修改库存和成本价
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */
    void saveStock(@Param("goodsId") Integer goodsId, @Param("inventoryQuantity") Integer inventoryQuantity,@Param("purchasingPrice") double purchasingPrice);

    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     */
    void deleteStock(Integer goodsId);

    //修改库存数量
    void upDateGoodsStock(Integer goodsId);

    /**
     * 查询库存报警商品信息
     * @return
     */
    List<Goods> getListAlarm();

    Integer getNumber(Integer goodsId);

    Integer getCustomer(Integer goodsId);
}
