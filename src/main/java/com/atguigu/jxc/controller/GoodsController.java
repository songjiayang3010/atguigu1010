package com.atguigu.jxc.controller;

//import com.baomidou.mybatisplus.core.metadata.IPage;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 商品信息Controller
 */
@Api(tags = "测试")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询商品库存信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    // /goods/listInventory
    @PostMapping("/goods/listInventory")
    public Map<String,Object> listInventory(Integer page, Integer rows,String codeOrName,Integer goodsTypeId){
        HashMap<String,Object> map=
            goodsService.listInventory(page,rows,codeOrName,goodsTypeId);
        return map;
    }

    /**
     * 查询商品的单位
     * @return
     */
    @PostMapping("/unit/list")
    public Map<String,Object> goodsUnit(){
        //调用服务层方法
        return goodsService.getGoodsUnit();
    }

    /**
     * 分页查询商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param goodsName 商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("/goods/list")
    public Map<String,Object> getGoodsList(Integer page,Integer rows,String goodsName,Integer goodsTypeId){
        return goodsService.getGoodsList(page,rows,goodsName,goodsTypeId);
    }

    /**
     * 生成商品编码
     * @return
     */
    @RequestMapping("/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     * @param goods 商品信息实体
     * @return
     */
    @PostMapping("/goods/save")
    public ServiceVO saveOrUpdateGoods(Goods goods){
        return goodsService.saveOrUpdateGoods(goods);
    }

    /**
     * 删除商品信息
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("/goods/delete")
    public ServiceVO delGoods(Integer goodsId){
        //调用服务层方法
        return goodsService.delGoods(goodsId);
    }

    /**
     * 分页查询无库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("/goods/getNoInventoryQuantity")
    public Map<String,Object> getNoInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        return goodsService.getNoInventoryQuantity(page,rows,nameOrCode);
    }

    /**
     * 分页查询有库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("/goods/getHasInventoryQuantity")
    public Map<String,Object> getHasInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        return goodsService.getHasInventoryQuantity(page,rows,nameOrCode);
    }

    /**
     * 修改库存和成本价
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */
    @PostMapping("/goods/saveStock")
    public ServiceVO saveStock(Integer goodsId,Integer inventoryQuantity,double purchasingPrice){
        return goodsService.saveStock(goodsId,inventoryQuantity,purchasingPrice);
    }

    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("/goods/deleteStock")
    public ServiceVO deleteStock(Integer goodsId){
        return goodsService.deleteStock(goodsId);
    }

    /**
     * 查询库存报警商品信息
     * @return
     */
    @PostMapping("/goods/listAlarm")
    public Map<String,Object> getListAlarm(){
        return goodsService.getListAlarm();
    }

}
