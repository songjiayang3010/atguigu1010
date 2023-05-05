package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.DamageList;

import java.util.Map;

public interface DamageService {

    /**
     * 新增报损单
     * @param damageList 报损单信息
     * @param damageListGoodsStr  商品信息
     * @return
     */
    ServiceVO saveDamage(DamageList damageList, String damageListGoodsStr);

    /**
     * 查询报损单商品信息
     * @param damageListId 报损单Id
     * @return
     */
    Map<String, Object> getDamageListGoods(Integer damageListId);

    /**
     * 报损报溢查询（根据时间限制）
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    Map<String, Object> getTimeList(String sTime, String eTime);
}
