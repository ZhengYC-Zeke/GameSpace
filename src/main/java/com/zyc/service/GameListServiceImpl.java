package com.zyc.service;

import com.zyc.entity.Game;
import com.zyc.entity.GameAttribute;
import com.zyc.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 进入首gemelist页面service层实现类
 * @author: Zeke
 * @date: 2020/2/2 13:13
 */
@Service
public class GameListServiceImpl implements GameListService {

    @Autowired
    private GameService gameService;

    @Override
    //点击进入gamelist相关页面
    public Map<String, Object> comeToGameList(String buttonName) {
        //创建返回值map集合
        Map<String, Object> map = new HashMap<>();
        /**
         * 点击精选按钮
         */
        if(buttonName.equals(GameAttribute.CHOICENESS_TITLE)){
            //获取精选数据
            List<Game> games = gameService.selectTwelveByRandom();
            //添加进map中
            map.put("games",games);
            map.put("title","每日精选");
            map.put("content", GameAttribute.CHOICENESS_CONTENT);
        }
        /**
         * 点击最近更新按钮
         */
        else if(buttonName.equals(GameAttribute.UPDATE_TITLE)){
            //获取最近更新数据
            List<Game> games = gameService.selectTwelveByUpdateDate();
            //添加进map中
            map.put("games",games);
            map.put("title","最近更新");
            map.put("content", GameAttribute.UPDATE_CONTENT);
        }
        /**
         * 点击时下热门按钮
         */
        else if(buttonName.equals(GameAttribute.NOWHOT_TITLE)){
            //获取时下热门数据
            Set<Game> games = gameService.selectTwelveByRedisScore();
            //添加进map中
            map.put("games",games);
            map.put("title","时下热门");
            map.put("content", GameAttribute.NOWHOT_CONTENT);
        }
        /**
         * 点击最多关注按钮
         */
        else if(buttonName.equals(GameAttribute.MOREATTENTION_TITLE)){
            //获取最多关注数据
            List<Game> games = gameService.selectTwelveByAttentionNum();
            //添加进map中
            map.put("games",games);
            map.put("title","最多关注");
            map.put("content", GameAttribute.MOREATTENTION_CONTENT);
        }
        /**
         * 点击即将推出按钮
         */
        else if(buttonName.equals(GameAttribute.COMESOON_TITLE)){
            //获取即将推出数据
            List<Game> games = gameService.selectTwelveWhereStateIsDevelopment();
            //添加进map中
            map.put("games",games);
            map.put("title","即将推出");
            map.put("content", GameAttribute.COMESOON_CONTENT);
        }
        /**
         * 点击抢先测试按钮
         */
        else if(buttonName.equals(GameAttribute.FIRSTTEST_TITLE)){
            //获取抢先测试数据
            List<Game> games = gameService.selectTwelveWhereStateIsTest();
            //添加进map中
            map.put("games",games);
            map.put("title","抢先测试");
            map.put("content", GameAttribute.FIRSTTEST_CONTENT);
        }
        return map;
    }

    @Override
    //点击进入探险家页面
    public Map<String , Object> comeToExplorer(){
        //创建set集合存储查询结果
        Set<Game> set = new HashSet<>();
        //创建返回值map集合
        Map<String, Object> map = new HashMap<>();
        //获取用户最喜欢的类别
        List<String> favoriteKind = Arrays.asList("动作射击","多人在线");
        List<String> likeKind = Arrays.asList("角色扮演","独立游戏","体育赛车","模拟经营");
        //搜索游戏
        for (String kind : favoriteKind) {
            List<Game> games = gameService.selectFiveByKind(kind);
            set.addAll(games);
        }
        for (String kind : likeKind) {
            List<Game> games = gameService.selectFiveByKind(kind);
            set.addAll(games);
        }
        //将结果添加进map中
        map.put("games",set);
        map.put("favoriteKind",favoriteKind);
        map.put("likeKind",likeKind);
        return map;
    }

    @Override
    //点击进入分类浏览页面
    public Map<String, Object> comeToKindList(String kind , String factory , String score, Integer page) {
        //根据类别和页面查询数据
        Map<String , Object> map = gameService.selectByKindAndFactoryAndScorePage(kind, factory, score, page);
        //查询数据总数
        int sum = gameService.selectSumByKindAndFactoryDateAndScore(kind, factory, score);
        //计算总页码
        sum = sum% Page.gamePage==0 ? sum/Page.gamePage : sum/Page.gamePage+1;
        map.put("sum",sum);
        return map;
    }
}
