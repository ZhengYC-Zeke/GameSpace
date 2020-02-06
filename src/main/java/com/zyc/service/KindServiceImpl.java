package com.zyc.service;

import com.zyc.entity.Game;
import com.zyc.entity.Kind;
import com.zyc.mapper.GameMapper;
import com.zyc.mapper.KindMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 类的描述
 * @author: Zeke
 * @date: 2019/12/23 16:40
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class KindServiceImpl implements KindService {

    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private GameMapper gameMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询所有
    public List<Kind> selectAll() {
        return kindMapper.selectAll();
    }

    @Override
    //添加
    public void add(Kind kind) {
        kindMapper.insert(kind);
    }

    @Override
    //删除
    public void delete(String id) {
        kindMapper.delete(new Kind().setId(id));
    }

    @Override
    //修改
    public void update(Kind kind) {
        //获取当前类别未修改的数据
        Kind oldKind = kindMapper.selectOne(new Kind().setId(kind.getId()));
        //对比类别名称是否发生变化
        if( ! oldKind.getName().equals(kind.getName())){
            //若发生变化则需要修改游戏对象的类别
            //获取所有游戏
            List<Game> games = gameMapper.select(new Game().setKind(oldKind.getName()));
            for (Game game : games) {
                game.setKind(kind.getName());
                gameMapper.updateByPrimaryKeySelective(game);
            }
        }
        kindMapper.updateByPrimaryKeySelective(kind);
    }

    @Override
    //分页查询
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Kind> selectAll(Integer page) {
        //分页查询,每页展示10条数据
        RowBounds rowBounds = new RowBounds((page-1)*Kind.kindPage , Kind.kindPage);
        List<Kind> kinds = kindMapper.selectByRowBounds(new Kind(), rowBounds);
        return kinds;
    }

    @Override
    //查询总数
    @Transactional(propagation = Propagation.SUPPORTS)
    public int selectAllSum() {
        return kindMapper.selectCount(new Kind());
    }

}
