package com.matjongchan.app.domain.dto;


import com.matjongchan.app.domain.entity.BusinessHoursDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
/**
 * 메인화면에서 표시될 식당들의 정보를 담을 객체
 */
public class SimpleRestaurant {

    private Integer id;     //식당 pk
    private String name;    //식당이름
    private String image_url;   //식당 첫번째 사진
    private String address;     //식당 주소. d_address + c_address;
    private Double total_score_count;       //식당 총점 평균점수
    private Integer total_review_count;     //식당 총 리뷰 개수
    private String today_business_state;    //현재 영업유무
    private BusinessHoursDto business_hours_dto; //식당 영업시간 전체
    private String reservation;     //예약여부
    private String number;          //전화번호
    private Double loc_x;           //경도
    private Double loc_y;           //위도

    private List<String> menu_name_list;        //식당 메뉴 리스트
    private ReviewDto recentSimpleReview;  //그 식당 최근 리뷰 하나

    @Builder
    public SimpleRestaurant(Integer id, String name, String image_url, String address, Double total_score_count, Integer total_review_count, String today_business_state, BusinessHoursDto business_hours_dto, String reservation, String number, Double loc_x, Double loc_y, List<String> menu_name_list, ReviewDto recentSimpleReview) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.address = address;
        this.total_score_count = total_score_count;
        this.total_review_count = total_review_count;
        this.today_business_state = today_business_state;
        this.business_hours_dto = business_hours_dto;
        this.reservation = reservation;
        this.number = number;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
        this.menu_name_list = menu_name_list;
        this.recentSimpleReview = recentSimpleReview;
    }
}
