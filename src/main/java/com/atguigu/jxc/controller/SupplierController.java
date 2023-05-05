package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 供应商
 */
@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 供应商首页展示
     * @param page
     * @param rows
     * @param supplierName 供应商名称
     * @return
     */
    @PostMapping("/supplier/list")
    public Map<String,Object> getIndexList(Integer page, Integer rows, String supplierName){
        return supplierService.getIndexList(page,rows,supplierName);
    }


    /**
     * 查看修改 还是 保存
     * @param supplier
     * @return
     */
   @PostMapping("/supplier/save")
   public ServiceVO save(Supplier supplier){
       return supplierService.save(supplier);
   }


    /**
     * 删除
     * @param ids
     * @return
     */
   @PostMapping("/supplier/delete")
    public ServiceVO delSupplier(String ids){
        return supplierService.delSupplier(ids);
   }
}
