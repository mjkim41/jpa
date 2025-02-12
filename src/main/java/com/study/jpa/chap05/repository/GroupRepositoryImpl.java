package com.study.jpa.chap05.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.jpa.chap05.dto.GroupAverageResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.study.jpa.chap05.entity.QIdol.idol;

// 3. Group Repoistory Custom 을 상속해야 함
@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepositoryCustom {

    // 3-1. querydsl를 사용해게 해주는 객체 주입받아야 함
    private final JPAQueryFactory queryFactory;

    // projection 가져오기
    @Override
    public List<GroupAverageResponse> groupAverage() {
        return queryFactory
                .select(idol.group.groupName, idol.age.avg())
                .from(idol)
                .groupBy(idol.group)
                .fetch()
                .stream()
                .map(GroupAverageResponse::from)
                .collect(Collectors.toList())
                ;

    }
}
