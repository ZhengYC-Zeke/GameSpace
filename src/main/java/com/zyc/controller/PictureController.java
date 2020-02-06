package com.zyc.controller;

import com.zyc.entity.Picture;
import com.zyc.service.PictureService;
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
 * @Description: 轮播图picture类视图跳转控制层
 * @author: Zeke
 * @date: 2019/12/23 14:02
 */
@Controller
@RequestMapping("picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

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
        map.put("rows", pictureService.selectAll(page));
        //放入当前页码
        map.put("page",page);
        //判断总页码
        map.put("total",pictureService.selectAllSum()% Picture.picturePage == 0 ? pictureService.selectAllSum()/Picture.picturePage:pictureService.selectAllSum()/Picture.picturePage+1);
        return map;
    }

    /**
     * 后台：jqgrid修改
     * @param oper：修改参数，picture：修改对象，session：用于文件删除
     * @return Map
     */
    @RequestMapping("edit")
    @ResponseBody
    public Map<String , Object> edit(String oper , Picture picture , HttpSession session){
        //创建返回值map
        Map<String,Object> map = new HashMap<>();
        //添加
        if(oper.equals("add")){
            map = pictureService.insert(picture);
        }
        //删除
        else if(oper.equals("del")){
            pictureService.deleteById(picture.getId(),session);
        }
        //修改
        else if(oper.equals("edit")){
            map = pictureService.update(picture,session);
        }
        return map;
    }

    /**
     * 后台：jqgrid文件上传
     * @param picPath：上传的文件，id：要修改对象的id，session：用于文件上传
     */
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile picPath , String id , HttpSession session){
        pictureService.upload(picPath,session,id);
    }

}
