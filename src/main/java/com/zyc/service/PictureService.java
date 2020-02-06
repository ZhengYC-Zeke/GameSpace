package com.zyc.service;

import com.zyc.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 轮播图picture类service层
 * @author: Zeke
 * @date: 2019/12/23 10:03
 */
public interface PictureService {

    /**
     * 后台
     */
    //查询所有
    List<Picture> selectAll();
    //分页查询所有
    List<Picture> selectAll(Integer page);
    //查询总数量
    int selectAllSum();
    //添加
    Map<String , Object> insert(Picture picture);
    //根据id删除
    void deleteById(String id , HttpSession session);
    //修改
    Map<String , Object> update(Picture picture , HttpSession session);
    //文件上传
    void upload(MultipartFile file , HttpSession session ,String id);
    //直接添加
    void add(Picture picture);

    /**
     * 前台
     */
    //查询五张状态正常的轮播图
    List<Picture> selectFivePictureWhereStateIsNormal();

}
