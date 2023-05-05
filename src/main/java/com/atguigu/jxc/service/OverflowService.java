package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.OverflowList;

import java.util.Map;

public interface OverflowService {

    /**
     * 新增报溢单
     * @param overflowList
     * @param overflowListGoodsStr
     * @return
     */
    ServiceVO overflowListGoods(OverflowList overflowList, String overflowListGoodsStr);

    /**
     * 获取报溢订单
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    Map<String, Object> getOverflowList(String sTime, String eTime);

    /**
     * 报溢单商品信息
     * @param overflowListId 报溢订单号
     * @return
     */
    Map<String, Object> getGoodsList(Integer overflowListId);
}
