package com.study.jpa.chap05.controller;

import com.study.jpa.chap05.controller.dto.GroupAverageResponse;
import com.study.jpa.chap05.dto.IdolResponse;
import com.study.jpa.chap05.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/idols")
@Slf4j
@RequiredArgsConstructor
public class IdolController {

    private final IdolService idolService;

    // 아이돌 정보 전체 조회
    @GetMapping
    public ResponseEntity<?> findIdols() {
        List<IdolResponse> idols = idolService.getIdols();
        return ResponseEntity.ok().body(idols);
    }

    // # 그룹별 평균 나이 조회 요청
    @GetMapping("/averageAge")
    public ResponseEntity<?> averageAge() {
        return ResponseEntity.ok()
                .body(idolService.average());
    }

}
