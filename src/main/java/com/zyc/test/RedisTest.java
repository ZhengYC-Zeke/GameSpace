package com.zyc.test;

import com.zyc.GameBbsApplication;
import com.zyc.entity.GameRedis;
import com.zyc.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.test
 * @Description: 类的描述
 * @author: Zeke
 * @date: 2020/1/5 11:26
 */
@SpringBootTest(classes = GameBbsApplication.class)
@RunWith(value = SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    //添加标签redis数据
    public void test01(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        //先取出原有数据
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, "英雄联盟");
        if(tags == null) {
            tags = new ArrayList<>();
            hashOperations.put(GameRedis.GAME_TAG, "英雄联盟", tags);
        }
        for (int x = 0 ; x <=20 ; x++ ) {
            Tag tag = new Tag();
            tag.setId(UUID.randomUUID().toString()).setGameName("英雄联盟").setContent("测试数据"+x);
            tags.add(tag);
        }
        hashOperations.put(GameRedis.GAME_TAG,"英雄联盟" , tags);
    }

    @Test
    //取出标签redis数据
    public void test02(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, "英雄联盟");
        tags.forEach(e -> System.out.println(e));
    }

    @Test
    public void test03(){
        //获取redis操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //获取数据
        List<Tag> tags = (List<Tag>) hashOperations.get(GameRedis.GAME_TAG, "aaa");
        System.out.println(tags);
    }

}
