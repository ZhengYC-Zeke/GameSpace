package com.zyc.controller;

import com.zyc.entity.Article;
import com.zyc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: 文章article类视图跳转控制层
 * @author: Zeke
 * @date: 2019/12/27 9:28
 */

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 后台：jqgrid查询
     * @param page：页码
     * @return Map
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String , Object> findAll(int page){
        //创建返回值map
        Map<String,Object> map = new HashMap<>();
        //分页查询所有
        map.put("rows", articleService.selectAll(page));
        //放入当前页码
        map.put("page",page);
        //判断总页码
        map.put("total",articleService.selectAllSum()% Article.articlePage == 0 ?articleService.selectAllSum()/Article.articlePage:articleService.selectAllSum()/Article.articlePage+1);
        return map;
    }

    /**
     * 后台：添加文章
     * @param file：文件，session：服务器：session，article：添加的文章对象
     */
    @RequestMapping("insert")
    @ResponseBody
    public void insert(HttpSession session, MultipartFile file, Article article){
        articleService.insert(article , file , session);
    }

    /**
     * 后台：修改文章
     * @param file：文件，session：服务器：session，article：添加的文章对象
     */
    @RequestMapping("update")
    @ResponseBody
    public void update(HttpSession session, MultipartFile file, Article article){
        articleService.update(session, file, article);
    }

    /**
     * 后台：jqgrid修改
     * @param oper：修改参数，article：修改对象
     * @return Map
     */
    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper , Article article , HttpSession session){
        //删除
        if(oper.equals("del")){
            articleService.delete(article.getId() , session);
        }
        //修改
        else if(oper.equals("edit")){
            articleService.updateByJqGrid(article);
        }
    }

}
