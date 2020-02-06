package com.zyc.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Description: 轮播图实体类
 * @author: Zeke
 * @date: 2019/12/23 9:58
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "picture")
public class Picture implements Serializable {

    //id
    @Id
    @Excel(name = "轮播图ID")
    private String id;
    //图片路径picPath
    @Excel(name = "轮播图",type=2,width = 80 , height = 40,savePath="D:\\IDEA-workspace\\WebProject\\game_bbs\\src\\main\\webapp\\static\\img\\game")
    @Column(name = "picPath")
    private String picPath;
    //创建时间
    @Column(name = "createDate")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createDate;
    //状态
    @Excel(name = "状态")
    private String state;
    //游戏id
    @Excel(name = "所属游戏ID")
    @Column(name = "gameId")
    private String gameId;
    //名称
    @Excel(name = "名称")
    private String name;
    //介绍
    @Excel(name = "内容")
    private String content;

    //后台分页时每页展示的个数
    @Transient
    public static int picturePage = 7;

}
