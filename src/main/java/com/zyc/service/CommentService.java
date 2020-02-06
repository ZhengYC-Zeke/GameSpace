package com.zyc.service;

import com.zyc.entity.Comment;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 评论comment类的service层接口
 * @author: Zeke
 * @date: 2020/1/4 11:25
 */
public interface CommentService {

    //根据游戏分页查询所有
    List<Comment> selectAllByGame(String gameName , int page);

    //查询总数据量
    Integer selectAllSum(String gameName);

    //后台修改
    void update(Comment comment);

    //后台添加
    void insert(Comment comment , String gameName);

    //后台删除
    void delete(String id);

    //后台：查询所有待审核的评论
    List<Comment> selectStateIsCheck(int page);

    //后台：查询待审核评论总数
    Integer selectStateIsCheckSum();

    //前台：根据游戏名称查询6条评论
    List<Comment> selectSixByGameName(String gameName);

}
