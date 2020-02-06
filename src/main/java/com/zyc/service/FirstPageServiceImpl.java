package com.zyc.service;

import com.zyc.entity.Game;
import com.zyc.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 进入首页firstPage service层实现类
 * @author: Zeke
 * @date: 2020/2/1 11:57
 */
@Service
public class FirstPageServiceImpl implements FirstPageService {

    @Autowired
    //注入游戏service
    private GameService gameService;
    @Autowired
    //注入轮播图service
    private PictureService pictureService;
    @Autowired
    //注入类别service
    private KindService kindService;
    @Autowired
    //注入文章service
    private ArticleService articleService;

    //进入首页
    @Override
    public Map<String, Object> comeFirstPage() {
        //获取五张首页轮播图
        List<Picture> pictures = pictureService.selectFivePictureWhereStateIsNormal();
        //根据轮播图查询游戏详情
        Map<Picture , Game> pictureGameMap = new HashMap<>();
        for (Picture picture : pictures) {
            //查询游戏
            Game game = gameService.selectByName(picture.getName());
            pictureGameMap.put(picture , game);
        }
        //获取特别推荐数据
        List<Game> particularlyRecommended = gameService.selectThreeByRandom();
        //获取实时热搜榜游戏
        Set<Game> hotGames = gameService.selectByRedisScore();
        //将set转化为list
        List<Game> hotGamesList = new ArrayList<>(hotGames);
        //获取最高评分游戏
        List<Game> scoreGames = gameService.selectByScore();
        //获取最近更新游戏
        List<Game> dateGames = gameService.selectByCreateDate();
        //获取最多好评游戏
        List<Game> goodConmmentsGames = gameService.selectByGoodConmmentsNum();
        //创建map用于返回值
        Map<String , Object> map = new HashMap<>();
        //将数据添加到返回集合中
        map.put("pictureGameMap",pictureGameMap);
        map.put("particularlyRecommended",particularlyRecommended);
        map.put("hotGames",hotGamesList);
        map.put("scoreGames",scoreGames);
        map.put("dateGames",dateGames);
        map.put("goodConmmentsGames",goodConmmentsGames);
        return map;
    }
}
