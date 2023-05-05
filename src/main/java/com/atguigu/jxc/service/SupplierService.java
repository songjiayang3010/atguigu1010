package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;

import java.util.Map;

public interface SupplierService {

    /**
     * 供应商首页展示
     * @param page
     * @param rows
     * @param supplierName 供应商名称
     * @return
     */
    Map<String, Object> getIndexList(Integer page, Integer rows, String supplierName);

    /**
     * 保存或者修改
     * @param supplier
     * @return
     */
    ServiceVO save(Supplier supplier);

    /**
     * 删除
     * @param ids
     * @return
     */
    ServiceVO delSupplier(String ids);
}
