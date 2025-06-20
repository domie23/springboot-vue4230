package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *开台信息：(OpeningInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OpeningInformation")
public class OpeningInformation implements Serializable {

    //OpeningInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opening_information_id")
    private Integer opening_information_id;
    // 订单号
    @Basic
    private String order_number;
    // 座位号
    @Basic
    private String seat_number;
    // 开台时间
    @Basic
    private Timestamp opening_time;
    // 人数
    @Basic
    private String number_of_people;
    // 茶位费
    @Basic
    private Integer tea_fee;
    // 消费金额
    @Basic
    private Integer consumption_amount;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
