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
 * @Description: 文章article实体类
 * @author: Zeke
 * @date: 2019/12/26 10:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "article")
public class Article implements Serializable {

    @Id
    //文章id
    private String id;
    //文章名称
    private String name;
    @Column(name = "userId")
    //作者id
    private String userId;
    //作者名称
    private String author;
    @Column(name = "picPath")
    //文章封面
    private String picPath;
    //文章大体介绍
    private String presentation;
    //文章内容
    private String content;
    @Column(name = "contentPath")
    //文章路径
    private String contentPath;
    @Column(name = "updateDate")
    //修改日期
    private Date updateDate;
    @Column(name = "createDate")
    //发表日期
    private Date createDate;
    //状态
    private String state;
    @Column(name = "goodNum")
    //好评数
    private Integer goodNum;
    @Column(name = "badNum")
    //差评数
    private Integer badNum;
    @Column(name = "generalNum")
    //中立评论数
    private Integer generalNum;

    //后台分页时每页展示的个数
    @Transient
    public static int articlePage = 6;

}
