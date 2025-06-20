package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *菜品类别：(DishCategory)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DishCategory")
public class DishCategory implements Serializable {

    //DishCategory编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_category_id")
    private Integer dish_category_id;
    // 菜品类别
    @Basic
    private String dish_category;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
