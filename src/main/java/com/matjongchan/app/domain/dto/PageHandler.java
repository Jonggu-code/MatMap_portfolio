package com.matjongchan.app.domain.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@AllArgsConstructor //모든 필드를 포함하는 생성자를 추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j  //log 편하게 사용
public class PageHandler {
    private int totalItems;       // 총 리뷰 개수
    private int itemsPerPage;     // 한 페이지에 표시할 리뷰 개수 (3개)
    private int currentPage;      // 현재 페이지

    public PageHandler(int totalItems,  int currentPage) {
        this.totalItems = totalItems;
        this.itemsPerPage = 3;
        this.currentPage = currentPage;
    }


}
