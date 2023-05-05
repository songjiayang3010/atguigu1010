package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.config.pageDemo;
import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.SaleListGoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for(int i = 4;i > intCode.toString().length();i--){

            unitCode = "0"+unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }


    /**
     * 分页查询商品库存信息
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @Override
    public HashMap<String, Object> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //动态获取 page
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        //获取 页面数据
        List<Goods> listgoods = goodsDao.listInventory(offSet,rows,codeOrName,goodsTypeId);
        //获取总条数
        int total = goodsDao.gettotal(codeOrName,goodsTypeId);
        //遍历
        for (Goods goods : listgoods) {
            //获取 ID
            Integer goodsId = goods.getGoodsId();
            //通过 ID 获取 单个销售量
            Integer Sale = goodsDao.getNumber(goodsId);
            //如果没有销售量设置默认值
            Sale = Sale == null?0:Sale;
            //查询 退货总量
            Integer Customer =goodsDao.getCustomer(goodsId);
            //判断退货是否为空
            if (Customer != null){
                 // 该 ID 需要减少库存量
                Sale = Sale - Customer;
            }
            //设置该商品的销售量
            goods.setSaleTotal(Sale);
        }
        map.put("total",total);
        map.put("rows",listgoods);
        return map;
    }

    /**
     * 查询商品单位
     * @return
     */
    @Override
    public Map<String, Object> getGoodsUnit() {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //获取商品单位
        List<Unit> unitList =  goodsDao.getGoodsUnits();
        //参数赋值
        map.put("rows",unitList);
        return map;
    }

    /**
     * 分页查询商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param goodsName 商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @Override
    public Map<String, Object> getGoodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //动态调用页数
        page = page == 0?1:page;
        int pageSet = (page-1)*rows;
        //获取数据库数据
        List<Goods> goods = goodsDao.listInventory(page, rows, goodsName, goodsTypeId);
        //获取总条数
        int total = goodsDao.gettotal(goodsName, goodsTypeId);
        //赋值
        map.put("total",total);
        map.put("rows",goods);
        return map;
    }


    /**
     * 添加或修改商品信息
     * @param goods 商品信息实体
     * @return
     */
    @Override
    public ServiceVO saveOrUpdateGoods(Goods goods) {
        if (goods.getGoodsId() == null){
            //保存
            goodsDao.saveGoods(goods);
        }else {
            //修改
            goodsDao.upDateGoods(goods);
        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
    }


    /**
     * 删除商品信息
     * 要判断商品状态,入库、有进货和销售单据的不能删除
     * @param goodsId 商品ID
     * @return
     */
    @Override
    public ServiceVO delGoods(Integer goodsId) {
        //通过 ID 查询需要删除的商品
        Goods goods = goodsDao.getGoods(goodsId);
        //判断是否存在
        if (goods !=null){
            //判断是否 state状态 是否符合删除要求
            // 0 初始化状态 1 期初库存入仓库  2  有进货或者销售单据
            if (goods.getState()==0){
                //删除操作
                goodsDao.delGoods(goodsId);
                return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
            }else {
                //无法删除
                return new ServiceVO(ErrorCode.HAS_FORM_ERROR_CODE,ErrorCode.HAS_FORM_ERROR_MESS);
            }
        }
        return new ServiceVO(ErrorCode.HAS_FORM_ERROR_CODE,ErrorCode.HAS_FORM_ERROR_MESS);
    }

    /**
     * 分页查询无库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @Override
    public Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        //创建 存储对象
        HashMap<String, Object> map = new HashMap<>();
        //动态设置页数
        page = page == 0?1:page;
        Integer pageSet = (page - 1)*rows;
        //调用 数据库方法
        List<Goods> goodsList = goodsDao.getNoInventoryQuantity(pageSet,rows,nameOrCode);
        //获取总条数
        int total = goodsDao.getNOInventoryTotal(nameOrCode);
        map.put("total",total);
        map.put("rows",goodsList);
        return map;
    }

    /**
     * 分页查询有库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @Override
    public Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //动态获取页面数值
        Integer pageSet = pageDemo.getPage(page, rows);
        //调用数据库获取数据
        List<Goods> goodsList = goodsDao.getHasInventoryQuantity(pageSet,rows,nameOrCode);
        //获取总条数
        Integer total = goodsDao.getasInventoryTotal(nameOrCode);
        //参数赋值
        map.put("total",total);
        map.put("rows",goodsList);
        return map;
    }

    /**
     * 修改库存和成本价
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */
    @Override
    public ServiceVO saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice) {
        //修改 库存与成本价
        goodsDao.saveStock(goodsId,inventoryQuantity,purchasingPrice);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除商品库存
     * 要判断商品状态 入库、有进货和销售单据的不能删除
     * // 0 初始化状态 1 期初库存入仓库  2  有进货或者销售单据
     * @param goodsId 商品ID
     * @return
     */
    @Override
    public ServiceVO deleteStock(Integer goodsId) {
        //获取数据进行判断
        Goods goods = goodsDao.getGoods(goodsId);
        if (goods != null){
            if (goods.getState()==0){
                //修改库存数量
                goodsDao.upDateGoodsStock(goodsId);
                return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
            }else {
                //修改失败
                return new ServiceVO(ErrorCode.STORED_ERROR_CODE,ErrorCode.STORED_ERROR_MESS);
            }
        }
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 查询库存报警商品信息
     * @return
     */
    @Override
    public Map<String, Object> getListAlarm() {
        //创建存储对象
        HashMap<String, Object> map = new HashMap<>();
        //获取数据
        List<Goods> goodsList = goodsDao.getListAlarm();
        //参数赋值
        map.put("rows",goodsList);
        return map;
    }


}
