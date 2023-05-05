package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {

    /**
     * 获取供应商
     * @param page
     * @param rows
     * @param supplierName
     * @return
     */
    List<Supplier> getIndexList(@Param("page") Integer page,@Param("rows") Integer rows,@Param("supplierName") String supplierName);

    /**
     * 查询数量
     * @param supplierName
     * @return
     */
    int getTotal(String supplierName);

    /**
     * 新增
     * @param supplier
     */
    void saveSupplier(Supplier supplier);

    /**
     * 获取 sql
     * @param supplierId
     * @return
     */
    Supplier getSupplier(Integer supplierId);


    /**
     * 删除 sql语句
     * @param supplierId
     */
    void DelSuppplier(Integer supplierId);

    /**
     * 修改
     * @param supplier
     */
    void UpdateSuppplier(Supplier supplier);
}
