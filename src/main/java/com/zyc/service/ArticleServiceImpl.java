package com.zyc.service;

import com.zyc.entity.Article;
import com.zyc.mapper.ArticleMapper;
import com.zyc.util.RegularUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 文章article类的service层实现类
 * @author: Zeke
 * @date: 2019/12/27 9:17
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //分页查询所有
    public List<Article> selectAll(int page) {
        Example example = new Example(Article.class);
        //设置查询数据按照时间排序
        example.orderBy("createDate").desc();
        RowBounds rowBounds = new RowBounds((page-1)*Article.articlePage,Article.articlePage);
        List<Article> articles = articleMapper.selectByExampleAndRowBounds(example,rowBounds);
        return articles;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    //查询总数据量
    public int selectAllSum() {
        return articleMapper.selectCount(new Article());
    }

    @Override
    //添加
    public void insert(Article article , MultipartFile file , HttpSession session) {
        //设置图片id
        article.setId(UUID.randomUUID().toString());
        //获取内容文件存储路径
        String contentPath = session.getServletContext().getRealPath("/static/articleContent");
        //创建新文件用于存储内容
        File newFile = new File(contentPath + "/" + article.getId()+".html");
        try( PrintWriter pw = new PrintWriter(newFile.getAbsolutePath())) {
            //写入文件头
            pw.println("<!DOCTYPE html>\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "</head>");
            //将内容写入文件
            pw.print(article.getContent());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        article.setContentPath("/static/articleContent/"+article.getId()+".html");
        if(! file.getOriginalFilename().equals("") ) {
            this.upload(file,session,article);
        }else {
            //没有图片则设置为无图
            article.setPicPath("/article/null.jpg");
        }
        //设置创建日期为当前日期
        article.setCreateDate(new Date());
        //设置文章状态
        article.setState("待审核");
        //执行添加方法
        articleMapper.insert(article);
    }

    @Override
    //删除
    public void delete(String id, HttpSession session) {
        //根据id获取对象
        Article article = articleMapper.selectOne(new Article().setId(id));
        //获取内容文件存储路径
        String contentPath = session.getServletContext().getRealPath("/static/articleContent");
        //创建新文件用于存储内容
        File newFile = new File(contentPath + "/" + id+".html");
        //删除内容文件
        newFile.delete();
        //获取封面文件存储路径
        String realPath = session.getServletContext().getRealPath("/static/img");
        //删除封面文件
        if (! article.getPicPath().equals("/article/null.jpg")) {
            File file = new File(realPath + article.getPicPath());
            file.delete();
        }
        //获取文章中所有图片的路径
        List<String> srcs = RegularUtil.getSrc(article.getContent());
        //删除图片文件
        for (String src : srcs) {
            System.out.println(src);
            File f = new File(session.getServletContext().getRealPath("")+src.split("/game")[1]);
            f.delete();
        }
        //删除数据库中对象
        articleMapper.delete(article);
    }

    @Override
    //文章内容,封面修改
    public void update(HttpSession session, MultipartFile file, Article article) {
        //获取老文章封面
        String oldPicPath = articleMapper.selectOne(new Article().setId(article.getId())).getPicPath();
        //获取内容文件存储路径
        String contentPath = session.getServletContext().getRealPath("/static/articleContent");
        //创建新文件用于存储内容
        File newFile = new File(contentPath + "/" + article.getId()+".html");
        try( PrintWriter pw = new PrintWriter(newFile.getAbsolutePath())) {
            //写入文件头
            pw.println("<!DOCTYPE html>\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "</head>");
            //将内容写入文件
            pw.print(article.getContent());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //判断是否修改文章封面
        if(! file.getOriginalFilename().equals("") ) {
            //删除原封面
            File f=new File(session.getServletContext().getRealPath("/static/img")+oldPicPath);
            f.delete();
            //上传新封面
            this.upload(file,session,article);
        }
        //设置修改日期
        article.setUpdateDate(new Date());
        //执行修改方法
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    //文章jQgrid修改
    public void updateByJqGrid(Article article) {
        //设置修改日期
        article.setUpdateDate(new Date());
        //执行修改方法
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    //文件上传
    public void upload(MultipartFile file, HttpSession session, Article article) {
        //获取文件存储路径
        String realPath = session.getServletContext().getRealPath("/static/img");
        //获取文件名称
        String filename = file.getOriginalFilename();
        //获取存储在服务器端的文件路径
        String picPath = "/article/"+filename;
        //创建文件
        File f = new File(realPath+picPath);
        //判断文件是否存在
        //文件不存在则上传
        if (!f.exists()) {
            //将文件存入文件夹下
            try {
                file.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //修改文件路径
        article.setPicPath(picPath);
        articleMapper.updateByPrimaryKeySelective(article);
    }
}
