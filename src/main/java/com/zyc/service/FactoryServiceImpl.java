package com.zyc.service;

import com.zyc.entity.Factory;
import com.zyc.entity.Game;
import com.zyc.mapper.FactoryMapper;
import com.zyc.mapper.GameMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 厂商factory类service层实现类
 * @author: Zeke
 * @date: 2019/12/25 9:34
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private GameMapper gameMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询所有
    public List<Factory> selectAll() {
        return factoryMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //分页查询所有
    public List<Factory> selectAll(int page) {
        //每页展示5条数据
        RowBounds rowBounds = new RowBounds((page-1)*Factory.factoryPage,Factory.factoryPage);
        List<Factory> factories = factoryMapper.selectByRowBounds(new Factory(), rowBounds);
        return factories;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询总数据量
    public int selectAllSum() {
        return factoryMapper.selectCount(new Factory());
    }

    @Override
    //添加
    public void insert(Factory factory) {
        factoryMapper.insert(factory);
    }

    @Override
    //根据id删除
    public void delete(String id) {
        factoryMapper.delete(new Factory().setId(id));
    }

    @Override
    //修改
    public void update(Factory factory) {
        //获取数据库中的对象
        Factory oldFactory = factoryMapper.selectOne(new Factory().setId(factory.getId()));
        //对比名称是否改变
        if( ! oldFactory.getName().equals(factory.getName())){
            //如果改变则需要修改游戏对象
            //获取所有游戏
            List<Game> games = gameMapper.select(new Game().setFactory(oldFactory.getName()));
            for (Game game : games) {
                game.setFactory(factory.getName());
                gameMapper.updateByPrimaryKeySelective(game);
            }
        }
        factoryMapper.updateByPrimaryKeySelective(factory);
    }
}
