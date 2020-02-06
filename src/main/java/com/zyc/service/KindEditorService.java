package com.zyc.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 富文本编辑器kindEditor service层接口
 * @author: Zeke
 * @date: 2019/12/27 11:14
 */
public interface KindEditorService {

    //上传文件
    Map<String , Object> upload(MultipartFile imgFile , HttpSession session);

}
