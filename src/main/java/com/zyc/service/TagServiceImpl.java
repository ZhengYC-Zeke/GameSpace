package com.zyc.service;

import com.zyc.entity.GameRedis;
import com.zyc.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 标签tag类service层实现类
 * @author: Zeke
 * @date: 2020/1/5 9:51
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TagServiceImpl implements TagService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询所有
    public List<Tag> selectAllByGame(String gameName) {
        if(gameName.equals("该类别下暂无游戏"))
            return null;
        //获取redis操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //获取数据
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, gameName);
        return tags;
    }

    @Override
    //后台添加
    public void insert(Tag tag , String gameName) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        //先取出原有数据
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, gameName);
        //判断是否存在标签库
        if(tags == null) {
            tags = new ArrayList<>();
            hashOperations.put(GameRedis.GAME_TAG, gameName, tags);
        }
        //判断是否存满
        if(tags.size() >= 20){
            //存满则清空一半数据
            tags = tags.subList(0,9);
        }
        hashOperations.put(GameRedis.GAME_TAG,gameName , tags);
    }

    @Override
    //后台修改
    public void update(Tag tag , String gameName) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        //先取出原有数据
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, gameName);
        //移除数据
        tags.remove(tag);
        //添加修改后的数据
        tags.add(tag);
        hashOperations.put(GameRedis.GAME_TAG,gameName , tags);
    }

    @Override
    //后台删除
    public void delete(Tag tag , String gameName) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        //先取出原有数据
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, gameName);
        //移除数据
        tags.remove(tag);
        hashOperations.put(GameRedis.GAME_TAG,gameName , tags);
    }

    @Override
    //后台删除当前所选的游戏的标签
    public void clearByGameName(String gameName) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(GameRedis.GAME_TAG,gameName);
    }

    @Override
    //后台清空所有
    public void clearAll() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(GameRedis.GAME_TAG);
    }

    @Override
    //前台查询三条标签数据
    public List<Tag> selectThreeByGame(String gameName) {
        //获取redis操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //获取数据
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, gameName);
        //空值判断
        if(tags == null)
            return null;
        else
            return tags.subList(0,3);
    }
}
