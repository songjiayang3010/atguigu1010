package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.service.OverflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OverflowController {

    @Autowired
    private OverflowService overflowService;

    /**
     * 新增报溢单
     * @param overflowList
     * @param overflowListGoodsStr
     * @return
     */
    @PostMapping("/overflowListGoods/save")
    public ServiceVO overflowListGoods(OverflowList overflowList, String overflowListGoodsStr){
        return overflowService.overflowListGoods(overflowList,overflowListGoodsStr);
    }

    /**
     * 获取报溢订单
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @PostMapping("overflowListGoods/list")
    public Map<String,Object> getOverfolowList(String sTime,String eTime){
        return overflowService.getOverflowList(sTime,eTime);
    }

    /**
     * 报溢单商品信息
     * @param overflowListId 报溢订单号
     * @return
     */
    @PostMapping("/overflowListGoods/goodsList")
    public Map<String,Object> getGoodsList(Integer overflowListId){
        return overflowService.getGoodsList(overflowListId);
    }
}
