package com.zyc.controller;

import com.zyc.service.KindEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.controller
 * @Description: 富文本编辑器KindEditor控制层
 * @author: Zeke
 * @date: 2019/12/27 9:38
 */

@Controller
@RequestMapping("kindEditor")
public class KindEditorController {

    @Autowired
    private KindEditorService kindEditorService;

    @RequestMapping("upload")
    @ResponseBody
    public Map<String , Object> upload(MultipartFile imgFile , HttpSession session){
        return kindEditorService.upload(imgFile, session);
    }

}
