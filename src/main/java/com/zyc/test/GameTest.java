package com.zyc.test;

import com.zyc.GameSpaceApplication;
import com.zyc.entity.Game;
import com.zyc.entity.GameRedis;
import com.zyc.mapper.GameMapper;
import com.zyc.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.test
 * @Description: 类的描述
 * @author: Zeke
 * @date: 2020/2/1 12:52
 */
@SpringBootTest(classes = GameSpaceApplication.class)
@RunWith(SpringRunner.class)
public class GameTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    //添加游戏redis数据
    public void test02(){
        List<Game> games = gameService.selectAll();
        double a = 0;
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        for (Game game : games) {
            zSetOperations.add(GameRedis.GAME_GAME,game,++a/10);
        }
    }

    @Test
    public void test03(){
        List<Game> games = gameService.selectFiveByKind("多人在线");
        for (Game game : games) {
            System.out.println(game);
        }

    }

    @Test
    public void test04(){
        Map<String, Object> map = gameService.selectByKindAndFactoryAndScorePage("动作射击", "all", "4", 1);
        List<Game> games = (List<Game>) map.get("games");
        for (Game game : games) {
            System.out.println(game);
        }
    }

}
