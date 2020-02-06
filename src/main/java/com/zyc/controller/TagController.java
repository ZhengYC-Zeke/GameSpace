package com.zyc.controller;

import com.zyc.entity.Tag;
import com.zyc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: 标签tag类视图跳转控制层
 * @author: Zeke
 * @date: 2020/1/5 12:31
 */
@Controller
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 后台：jqgrid查询
     * @param gameName：所属游戏
     * @return Map
     */
    @RequestMapping("findAllByGame")
    @ResponseBody
    public Map<String ,Object> findAllByGame(String gameName){
        Map<String ,Object> map = new HashMap<>();
        //查询所有
        map.put("rows", tagService.selectAllByGame(gameName));
        return map;
    }

    /**
     * 后台：jqgrid修改
     * @param oper：修改参数，tag：修改对象，gameName：所属游戏名
     * @return Map
     */
    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper , Tag tag , String gameName){
        //添加
        if(oper.equals("add")){
            tagService.insert(tag , gameName);
        }
        //删除
        else if(oper.equals("del")){
            tagService.delete(tag , gameName);
        }
        //修改
        else if(oper.equals("edit")){
            tagService.update(tag, gameName);
        }
    }

    /**
     * 后台：删除当前所选游戏的所有标签
     * @param gameName：所属游戏名
     */
    @RequestMapping("clearByGameName")
    @ResponseBody
    public void clearByGameName(String gameName){
        tagService.clearByGameName(gameName);
    }

    /**
     * 后台：删除所有标签
     */
    @RequestMapping("clearAll")
    @ResponseBody
    public void clearAll(){
        tagService.clearAll();
    }

    /**
     * 前台：ajax根据游戏查询三条数据
     * @param gameName：所属游戏
     * @return List<Tag>
     */
    @RequestMapping("findThreeByGame")
    @ResponseBody
    public List<Tag> findThreeByGame(String gameName){
        //查询三条数据所有
        List<Tag> tags = tagService.selectThreeByGame(gameName);
        return tags;
    }

}
