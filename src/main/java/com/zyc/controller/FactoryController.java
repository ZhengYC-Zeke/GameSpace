package com.zyc.controller;

import com.zyc.entity.Factory;
import com.zyc.service.FactoryService;
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
 * @Description: 厂商factory类视图跳转控制层
 * @author: Zeke
 * @date: 2019/12/25 9:40
 */
@Controller
@RequestMapping("factory")
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

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
        map.put("rows", factoryService.selectAll(page));
        //放入当前页码
        map.put("page",page);
        //判断总页码
        map.put("total",factoryService.selectAllSum()% Factory.factoryPage == 0 ? factoryService.selectAllSum()/Factory.factoryPage:factoryService.selectAllSum()/Factory.factoryPage+1);
        return map;
    }

    /**
     * 后台：jqgrid修改
     * @param oper：修改参数，factory：修改对象
     * @return Map
     */
    @RequestMapping("edit")
    @ResponseBody
    public void edit(String oper , Factory factory){
        //添加
        if(oper.equals("add")){
            factoryService.insert(factory);
        }
        //删除
        else if(oper.equals("del")){
            factoryService.delete(factory.getId());
        }
        //修改
        else if(oper.equals("edit")){
            factoryService.update(factory);
        }
    }

    /**
     * 后台：ajax查询所有
     * @return List<Factory>
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Factory> selectAll(){
        List<Factory> factories = factoryService.selectAll();
        return factories;
    }

}
