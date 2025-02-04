package com.study.jpa.chap03.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
//@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name", nullable = false)
    private String name; // 사원명

    // DB 아닌 Java의 패러다임을 가져와서, FK만 가져오는 게 아니라 Entity 자체를 가져옴
    // Employee 가 Department에 대해서 Many
    @ManyToOne
    @JoinColumn(name = "dept_id") // FK 컬럼명
    private Department department;

    @Override
    public String toString() {
        return "Employee [id=" + id+ ", name=" + name + ", department=" + department + "]";
    }


}
