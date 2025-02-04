package com.study.jpa.chap01.repository;

import com.study.jpa.chap01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속 받으면 CrudRepository 에서 save 등 기본 CRUD를 제공함
// 이 때, 첫번쩨 제너릭 타입에는 엔터티 클래스 타입, 두번째는 ID의 type을 받음

// public interface CrudRepository<T, ID> extends Repository<T, ID> {

// 1. JpaRepository 상속 받기 (기본 crud 제공)
// 2. JpaRepository의 제네릭 타입 지정해주기 (entity 클래스 타입, ID type)
//    - JpaRepository를 타고 들어가보면, ListCrudRepository -> CrudRepository 클래스를 상속받고,
//      CrudRepository 클래스의 내장 메소드를 보면 첫번째는 entity, 두번째는 id의 데이터 타입임을 확인 가능
//
//       <S extends T> S save(S entity);
//    Optional<T> findById(ID id);
public interface ProductRepository extends JpaRepository<Product, Long> {

}
