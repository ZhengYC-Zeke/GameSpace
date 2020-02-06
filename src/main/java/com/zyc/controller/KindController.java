package com.zyc.controller;

import com.zyc.entity.Kind;
import com.zyc.service.KindService;
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
 * @Description: 类别kind类视图跳转控制层
 * @author: Zeke
 * @date: 2019/12/23 16:45
 */

@Controller
@RequestMapping("kind")
public class KindController {

    @Autowired
    private KindService kindService;

    /**
     * 后台：jqgrid查询
     * @param page：页码
     * @return Map
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Map<String ,Object> findAll(Integer page){
        Map<String ,Object> map = new HashMap<>();
        //分页查询所有
        map.put("rows", kindService.selectAll(page));
        //放入当前页码
        map.put("page",page);
        //判断总页码
        map.put("total",kindService.selectAllSum()% Kind.kindPage == 0 ? kindService.selectAllSum()/Kind.kindPage:kindService.selectAllSum()/Kind.kindPage+1);
        return map;
    }

    /**
     * 后台：jqgrid修改
     * @param oper：修改参数，kind：修改对象
     * @return Map
     */
    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper , Kind kind){
        //添加
        if(oper.equals("add")){
            kindService.add(kind);
        }
        //删除
        else if(oper.equals("del")){
            kindService.delete(kind.getId());
        }
        //修改
        else if(oper.equals("edit")){
            kindService.update(kind);
        }
    }

    /**
     * 后台：ajax查询所有
     * @return List<Kind>
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Kind> selectAll(){
        List<Kind> kinds = kindService.selectAll();
        return kinds;
    }
}
