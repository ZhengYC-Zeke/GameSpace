package com.zyc.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: easypoi操作 service层
 * @author: Zeke
 * @date: 2019/12/23 15:47
 */
public interface ExcelService {

    //导入
    void in(String value , MultipartFile file , HttpSession session);

    //导出
    void out(String value , HttpServletResponse response , HttpSession session);

}
