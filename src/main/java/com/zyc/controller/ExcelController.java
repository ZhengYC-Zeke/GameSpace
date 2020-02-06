package com.zyc.controller;

import com.zyc.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: Excel类控制层
 * @author: Zeke
 * @date: 2019/12/23 15:59
 */

@RestController
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    //导出
    @RequestMapping("out")
    public void out(String value, HttpServletResponse response, HttpSession session){
        excelService.out(value, response, session);
    }

    //导入
    @RequestMapping("in")
    public void in(String value , MultipartFile file , HttpSession session){
        excelService.in(value, file, session);
    }

}
