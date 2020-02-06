package com.zyc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.entity
 * @Description: 分页相关实体类
 * @author: Zeke
 * @date: 2020/2/4 17:49
 */

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Page {

    //游戏分页：每页展示数据=12
    public static int gamePage = 12;
    //游戏分页：总页码
    public static int gameSumPage;

}
