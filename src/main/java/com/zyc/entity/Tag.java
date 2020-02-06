package com.zyc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.entity
 * @Description: 标签tag实体类
 * @author: Zeke
 * @date: 2020/1/4 15:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "tag")
public class Tag implements Serializable {

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
    @Column(name = "createDate")
    //创建时间
    private Date createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(gameName, tag.gameName) &&
                Objects.equals(userId, tag.userId) &&
                Objects.equals(createDate, tag.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName, userId, createDate);
    }
}
