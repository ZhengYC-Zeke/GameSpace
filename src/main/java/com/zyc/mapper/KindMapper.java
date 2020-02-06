package com.zyc.mapper;

import com.zyc.entity.Kind;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.mapper
 * @Description: 类别kind类mapper层接口
 * @author: Zeke
 * @date: 2019/12/23 16:38
 */

@Repository
public interface KindMapper extends Mapper<Kind> {
}
