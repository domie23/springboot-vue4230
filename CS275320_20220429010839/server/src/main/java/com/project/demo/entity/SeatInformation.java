package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *座位信息：(SeatInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SeatInformation")
public class SeatInformation implements Serializable {

    //SeatInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_information_id")
    private Integer seat_information_id;
    // 座位号
    @Basic
    private String seat_number;
    // 座位所在
    @Basic
    private String seat_location;
    // 容纳人数
    @Basic
    private String capacity;
    // 座位状态
    @Basic
    private String seat_status;
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
