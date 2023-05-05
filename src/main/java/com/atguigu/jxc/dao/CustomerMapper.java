package com.atguigu.jxc.dao;


import com.atguigu.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    /**
     * 获取用户页面数据
     * @param page
     * @param rows
     * @param customerName
     * @return
     */
    List<Customer> CustomerList(@Param("page") Integer page, @Param("rows") Integer rows,@Param("customerName") String customerName);

    /**
     * 用户页面总条数
     * @param customerName
     * @return
     */
    int getToTal(@Param("customerName") String customerName);

    /**
     * 新增用户
     * @param customer
     */
    void saveCustomer(Customer customer);

    /**
     * 修改用户
     * @param customer
     */
    void upDateCustomer(Customer customer);

    /**
     * 删除用户
     * @param ids
     */
    void DelCustomer(@Param("ids") String ids);
}
