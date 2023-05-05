package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 客户列表分页
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 获取用户首页
     * @param page
     * @param rows
     * @param customerName
     * @return
     */
    @PostMapping("/customer/list")
    public Map<String,Object> getCustomerList(Integer page,Integer rows,String customerName){
        return customerService.getCustomerList(page,rows,customerName);
    }

    /**
     * 用户新增或者修改
     * @param customer
     * @return
     */
    @PostMapping("/customer/save")
    public ServiceVO saveOrUpdateCustomer(Customer customer){
        return customerService.saveOrUpdateCustomer(customer);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @PostMapping("/customer/delete")
    public ServiceVO DelCustomer(String ids){
        return customerService.DelCustomer(ids);
    }
}
