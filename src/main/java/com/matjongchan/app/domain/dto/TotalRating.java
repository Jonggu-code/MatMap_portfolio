package com.matjongchan.app.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 식당에 총평점 낸 사람 수 모아둔 객체
 * 상세페이지에서 사용
 */
public class TotalRating {

    private Integer first;  // 총 평점에서 1점 준 사람 수
    private Integer second;  // 총 평점에서 2점 준 사람 수
    private Integer third;  // 총 평점에서 3점 준 사람 수
    private Integer fourth;  // 총 평점에서 4점 준 사람 수
    private Integer fifth;  // 총 평점에서 5점 준 사람 수

}
