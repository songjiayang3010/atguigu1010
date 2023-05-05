package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.OverflowList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageMapper {

    /**
     * 存储 Damage 数据
     * @param damageList
     * @return
     */
    void saveDamageList(DamageList damageList);

    /**
     * 存入 Damage_list_goods 商品记录
     * @param list
     */
    void saveDamageListGoods(DamageListGoods list);

    /**
     * 查询报损单商品信息
     * @param damageListId 报损单Id
     * @return
     */
    List<DamageListGoods> getDamageListGoods(Integer damageListId);

    /**
     * 报损报溢查询（根据时间限制）
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    List<DamageList> getTimeList(@Param("sTime") String sTime,@Param("eTime") String eTime);
}
