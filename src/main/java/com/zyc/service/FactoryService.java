package com.zyc.service;

import com.zyc.entity.Factory;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 厂商factory类service层接口
 * @author: Zeke
 * @date: 2019/12/25 9:31
 */
public interface FactoryService {

    //查询所有
    List<Factory> selectAll();
    //分页查询所有
    List<Factory> selectAll(int page);
    //查询总数据量
    int selectAllSum();
    //添加
    void insert(Factory factory);
    //根据id删除
    void delete(String id);
    //修改
    void update(Factory factory);

}
