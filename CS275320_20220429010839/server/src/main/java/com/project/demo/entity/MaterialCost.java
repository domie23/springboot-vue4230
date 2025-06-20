package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *材料成本：(MaterialCost)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MaterialCost")
public class MaterialCost implements Serializable {

    //MaterialCost编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_cost_id")
    private Integer material_cost_id;
    // 材料编号
    @Basic
    private String material_number;
    // 材料名称
    @Basic
    private String material_name;
    // 材料单价
    @Basic
    private String unit_price_of_materials;
    // 计算单位
    @Basic
    private String unit_of_calculation;
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
