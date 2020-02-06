package com.zyc.controller;

import com.zyc.entity.Comment;
import com.zyc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: 评论comment类视图跳转控制层
 * @author: Zeke
 * @date: 2019/12/23 16:45
 */

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 后台：jqgrid查询
     * @param page：页码，gameName：所属游戏
     * @return Map
     */
    @RequestMapping("findAllByGame")
    @ResponseBody
    public Map<String ,Object> findAllByGame(Integer page , String gameName){
        Map<String ,Object> map = new HashMap<>();
        //分页查询所有
        map.put("rows", commentService.selectAllByGame(gameName , page));
        //放入当前页码
        map.put("page",page);
        //判断总页码
        map.put("total",commentService.selectAllSum(gameName)% Comment.commentPage == 0 ? commentService.selectAllSum(gameName)/Comment.commentPage:commentService.selectAllSum(gameName)/Comment.commentPage+1);
        return map;
    }

    /**
     * 后台：jqgrid修改
     * @param oper：修改参数，comment：修改对象，gameName：所属游戏名
     * @return Map
     */
    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper , Comment comment , String gameName){
        //添加
        if(oper.equals("add")){
            commentService.insert(comment , gameName);
        }
        //删除
        else if(oper.equals("del")){
            commentService.delete(comment.getId());
        }
        //修改
        else if(oper.equals("edit")){
            commentService.update(comment);
        }
    }

    /**
     * 后台：查询所有待审核的评论
     * @param page：页码
     * @return Map
     */
    @RequestMapping("selectStateIsCheck")
    @ResponseBody
    public Map<String ,Object> selectStateIsCheck(Integer page){
        Map<String ,Object> map = new HashMap<>();
        //分页查询所有
        map.put("rows", commentService.selectStateIsCheck(page));
        //放入当前页码
        map.put("page",page);
        //判断总页码
        map.put("total",commentService.selectStateIsCheckSum()% Comment.commentPage == 0 ? commentService.selectStateIsCheckSum()/Comment.commentPage:commentService.selectStateIsCheckSum()/Comment.commentPage+1);
        return map;
    }

    /**
     * 后台：ajax评论审核通过
     * @param id：评论id
     */
    @RequestMapping("cPass")
    @ResponseBody
    public void cPass(String id){
        commentService.update(new Comment().setId(id).setState("正常"));
    }

    /**
     * 后台：ajax评论审核未通过
     * @param id：评论id，result：未通过原因，userId：用户Id
     */
    @RequestMapping("cUnPass")
    @ResponseBody
    public void cUnPass(String id , String result , String userId){
        System.out.println(result);
        commentService.update(new Comment().setId(id).setState("违规"));
        //发送消息告知用户原因
    }

}
