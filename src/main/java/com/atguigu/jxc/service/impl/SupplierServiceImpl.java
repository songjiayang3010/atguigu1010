package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierMapper;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 供应商首页展示
     * @param page
     * @param rows
     * @param supplierName 供应商名称
     * @return
     */
    @Override
    public Map<String, Object> getIndexList(Integer page, Integer rows, String supplierName) {
        //调整 page的更新
        HashMap<String, Object> map = new HashMap<>();
        page = page == 0 ? 1:page;
        int offSet = (page -1) * rows;
        //调用dao方法
        List<Supplier> suppliers =
            supplierMapper.getIndexList(page,rows,supplierName);
        //查询数量
        int total =
                supplierMapper.getTotal(supplierName);
        map.put("total",total);
        map.put("rows",suppliers);
        return map;
    }

    /**
     * 保存或者修改
     * @param supplier
     * @return
     */
    @Override
    public ServiceVO save(Supplier supplier) {
        //判断修改还是保存
        if (supplier.getSupplierId() == null){
            //保存
            supplierMapper.saveSupplier(supplier);
        }else {
            //修改
            supplierMapper.UpdateSuppplier(supplier);
        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @Override
    public ServiceVO delSupplier(String ids) {
        supplierMapper.DelSuppplier(new Integer(ids));
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }
}
