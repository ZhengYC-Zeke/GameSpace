package com.zyc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.util
 * @Description: 正则表达式工具类
 * @author: Zeke
 * @date: 2020/1/2 10:36
 */
public class RegularUtil {

    //获取html文档中img中的src路径
    public static List<String> getSrc(String content){
        //正则表达式
        String regex;
        //创建返回值集合存储src路径
        List<String> list = new ArrayList<>();
        //提取字符串中的img标签
        regex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        Matcher ma = pa.matcher(content);
        while (ma.find())
        {
            //提取字符串中的src路径
            Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(ma.group());
            while(m.find())
            {
                list.add(m.group(1));
            }
        }
        return list;
    }

}
