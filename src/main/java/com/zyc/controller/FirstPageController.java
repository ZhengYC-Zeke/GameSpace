package com.zyc.controller;

import com.zyc.service.FirstPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: 前台进入首页视图跳转控制层
 * @author: Zeke
 * @date: 2020/2/1 11:52
 */

@Controller
@RequestMapping("firstPage")
public class FirstPageController {

    @Autowired
    private FirstPageService firstPageService;

    /**
     * 前台：进入首页
     * @param request：request对象用于存储数据
     * @return String：页面跳转
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){
        //获取数据集合
        Map<String, Object> map = firstPageService.comeFirstPage();
        request.setAttribute("map",map);
        //存入作用域
        return "static/jsp/main";
    }

}
