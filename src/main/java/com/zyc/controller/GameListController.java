package com.zyc.controller;

import com.zyc.service.GameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: gamelist视图跳转控制层
 * @author: Zeke
 * @date: 2020/2/2 13:24
 */
@Controller
@RequestMapping("gamelist")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    /**
     * 前台：按钮跳转
     * @param request：request对象用于存储数据，buttonName：点击的按钮名称
     * @return String：页面跳转
     */
    @RequestMapping("buttonClick")
    public String choiceness(HttpServletRequest request , String buttonName){
        //获取数据
        Map<String, Object> map = gameListService.comeToGameList(buttonName);
        //将数据存入作用域中
        request.setAttribute("map",map);
        return "static/jsp/gamelist";
    }

    /**
     * 前台：探险家页面跳转
     * @param request：request对象用于存储数据
     * @return String：页面跳转
     */
    @RequestMapping("explorer")
    public String explorer(HttpServletRequest request){
        //获取数据
        Map<String, Object> map = gameListService.comeToExplorer();
        //将数据存入作用域中
        request.setAttribute("map",map);
        return "static/jsp/explorer";
    }

    /**
     * 前台：分类浏览页面跳转
     * @param  kind：当前选择类别
     *               factory：当前选择厂商
     *               score：当前选择评分
     *               page：当前页码
     *               request：request对象用于存储数据
     * @return String：页面跳转
     */
    @RequestMapping("kindShow")
    public String kindShow(String kind , String factory , String score , Integer page , HttpServletRequest request){
        //获取数据
        Map<String, Object> map = gameListService.comeToKindList(kind , factory , score , page);
        //存储数据
        request.setAttribute("map",map);
        return "static/jsp/kind";
    }


}
