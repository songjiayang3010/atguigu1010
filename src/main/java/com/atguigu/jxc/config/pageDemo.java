package com.atguigu.jxc.config;

/**
 * 动态设置页数
 */
public class pageDemo {
    public static Integer getPage(Integer page, Integer rows){
        //动态设置页数
        page = page == 0?1:page;
        int pageSet = (page - 1)*rows;
        return pageSet;
    }
}
