package com.matjongchan.app.domain.dto;


import com.matjongchan.app.domain.entity.OtherImageDto;
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
    private Boolean restaurant_total_score_count;   //식당 총 평점 평균 점수
    private Integer restaurant_total_review_count;  //식당 총 리뷰 갯수
    private String restaurant_address;      //c_address + d_address;
    private List<String> restaurant_image_url_list;  //해당 식당 이미지 2개

}
