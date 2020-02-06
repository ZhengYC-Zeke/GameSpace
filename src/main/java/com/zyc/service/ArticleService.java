package com.zyc.service;

import com.zyc.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 文章article类的service层接口
 * @author: Zeke
 * @date: 2019/12/26 16:35
 */
public interface ArticleService {

    //分页查询所有
    List<Article> selectAll(int page);
    //查询总数据量
    int selectAllSum();
    //添加
    void insert(Article article , MultipartFile file , HttpSession session);
    //删除
    void delete(String id , HttpSession session);
    //文章内容,封面修改
    void update(HttpSession session, MultipartFile file, Article article);
    //文章jQgrid修改
    void updateByJqGrid(Article article);
    //文件上传
    void upload(MultipartFile file , HttpSession session , Article article);

}
