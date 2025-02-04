package com.study.jpa.chap02.repository;

import com.study.jpa.chap02.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void insertData() {
        Student s1 = Student.builder()
                .name("쿠로미")
                .city("청양군")
                .major("경제학")
                .build();

        Student s2 = Student.builder()
                .name("춘식이")
                .city("서울시")
                .major("컴퓨터공학")
                .build();

        Student s3 = Student.builder()
                .name("어피치")
                .city("제주도")
                .major("화학공학")
                .build();

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
    }

    @Test
    @DisplayName("이름이 춘식이인 학생의 모든 정보 조회")
    void test1() {
        //given
        String stu_name = "춘식이";
        //when
        List<Student> students = studentRepository.findByName(stu_name);
        //then
        Assertions.assertThat(students.size()).isEqualTo(1);
        Assertions.assertThat(students.get(0).getName()).isEqualTo(stu_name);
    }

    @Test
    @DisplayName("도시명과 전공명으로 조회")
    void findByCityAndMajorTest() {
        //given
        String city = "제주도";
        String major = "화학공학";
        // when
        List<Student> foundStudents = studentRepository.findByCityAndMajor(city, major);
        // then
        Assertions.assertThat(foundStudents.size()).isEqualTo(1);
        Assertions.assertThat(foundStudents.get(0).getCity()).isEqualTo(city);
    }

    @Test
    @DisplayName("전공에 공학이 포함된 학생들 조회")
    void majorContainingtest() {
        //given
        String major = "공학";
        // when
        List<Student> byMajorContaining = studentRepository.findByMajorContaining(major);
        //then
        byMajorContaining.forEach(System.out::println);
    }

    // JPQL
    @Test
    @DisplayName("jpql로 학생 조회하기")
    void test5() {
        //given
        String city = "서울시";
        // when
        List<Student> studentList = studentRepository.getStudentByCity(city);
        // then
        studentList.forEach(System.out::println);
    }

    // 순수 sql
    @Test
    @DisplayName("순수 sql로 학생정보 조회")
    void nativeSQLTest() {
        //given
        String name = "쿠로미";
        String city = "청양군";
        // when
        List<Student> studentByNameOrCity = studentRepository.getStudentByNameOrCity(name, city);
        // then
        studentByNameOrCity.forEach(System.out::println);
    }




}