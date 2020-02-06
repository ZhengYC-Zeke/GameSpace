package com.zyc.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 富文本编辑器KindEditor service层实现类
 * @author: Zeke
 * @date: 2019/12/27 11:15
 */
@Service
public class KindEditorServiceImpl implements KindEditorService {

    @Override
    //上传文章内容图片
    public Map<String, Object> upload(MultipartFile imgFile, HttpSession session) {
        //创建map用于返回值
        Map<String , Object> map = new HashMap<>();
        //获取文件名
        String filename = imgFile.getOriginalFilename();
        //获取文件存储路径
        String realPath = session.getServletContext().getRealPath("/static/img/articleContentImg");
        //创建要上传的文件
        File f = new File(realPath + "/" + filename);
        //文件不存在则上传
        if ( ! f.exists()) {
            //将文件存入文件夹下
            try {
                imgFile.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("error",0);
        map.put("url","http://localhost:8081/game/static/img/articleContentImg/"+filename);
        return map;
    }
}
