package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *统计报表：(StatisticalReport)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StatisticalReport")
public class StatisticalReport implements Serializable {

    //StatisticalReport编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistical_report_id")
    private Integer statistical_report_id;
    // 统计名称
    @Basic
    private String statistical_name;
    // 年份
    @Basic
    private String particular_year;
    // 月份
    @Basic
    private String month;
    // 日期
    @Basic
    private String date;
    // 统计数量
    @Basic
    private Integer statistical_quantity;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
