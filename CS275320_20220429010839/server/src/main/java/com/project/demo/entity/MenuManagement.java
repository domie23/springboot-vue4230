package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *菜谱管理：(MenuManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MenuManagement")
public class MenuManagement implements Serializable {

    //MenuManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_management_id")
    private Integer menu_management_id;
    // 菜品编号
    @Basic
    private String dish_no;
    // 菜品信息
    @Basic
    private String dish_information;
    // 菜品类别
    @Basic
    private String dish_category;
    // 备注
    @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
