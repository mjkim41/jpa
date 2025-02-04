package com.study.jpa.chap01.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
//@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 1. 클래스 이름에 @Entity, @Table(name="DB 테이블명") 설정
//   - @Entity : DB랑 연결된 클래스이다.
//    - @Table(name="db 테이블명") : DB의 00 테이블과 연결된 클래스이다.
@Entity
@Table(name = "tbl_product")
public class Product {

// 기본 설정 :
//    create table tbl_product (
//            price integer not null,
//            id bigint not null,
//            name varchar(255),
//    primary key (id)
//    ) engine=InnoDB

    // 실무에서는 수동으로 테이블 만든 후에 @Column으로는 연결만 함

    // @ID : PK 설정
    // @Column(name = "", nullable = true/false, length = 30, unique = true, updatable = false) : DB 칼럼 속성
    // @GeneratedValue(strategy = GenerationType.IDENTITY) : AUTO_INCREMENT (oracle는 auto_increment 없으므로 sequence로)
    // 기본 설정 확인
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;

    @Column(name = "prod_nm", nullable = false, length = 30)
    private String name; // 상품명

    @Column(name = "prod_price", nullable = true)
    private int price; // 상품 가격

    // DEFAULT CURRENT_TIMESTAP ON UPDATE CURRENT_TIMESTAMP
    // @CreationTimestamp : insert시 자동으로 현재 시간 저장
    @Column(updatable = false) // 수정불가
    @CreationTimestamp
    private LocalDateTime createdAt; // 상품 등록 시간

    // @UpdateTimestamp : UPDATE문 실행 시 자동으로 시간 수정
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    // enum 등록 : 열거형 데이터는 따로 설정을 안하면 숫자로 저장함
    // -> @Enumerated(EnumType.STRING)
    @Enumerated(EnumType.STRING)
    private Category category;

    public enum Category {
        FOOD, FASHION, ELECTRONIC
    }

    // 수정 편의 메서드
    public void changeProduct(String newName, int newPrice, Category newCategory) {
        this.name = newName;
        this.price = newPrice;
        this.category = newCategory;
    }


}
