package com.study.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// QueryDSL를 쓰려면 Entity Manager를 주입 받은 JPAQueryFactory @bean을 생성해야 함
// 1. @Configuration 붙이기
@Configuration
public class QueryDslConfig {

    // 2. EntityManager 주입받기(fyi. @persistentContext : jpa 관련 빈 관리하는 컨테이너)
    @PersistenceContext
    private EntityManager em;

    // 3-1. 앱 실행 시 @bean 자동 호출하여 bean 생성됨
    @Bean
    public JPAQueryFactory factory() {
        // JPAQUERYFACTORY에 Entity Manger를 담아 반환
        return new JPAQueryFactory(em);
    }
}