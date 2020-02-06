package com.zyc.service;

import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 进入gamelist页面service层接口
 * @author: Zeke
 * @date: 2020/2/2 13:09
 */
public interface GameListService {

    //点击进入gamelist相关页面
    Map<String , Object> comeToGameList(String buttonName);
    //点击进入探险家页面
    Map<String , Object> comeToExplorer();
    //点击进入分类浏览页面
    Map<String , Object> comeToKindList(String kind , String factory , String score , Integer page);

}
