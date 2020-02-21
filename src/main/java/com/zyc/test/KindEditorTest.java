package com.zyc.test;

import com.zyc.GameSpaceApplication;
import com.zyc.entity.Article;
import com.zyc.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.test
 * @Description: 类的描述
 * @author: Zeke
 * @date: 2020/1/2 10:14
 */

@SpringBootTest(classes = GameSpaceApplication.class)
@RunWith(value = SpringRunner.class)
public class KindEditorTest {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    HttpSession session;

    @Test
    public void test01(){
        Article article = articleMapper.selectOne(new Article().setId("bbf276f8-6e2b-4950-8938-dbb9cdfc12a6"));
        String regex;
        List<String> list = new ArrayList<>();
        //提取字符串中的img标签
        regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(article.getContent());
        while (ma.find())
        {
            //提取字符串中的src路径
            Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(ma.group());
            while(m.find())
            {
                list.add(m.group(1));
            }
        }
        list.forEach(e -> System.out.println(e));
    }

    @Test
    public void test02(){
        System.out.println(session.getServletContext().getRealPath(""));
        File f = new File(session.getServletContext().getRealPath("")+"/static/img/articleContentImg/bad1.png");
        boolean delete = f.delete();
        System.out.println(delete);
    }

}
