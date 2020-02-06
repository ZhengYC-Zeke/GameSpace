package com.zyc.mapper;

import com.zyc.entity.Factory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.mapper
 * @Description: 厂商factory类mapper层接口
 * @author: Zeke
 * @date: 2019/12/25 9:30
 */
@Repository
public interface FactoryMapper extends Mapper<Factory> {
}
