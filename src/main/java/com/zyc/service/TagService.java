package com.zyc.service;

import com.zyc.entity.Tag;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 标签tag类service层
 * @author: Zeke
 * @date: 2020/1/5 9:47
 */
public interface TagService {

    //查询所有（每个标签库最多存20条标签）
    List<Tag> selectAllByGame(String gameName);
    //后台添加
    void insert(Tag tag , String gameName);
    //后台修改
    void update(Tag tag , String gameName);
    //后台删除
    void delete(Tag tag , String gameName);
    //后台删除当前所选游戏的标签
    void clearByGameName(String gameName);
    //后台删除所有标签
    void clearAll();

    //前台查询三条标签数据
    List<Tag> selectThreeByGame(String gameName);

}
