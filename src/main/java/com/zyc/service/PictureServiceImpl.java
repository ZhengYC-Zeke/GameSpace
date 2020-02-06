package com.zyc.service;

import com.zyc.entity.Picture;
import com.zyc.mapper.PictureMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 轮播图picture类service层实现类
 * @author: Zeke
 * @date: 2019/12/23 10:06
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    /**
     * 后台
     */

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询所有
    public List<Picture> selectAll() {
        return pictureMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //分页查询所有
    public List<Picture> selectAll(Integer page) {
        //分页时每页展示7条数据
        RowBounds rowBounds = new RowBounds((page-1)*Picture.picturePage,Picture.picturePage);
        List<Picture> pictures = pictureMapper.selectByRowBounds(new Picture(), rowBounds);
        return pictures;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询所有的总数
    public int selectAllSum() {
        return pictureMapper.selectCount(new Picture());
    }

    @Override
    //添加
    public Map<String , Object> insert(Picture picture) {
        //创建map用于返回值
        Map<String , Object> map = new HashMap<>();
        //设置图片id
        picture.setId(UUID.randomUUID().toString());
        //判断上传的文件是否为空
        if (picture.getPicPath().equals("")) {
            //如果为空则不进行上传操作
            map.put("code", "error");
            picture.setPicPath("/img/game/null.jpg");
            //将图片设置为无图状态
            picture.setState("无图");
        } else {
            //如果不为空则返回200,前台进行图片上传操作
            map.put("code", "200");
            //将picpath设为空图片避免前台上传图片前报错
            picture.setPicPath("/img/game/loading.gif");
            //修改文件状态
            picture.setState("正常");
        }
        //将id放入map用于上传文件
        map.put("data",picture.getId());
        //设置创建日期为当前日期
        picture.setCreateDate(new Date());
        //执行添加方法
        pictureMapper.insert(picture);
        return map;
    }

    @Override
    //删除
    public void deleteById(String id , HttpSession session) {
       //获取要删除的对象
        Picture picture = pictureMapper.selectOne(new Picture().setId(id));
        //获取当前轮播图图片存储路径
        String picPath = session.getServletContext().getRealPath("/static") + picture.getPicPath();
        //如果存在图片则先删除文件
        if( ! picPath.equals("/img/game/null.jpg")){
            File file = new File(picPath);
            file.delete();
        }
        //删除数据库中对象
        pictureMapper.delete(picture);
    }

    @Override
    //修改
    public Map<String , Object> update(Picture picture , HttpSession session) {
        //创建map用于返回值
        Map<String , Object> map = new HashMap<>();
        //判断上传的文件是否为空
        if(picture.getPicPath().equals("")) {
            //如果为空则不进行上传操作
            map.put("code", "error");
            picture.setPicPath("/img/game/null.jpg");
            picture.setState("无图");
        }else {
            //如果不为空则返回200,前台进行图片上传操作
            map.put("code", "200");
            //删除原图片
            File file = new File(session.getServletContext().getRealPath("/static")+picture.getPicPath());
            file.delete();
            //将picpath设为空图片避免前台上传图片前报错
            picture.setPicPath("/img/game/loading.gif");
            //修改文件状态
            picture.setState("正常");
        }
        //将修改的id存入map
        map.put("data",picture.getId());
        //修改内容
        pictureMapper.updateByPrimaryKeySelective(picture);
        return map;
    }

    @Override
    public void upload(MultipartFile file, HttpSession session , String id) {
        //获取文件存储路径
        String realPath = session.getServletContext().getRealPath("/static");
        //获取文件名称
        String filename = file.getOriginalFilename();
        //获取存储在服务器端的文件路径
        String picPath = "/img/game"+filename;
        //创建文件
        File f = new File(realPath+picPath);
        try {
            //上传文件
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改文件路径
        Picture picture = new Picture();
        picture.setId(id);
        picture.setPicPath(picPath);
        pictureMapper.updateByPrimaryKeySelective(picture);
    }

    @Override
    //直接添加
    public void add(Picture picture) {
        pictureMapper.insert(picture);
    }

    /**
     * 前台
     */

    @Override
    //查询五张状态正常的轮播图
    public List<Picture> selectFivePictureWhereStateIsNormal() {
        return pictureMapper.selectFivePictureWhereStateIsNormal();
    }
}
