package com.zyc.service;

import com.zyc.entity.Kind;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 种类kind类service层接口
 * @author: Zeke
 * @date: 2019/12/23 16:39
 */

public interface KindService {

    //查询所有
    List<Kind> selectAll();
    //添加
    void add(Kind kind);
    //删除
    void delete(String id);
    //修改
    void update(Kind kind);
    //分页查询所有
    List<Kind> selectAll(Integer page);
    //查询总数
    int selectAllSum();

}
