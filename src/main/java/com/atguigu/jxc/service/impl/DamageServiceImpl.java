package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.jxc.dao.DamageMapper;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.service.DamageService;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DamageServiceImpl implements DamageService {

    @Autowired
    private DamageMapper damageMapper;

    /**
     * 新增报损单
     * @param damageList 报损单信息
     * @param damageListGoodsStr  商品信息
     * @return
     */
    @Override
    public ServiceVO saveDamage(DamageList damageList, String damageListGoodsStr) {
        //添加 damageList 获取 ID
        damageMapper.saveDamageList(damageList);
        //String 转换为实体类
        List<DamageListGoods> damageLists = JSONObject.parseArray(damageListGoodsStr, DamageListGoods.class);
        //获取主键赋值 t_damage_list_goods
        Integer damageListId = damageList.getDamageListId();
        //遍历
        for (DamageListGoods list : damageLists) {
            //传入每条数据 damage_list_id
            list.setDamageListId(damageListId);
            //储存数据
            damageMapper.saveDamageListGoods(list);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 查询报损单商品信息
     * @param damageListId 报损单Id
     * @return
     */
    @Override
    public Map<String, Object> getDamageListGoods(Integer damageListId) {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //调用数据库数据 t_damage_list_goods
        List<DamageListGoods> damageListGoodsList = damageMapper.getDamageListGoods(damageListId);
        //参数赋值
        map.put("rows",damageListGoodsList);
        return map;
    }

    /**
     * 报损单查询
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @Override
    public Map<String, Object> getTimeList(String sTime, String eTime) {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //数据库获取符合时间要求
        List<DamageList> damageLists  = damageMapper.getTimeList(sTime,eTime);
        //数据存储
        map.put("rows",damageLists);
        return map;
    }
}
