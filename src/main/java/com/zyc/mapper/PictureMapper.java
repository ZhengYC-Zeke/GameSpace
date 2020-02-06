package com.zyc.mapper;

import com.zyc.entity.Picture;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.mapper
 * @Description: 轮播图picture类mapper层
 * @author: Zeke
 * @date: 2019/12/23 10:02
 */

@Repository
public interface PictureMapper extends Mapper<Picture> {

    //查询五张状态正常的轮播图
    @Select("select * from picture where state = '正常' order by rand() limit 5")
    List<Picture> selectFivePictureWhereStateIsNormal();

}
