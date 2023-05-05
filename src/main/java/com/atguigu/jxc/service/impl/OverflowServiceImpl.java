package com.atguigu.jxc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.jxc.dao.OverflowMapper;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.service.OverflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OverflowServiceImpl implements OverflowService {

    @Autowired
    private OverflowMapper overflowMapper;

    /**
     * 新增报溢单
     * @param overflowList
     * @param overflowListGoodsStr
     * @return
     */
    @Override
    public ServiceVO overflowListGoods(OverflowList overflowList, String overflowListGoodsStr) {
        //保存 overflowList 获取主键
        overflowMapper.saveOverflow(overflowList);
        //将 overflowListGoodsStr string类型转换为json
        List<OverflowListGoods> overflowListGoods = JSONObject.parseArray(overflowListGoodsStr, OverflowListGoods.class);
        //获取主键
        Integer overflowListId = overflowList.getOverflowListId();
        //遍历
        for (OverflowListGoods overflowListGood : overflowListGoods) {
            //赋值外键
            overflowListGood.setOverflowListId(overflowListId);
            //保存 t_overflow_list_goods
            overflowMapper.saveOverflowListGoods(overflowListGood);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 获取报溢订单
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @Override
    public Map<String, Object> getOverflowList(String sTime, String eTime) {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //获取符合时间要求报溢订单
        List<OverflowList> overflowLists = overflowMapper.getOverflowList(sTime,eTime);
        //存储数据
        map.put("rows",overflowLists);
        return map;
    }

    /**
     * 报溢单商品信息
     * @param overflowListId 报溢订单号
     * @return
     */
    @Override
    public Map<String, Object> getGoodsList(Integer overflowListId) {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //根据 订单号获取商品
        List<OverflowListGoods> overflowListGoods = overflowMapper.getGoodsList(overflowListId);
        //存储数据
        map.put("rows",overflowListGoods);
        return map;
    }
}
