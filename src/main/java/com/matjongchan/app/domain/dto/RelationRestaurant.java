package com.matjongchan.app.domain.dto;


import com.matjongchan.app.domain.entity.OtherImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
    // 연관 가게로 뜨는 식당들에 사용할 객체
public class RelationRestaurant {

    private String restaurant_name; //식당이름
    private Double restaurant_total_score_count;   //식당 총 평점 평균 점수
    private Integer restaurant_total_review_count;  //식당 총 리뷰 갯수
    private String restaurant_address;      //c_address + d_address;
    private List<String> restaurant_image_url_list;  //해당 식당 이미지 2개

    @Builder
    public RelationRestaurant(String restaurant_name, Double restaurant_total_score_count, Integer restaurant_total_review_count, String restaurant_address, List<String> restaurant_image_url_list) {
        this.restaurant_name = restaurant_name;
        this.restaurant_total_score_count = restaurant_total_score_count;
        this.restaurant_total_review_count = restaurant_total_review_count;
        this.restaurant_address = restaurant_address;
        this.restaurant_image_url_list = restaurant_image_url_list;
    }
}
