package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerMapper;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Map<String, Object> getCustomerList(Integer page, Integer rows, String customerName) {
        //创建传送数值对象
        HashMap<String, Object> map = new HashMap<>();
        //动态获取页数
        page = page == 0?1:page;
        int pageset = (page - 1)*rows;
        //获取页面数据
        List<Customer> customerList =
                customerMapper.CustomerList(page,rows,customerName);
        //获取 总条数
        int total = customerMapper.getToTal(customerName);
        //提交数据
        map.put("total",total);
        map.put("rows",customerList);
        return map;
    }

    /**
     * 保存或者修改
     * @param customer
     * @return
     */
    @Override
    public ServiceVO saveOrUpdateCustomer(Customer customer) {
        //通过 ID 判断是保存/修改
        if (customer.getCustomerId() == null){
            //保存
            customerMapper.saveCustomer(customer);
        }else {
            //修改
            customerMapper.upDateCustomer(customer);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public ServiceVO DelCustomer(String ids) {
        //分割
        String[] split = ids.split(",");
        for (String idList : split) {
            //根据 ID 删除用户
            customerMapper.DelCustomer(idList);
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }
}
