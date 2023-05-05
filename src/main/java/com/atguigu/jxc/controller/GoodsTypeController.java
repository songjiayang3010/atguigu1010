package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.service.GoodsTypeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @description 商品类别控制器
 */
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 查询所有商品类别
     * @return easyui要求的JSON格式字符串
     */
    @PostMapping("/loadGoodsType")
    @RequiresPermissions(value={"商品管理","进货入库","退货出库","销售出库","客户退货","当前库存查询","商品报损","商品报溢","商品采购统计"},logical = Logical.OR)
    public ArrayList<Object> loadGoodsType() {
        return goodsTypeService.loadGoodsType();
    }

    /**
     * 商品分类新增
     * @param goodsTypeName 商品名称
     * @param pId 分类层级
     * @return
     */
    @PostMapping("/save")
    public ServiceVO saveGoodsType(String goodsTypeName,Integer pId){
        //调用服务层方法
        return goodsTypeService.saveGoodsType(goodsTypeName,pId);
    }

    /**
     * 删除商品
     * @param goodsTypeId 分类 ID
     * @return
     */
    @PostMapping("/delete")
    public ServiceVO delGoodsType(Integer goodsTypeId){
        return goodsTypeService.delGoodsType(goodsTypeId);
    }
}
