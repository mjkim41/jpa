package com.study.jpa.chap03.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
// @ToString 시 제외하기
@ToString(exclude = {"department"})
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

    // 다태일 테이블에서 '다'에 해당하는 eNTITY에 특별히 해줘야 되는 작업!
    // 1. field에 1:N에서 1에 해당하는 Entity를 통째로 넣기
    // 2. @ManyToOne(fetch = FetchType.LAZY/EAGER)
    // - LAZY : FK로 연결된 테이블의 내용의 경우, 그 테이블의 내용을 사용할 때만 join 에서 가져옴
    // - EAGERLY : 항상 조인
    @ManyToOne(fetch = FetchType.LAZY)
    // 3. @JoinColumn(name = " FK의 DB상 이름 ")
    @JoinColumn(name = "dept_id") // FK 컬럼명
    private Department department;


}
