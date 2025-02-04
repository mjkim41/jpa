package com.study.jpa.chap02.repository;

import com.study.jpa.chap02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    // 쿼리 메서드 만들기 (메서드의 특별한 이름 규칙을 사용하여 JPQL 생성

    // 단일 조회하고 있으면 Optional<Student>
    List<Student> findByName(String studentName);

    // 두 개 이상의 필드
    List<Student> findByCityAndMajor(String city, String major);

    // where major like '%major%' : Containing
    List<Student> findByMajorContaining(String major);

    // where major like 'major%' : StringWith
    List<Student> findByMajorStartingWith(String major);

    // where major like '%major' : EndingWith
    List<Student> findByMajorEndingWith(String major);

    // where age <= ?
//    List<Student> findByAgeLessThanEqual(int age);



    // JPQL 사용하기 :
    // 1. @Query annotation 붙이기 / 테이블 이름이 아니라 entity 기준으로 어노테이션 안 내용이 작성됨 / ?!는 첫번째 parameter 라는 뜻(city)
    // 도시 이름으로 학생정보 조회
    @Query("SELECT st FROM Student st WHERE st.city = ?1")
    List<Student> getStudentByCity(String city);



    // 순수 SQL  : @Query(value="", nativeQuery=True),  단, 이때 parameter는 똑같이 '?1, ?2' 사용
    // 이름 또는 도시로 학생정보 조회
    @Query(value= """
            SELECT *
            FROM tbl_student
            WHERE stu_name = ?1
                OR city = ?2
            """, nativeQuery=true)
    List<Student> getStudentByNameOrCity(String studentName, String city);

}
