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
 * 식당 전체 리뷰수와 총 평점을 변경에 사용하는 객체
 */
public class TotalCount {

    private Integer restaurant_id;
    private Integer fk_restaurant;
    private Double total_score_count;
    private Double total_review_count;

}
