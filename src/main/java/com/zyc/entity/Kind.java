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
 * @Description: 类别实体类
 * @author: Zeke
 * @date: 2019/12/23 16:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "kind")
public class Kind implements Serializable {

    //id
    @Id
    private String id;
    //类别名称
    @Column(name = "name")
    private String name;
    //游戏数量
    @Column(name = "gameNum")
    private Integer gameNum;
    //喜欢人数
    @Column(name = "likeNum")
    private Integer likeNum;

    //后台分页时每页展示的个数
    @Transient
    public static int kindPage = 10;

}
