package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.service.DamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DamageController {

    @Autowired
    private DamageService damageService;


    /**
     * 新增报损单
     * @param damageList 报损单信息
     * @param damageListGoodsStr  商品信息
     * @return
     */
    @PostMapping("/damageListGoods/save")
    public ServiceVO saveDamage(DamageList damageList, String damageListGoodsStr){
        return damageService.saveDamage(damageList,damageListGoodsStr);
    }


    /**
     * 查询报损单商品信息
     * @param damageListId 报损单Id
     * @return
     */
    @PostMapping("/damageListGoods/goodsList")
    public Map<String,Object> getDamageListGoods(Integer damageListId){
        return damageService.getDamageListGoods(damageListId);
    }


    /**
     * 报损报溢查询（根据时间限制）
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return
     */
    @PostMapping("/damageListGoods/list")
    public Map<String,Object> getTimeList(String sTime, String eTime){
        return damageService.getTimeList(sTime,eTime);
    }

}
