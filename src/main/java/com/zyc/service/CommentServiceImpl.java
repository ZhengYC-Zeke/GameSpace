package com.zyc.service;

import com.zyc.entity.Comment;
import com.zyc.mapper.CommentMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 评论comment类的service层接口实现类
 * @author: Zeke
 * @date: 2020/1/4 11:29
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //分页查询所有
    public List<Comment> selectAllByGame(String gameName , int page) {
        Example example = new Example(Comment.class);
        //设置查询数据按照时间排序
        example.orderBy("createDate").desc();
        //根据游戏名称搜索
        example.createCriteria().andEqualTo("gameName",gameName);
        RowBounds rowBounds = new RowBounds((page-1)*Comment.commentPage , Comment.commentPage);
        List<Comment> comments = commentMapper.selectByExampleAndRowBounds(example,rowBounds);
        return comments;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询数据总量
    public Integer selectAllSum(String gameName) {
        Example example = new Example(Comment.class);
        //设置查询数据按照时间排序
        example.orderBy("createDate").desc();
        //根据游戏名称搜索
        example.createCriteria().andEqualTo("gameName",gameName);
        return commentMapper.selectCountByExample(example);
    }

    @Override
    //后台修改
    public void update(Comment comment) {
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    //后台添加
    public void insert(Comment comment , String gameName) {
        comment.setId(UUID.randomUUID().toString());
        comment.setCreateDate(new Date());
        comment.setGameName(gameName);
        comment.setState("待审核");
        commentMapper.insert(comment);
    }

    @Override
    //后台删除
    public void delete(String id) {
        commentMapper.delete(new Comment().setId(id));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //后台：查询所有待审核的评论
    public List<Comment> selectStateIsCheck(int page) {
        Example example = new Example(Comment.class);
        //根据状态搜索
        example.createCriteria().andEqualTo("state","待审核");
        RowBounds rowBounds = new RowBounds((page-1)*Comment.commentPage , Comment.commentPage);
        List<Comment> comments = commentMapper.selectByExampleAndRowBounds(example, rowBounds);
        return comments;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //后台：查询所有待审核的评论
    public Integer selectStateIsCheckSum() {
        Example example = new Example(Comment.class);
        //根据状态搜索
        example.createCriteria().andEqualTo("state","待审核");
        return commentMapper.selectCountByExample(example);
    }

    @Override
    //前台：根据游戏查询6条评论
    public List<Comment> selectSixByGameName(String gameName) {
        //根据游戏名称搜索
        List<Comment> comments = commentMapper.selectSixByGameName(gameName);
        return comments;
    }
}
