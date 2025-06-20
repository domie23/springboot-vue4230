package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *结账管理：(SettlementManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SettlementManagement")
public class SettlementManagement implements Serializable {

    //SettlementManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_management_id")
    private Integer settlement_management_id;
    // 订单号
    @Basic
    private String order_number;
    // 座位号
    @Basic
    private String seat_number;
    // 开台时间
    @Basic
    private Timestamp opening_time;
    // 实收金额
    @Basic
    private Integer paid_in_amount;
    // 茶位费
    @Basic
    private Integer tea_fee;
    // 消费金额
    @Basic
    private Integer consumption_amount;
    // 找零金额
    @Basic
    private String change_amount;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
