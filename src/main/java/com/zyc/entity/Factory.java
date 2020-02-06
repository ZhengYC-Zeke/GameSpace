package com.zyc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.entity
 * @Description: 游戏厂商实体类
 * @author: Zeke
 * @date: 2019/12/25 9:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "factory")
public class Factory implements Serializable {

    //id
    @Id
    private String id;
    //厂商名
    private String name;
    //游戏数量
    @Column(name = "gameNum")
    private Integer gameNum;
    //好评数量
    @Column(name = "goodCommentsNum")
    private Integer goodCommentsNum;
    //差评数量
    @Column(name = "badCommentsNum")
    private Integer badCommentsNum;
    //评分
    private Double score;

    //后台分页时每页展示的个数
    @Transient
    public static int factoryPage = 10;

}
