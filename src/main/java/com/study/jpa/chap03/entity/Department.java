package com.study.jpa.chap03.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(exclude = {"employees"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder


    /*
      [ N:1 테이블 중 1에 해당하는 테이블에서 해야 할 일 ]
      FYI. 아래 작업 하는 목적 : JPA에서 자동으로 테이블을 조인해서, 1테이블을 검색하면 N 테이블에 대한 정보도 함께 조회할 수 있도록 하기 위함임
      1-1. 필드로 List<1테이블> 을 가져온다.
        - 예시) 사원, 부서 테이블인 경우 : 부서 테이블에서 List<사원 Entity>를 필드로 가져온다.
      1-2. List<1테이블에> 아래 어노테이션 태그를 붙여준다.
           @OneToMany(
            mappedBy = "1 Entity에서 N Entity를 가져온 필드명"
            cascade = CascadeType.ALL (부서 테이블에서 List<사원>을 수정하더라도, 실제로 DB에서 사원이 수정된다.)
            orphanRemoval = true (예를 들어서, 부서 테이블에서 영업팀이 삭제 되면, 영업팀에 속하는 사원들도 함께 사라진다.)
            )
       2. 1 Entity에서 N Entity의 값을 삭제/추가하면 -> 1 Entity에도 해당 변경이 반영되게 하고 싶음
           -> 1 Entity에 아래 '// 2.'와 같이 편의 메소드 만들어 준다.
     */
@Entity
@Table(name = "tbl_dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id; // 부서번호

    @Column(name = "dept_name", nullable = false)
    private String name; // 부서명

    /*
      1. 필드로 List<1테이블> 을 가져온다.
        - 예시) 사원, 부서 테이블인 경우 : 부서 테이블에서 List<사원 Entity>를 필드로 가져온다.
      2. @OneToMany(
            mappedBy = "1 Entity에서 N Entity를 가져온 필드명"
            cascade = CascadeType.ALL (부서 테이블에서 List<사원>을 수정하더라도, 실제로 DB에서 사원이 수정된다.)
            orphanRemoval = true (예를 들어서, 부서 테이블에서 영업팀이 삭제 되면, 영업팀에 속하는 사원들도 함께 사라진다.)
            )
     */
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();


    // 2. 리스트에 사원 추가 편의 메서드
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setDepartment(this);
    }


    // 리스트에 사원 제거 편의 메서드
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.setDepartment(null);
    }



}