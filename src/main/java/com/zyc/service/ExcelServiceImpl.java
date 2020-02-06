package com.zyc.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.zyc.entity.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: Excel操作service层实现类
 * @author: Zeke
 * @date: 2019/12/23 15:48
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private PictureService pictureService;

    @Override
    public void in(String value , MultipartFile file , HttpSession session){
        //获取文件存储路径
        String realPath = session.getServletContext().getRealPath("/static/excel");
        //获取文件名
        String filename = file.getOriginalFilename();
        //上传文件
        File newFile = new File(realPath + "/" + filename);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //轮播图导入
        if(value.equals("picture")){
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
            params.setTitleRows(1);
            List<Picture> list = ExcelImportUtil.importExcel(newFile, Picture.class, params);
            for (Picture picture : list) {
                picture.setId(UUID.randomUUID().toString());
                picture.setPicPath(picture.getPicPath().split("static")[1]);
                picture.setCreateDate(new Date());
                pictureService.add(picture);
            }
        }
        newFile.delete();
    }

    @Override
    //导出
    public void out(String value, HttpServletResponse response, HttpSession session) {
        //创建excel工作簿
        Workbook workbook = null;
        //判断要导出的数据种类
        //导出轮播图
        if(value.equals("picture")){
            List<Picture> pictures = pictureService.selectAll();
            for (Picture picture : pictures) {
                picture.setPicPath(session.getServletContext().getRealPath("/static/")+picture.getPicPath());
            }
            workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图详情","轮播图"),
                    Picture .class, pictures);
        }
        //设置输出类型
        response.setContentType("application/vnd.ms-excel");
        try {
            //设置请求头和打开方式
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(UUID.randomUUID().toString()+".xls", "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            //写入excel表格
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
