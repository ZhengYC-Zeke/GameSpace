package com.zyc.mapper;

import com.zyc.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.mapper
 * @Description: 评论comment类mapper层接口
 * @author: Zeke
 * @date: 2020/1/4 11:24
 */
@Repository
public interface CommentMapper extends Mapper<Comment> {

    //前台：根据游戏名称查询6条评论
    @Select("select * from comment where gameName = #{gameName} order by rand() limit 6")
    List<Comment> selectSixByGameName(@Param("gameName") String gameName);

}
