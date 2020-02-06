package com.zyc.controller;

import com.zyc.entity.Comment;
import com.zyc.entity.Game;
import com.zyc.service.CommentService;
import com.zyc.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: 游戏game类视图跳转控制层
 * @author: Zeke
 * @date: 2019/12/24 16:35
 */

@RequestMapping("game")
@Controller
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private CommentService commentService;

    /**
     * 后台：jqgrid查询
     * @param page：页码，kindName：游戏所属类别名称
     * @return Map
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String ,Object> findAll(Integer page , String kindName){
        Map<String ,Object> map = new HashMap<>();
        //分页查询所有
        map.put("rows", gameService.selectAllByKindName(page ,kindName));
        //放入当前页码
        map.put("page",page);
        //判断总页码
        map.put("total",gameService.selectAllSum()% Game.gamePage == 0 ? gameService.selectAllSum()/Game.gamePage:gameService.selectAllSum()/Game.gamePage+1);
        return map;
    }

    /**
     * 后台：添加游戏
     * @param files：文件，session：服务器：session，game：添加的游戏对象
     */
    @RequestMapping("insert")
    @ResponseBody
    public void insert(MultipartFile[] files, HttpSession session, Game game){
        //添加游戏
        gameService.insert(files, session, game);
    }

    /**
     * 后台：全局修改游戏
     * @param files：文件，session：服务器：session，game：添加的游戏对象
     */
    @RequestMapping("update")
    @ResponseBody
    public void update(MultipartFile[] files, HttpSession session, Game game){
        gameService.update(files, session, game);
    }

    /**
     * 后台：根据id查找游戏
     * @param id：游戏id
     * @Return Game：游戏对象
     */
    @RequestMapping("selectById")
    @ResponseBody
    public Game selectById(String id){
        Game game = gameService.selectById(id);
        return game;
    }

    /**
     * 后台：jqgrid修改：删除
     * @param oper：操作属性，id：游戏对象id，session：HttpSession对象
     * @return Map
     */
    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper ,String id , HttpSession session){
        if(oper.equals("del")){
            gameService.delete(id,session);
        }
    }

    /**
     * 后台：ajax查询所有
     * @return List<Game>
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Game> selectAll(){
        List<Game> games = gameService.selectAll();
        return games;
    }

    /**
     * 后台：ajax根据类别查询
     * @param kindName：类别名称
     * @return List<Game>
     */
    @RequestMapping("selectByKindName")
    @ResponseBody
    public List<Game> selectByKindName(String kindName){
        List<Game> games = gameService.selectByKindName(kindName);
        return games;
    }

    /**
     * 前台：游戏详情查询
     * @param id：游戏Id，request：request对象用于存储数据
     * @return Game
     */
    @RequestMapping("selectGameById")
    public String selectGameById(String id , HttpServletRequest request){
        //查询游戏数据
        Game game = gameService.selectById(id);
        request.setAttribute("game",game);
        //创建存储评论和用户的map
//        Map<String , Object> map = new HashMap<>();
        //查询评论数据
        List<Comment> comments = commentService.selectSixByGameName(game.getName());
        request.setAttribute("comments",comments);
        //根据评论获取用户
//        for (Comment comment : comments) {
//
//        }
        return "static/jsp/show";
    }

}
