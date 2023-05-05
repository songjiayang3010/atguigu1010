package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverflowMapper {

    //保存 overflowList 获取主键
    void saveOverflow(OverflowList overflowList);

    //保存
    void saveOverflowListGoods(OverflowListGoods overflowListGood);

    /**
     * 获取报溢订单
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    List<OverflowList> getOverflowList(@Param("sTime") String sTime,@Param("eTime") String eTime);

    /**
     * 报溢单商品信息
     * @param overflowListId 报溢订单号
     * @return
     */
    List<OverflowListGoods> getGoodsList(Integer overflowListId);
}
