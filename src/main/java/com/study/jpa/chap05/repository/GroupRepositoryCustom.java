package com.study.jpa.chap05.repository;

import com.study.jpa.chap05.controller.dto.GroupAverageResponse;

import java.util.List;

// 1. querydsl, jdbc와 결합하기 위한 추가 인터페이스 만들기
// 2. 이름 반드시 Custom 붙이고, 일반 repository 에 extends 해야 함
// 3. 구현 클래스 만들기(반드시 이름 끝에 impl 들어가야 함) . Group Repoistory Custom 을 상속해야 함
public interface GroupRepositoryCustom {

    // 그룹별 평균나이 조회 - queryDSL로 조회
    List<com.study.jpa.chap05.dto.GroupAverageResponse> groupAverage();
}
