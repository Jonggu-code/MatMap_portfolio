package com.matjongchan.app.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 식당에 총평점 낸 사람 수 모아둔 객체
 * 상세페이지에서 사용
 */
public class TotalRating {

    private Integer first = 0 ;  // 총 평점에서 1점 준 사람 수
    private Integer second = 0;  // 총 평점에서 2점 준 사람 수
    private Integer third = 0;  // 총 평점에서 3점 준 사람 수
    private Integer fourth = 0;  // 총 평점에서 4점 준 사람 수
    private Integer fifth = 0;  // 총 평점에서 5점 준 사람 수

    public void plusFirst(){
        first++;
    }
    public void plusSecond(){
        second++;
    }
    public void plusThird(){
        third++;
    }
    public void plusFourth(){
        fourth++;
    }
    public void plusFifth(){
        fifth++;
    }

    public static TotalRating listToTotalRating(List<Double> totalScoreCountList){
        TotalRating totalRating = new TotalRating();

        for (Double v : totalScoreCountList) {
            if(v > 4.0){
                totalRating.plusFifth();
            }else if(v > 3.0){
                totalRating.plusFourth();
            }else if(v > 2.0){
                totalRating.plusThird();
            } else if (v > 1.0) {
                totalRating.plusSecond();
            }else{
                totalRating.plusFirst();
            }
        }
        return totalRating;
    }
}
