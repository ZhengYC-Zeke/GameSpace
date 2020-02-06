package com.zyc.mapper;

import com.zyc.entity.Article;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * @ProjectName: WebProject
 * @Package com.zyc.mapper
 * @Description: 文章article类的mapper层接口
 * @author: Zeke
 * @date: 2019/12/26 14:18
 */

@Repository
public interface ArticleMapper extends Mapper<Article> {

}
