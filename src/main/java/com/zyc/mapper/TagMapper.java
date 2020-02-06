package com.zyc.mapper;

import com.zyc.entity.Tag;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.mapper
 * @Description: 标签tag类mapper层
 * @author: Zeke
 * @date: 2020/1/4 15:04
 */
@Repository
public interface TagMapper extends Mapper<Tag> {
}
