package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Customer;

import java.util.Map;

public interface CustomerService {

    /**
     * 获取用户首页
     * @param page
     * @param rows
     * @param customerName
     * @return
     */
    Map<String, Object> getCustomerList(Integer page, Integer rows, String customerName);

    /**
     * 保存或者修改
     * @param customer
     * @return
     */
    ServiceVO saveOrUpdateCustomer(Customer customer);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    ServiceVO DelCustomer(String ids);
}
