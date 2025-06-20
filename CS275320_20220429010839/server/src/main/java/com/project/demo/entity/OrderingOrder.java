package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *点餐订单：(OrderingOrder)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OrderingOrder")
public class OrderingOrder implements Serializable {

    //OrderingOrder编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordering_order_id")
    private Integer ordering_order_id;
    // 订单号
    @Basic
    private String order_number;
    // 座位号
    @Basic
    private String seat_number;
    // 菜品名称
    @Basic
    private String dish_name;
    // 菜品数量
    @Basic
    private String quantity_of_dishes;
    // 菜品价格
    @Basic
    private Integer dish_price;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
