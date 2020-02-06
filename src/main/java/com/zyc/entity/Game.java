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
 * @Description: 游戏实体类
 * @author: Zeke
 * @date: 2019/12/24 9:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "game")
public class Game implements Serializable {

    //id
    @Id
    private String id;
    //游戏名称
    private String name;
    //游戏类别
    private String kind;
    //游戏厂商
    private String factory;
    //游戏简介
    private String introduce;
    //游戏评分
    private Double score;
    //游戏状态
    private String state;
    //关注人数
    @Column(name = "attentionNum")
    private Integer attentionNum;
    //window支持
    private Integer windows;
    //mac支持
    private Integer mac;
    //视频路径
    @Column(name = "vedioPath")
    private String vedioPath;
    //游戏详情页图片路径
    @Column(name = "showGamePath")
    private String showGamePath;
    //特别推荐页面图片路径
    @Column(name = "recommendedGamePath")
    private String recommendedGamePath;
    //首页按钮展示页面图片
    @Column(name = "listGamePath")
    private String listGamePath;
    //游戏截图1
    @Column(name = "cutGamePath1")
    private String cutGamePath1;
    //游戏截图2
    @Column(name = "cutGamePath2")
    private String cutGamePath2;
    //上架时间
    @Column(name = "createDate")
    private Date createDate;
    //更新时间
    @Column(name = "updateDate")
    private Date updateDate;
    //好评数量
    @Column(name = "goodCommentsNum")
    private Integer goodCommentsNum;
    //差评数量
    @Column(name = "badCommentsNum")
    private Integer badCommentsNum;

    //后台分页时每页展示的个数
    @Transient
    public static int gamePage = 5;

}
