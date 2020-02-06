package com.zyc.mapper;

import com.zyc.entity.Game;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.mapper
 * @Description: 游戏game类mapper层接口
 * @author: Zeke
 * @date: 2019/12/24 9:15
 */
@Repository
public interface GameMapper extends Mapper<Game> {

    //特别推荐：随机查询3条数据
    @Select("select * from game order by rand() limit 3")
    List<Game> selectThreeByRandom();

    //精选：随机查询12条数据
    @Select("select * from game order by rand() limit 12")
    List<Game> selectTwelveByRandom();

    //探险家：根据类别随机查询5条数据
    @Select("select * from game where kind = #{kind} order by rand() limit 5")
    List<Game> selectFiveByKind(@Param("kind") String kind);

}
