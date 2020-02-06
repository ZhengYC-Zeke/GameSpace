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
import java.util.Date;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.entity
 * @Description: 评论实体类
 * @author: Zeke
 * @date: 2020/1/4 11:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    private String id;
    //评论内容
    private String content;
    @Column(name = "gameName")
    //所属游戏
    private String gameName;
    @Column(name = "userId")
    //所属用户Id
    private String userId;
    //状态
    private String state;
    //评论种类(好评,差评)
    private String kind;
    @Column(name = "createDate")
    //创建时间
    private Date createDate;
    @Column(name = "updateDate")
    //修改时间
    private Date updateDate;
    @Column(name = "goodNum")
    //好评数量
    private Integer goodNum;
    @Column(name = "badNum")
    //差评数量
    private Integer badNum;
    @Column(name = "generalNum")
    //中立评论数量
    private Integer generalNum;

    //后台分页时每页展示的个数
    @Transient
    public static int commentPage = 10;

}
