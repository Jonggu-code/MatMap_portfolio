package com.matjongchan.app.domain.dto;

import com.matjongchan.app.domain.entity.BusinessHoursDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
    // 식당 상세 페이지에서 사용할 객체
public class RestaurantDetail {

    private String restaurant_name; //식당 이름
    private String restaurant_category; //식당 카테고리
    private Double restaurant_total_score_count;    //식당 총 평점 평균
    private Integer restaurant_total_review_count;  //식당 총 리뷰 갯수
    private String restaurant_address;  //식당 주소 c_address + d_address
    private String restaurant_number;   //식당 번호
    private String restaurant_reservation;  //식당 예약유무
    private String restaurant_memo; //식당 소개?
    private List<String> restaurant_image_url_list; //식당 이미지 경로 리스트...

    private String today_business_state;    //현재 영업유무
    private BusinessHoursDto business_hours_dto; //식당 영업시간 전체

    private List<MenuDetail> menu_detail_list;

    private List<String> review_image_url_list; //리뷰에 있는 이미지 경로 리스트

    //연관 가게에 대한 정보들을 담은 리스트. 3개정도 담을예정. Category가 같은거 위주
    private List<RelationRestaurant> relation_restaurant_list;

    private Double restaurant_total_taste_score_count;  //식당리뷰에서 총 맛 점수 평균
    private Double restaurant_total_clean_score_count;  //식당리뷰에서 총 청결 점수 평균
    private Double restaurant_total_kind_score_count;  //식당리뷰에서 총 친절도 점수 평균
    private TotalRating restaurant_total_rating;    //식당 만족도 조사

    @Builder
    public RestaurantDetail(String restaurant_name, String restaurant_category, Double restaurant_total_score_count, Integer restaurant_total_review_count, String restaurant_address, String restaurant_number, String restaurant_reservation, String restaurant_memo, List<String> restaurant_image_url_list, String today_business_state, BusinessHoursDto business_hours_dto, List<MenuDetail> menu_detail_list, List<String> review_image_url_list, List<RelationRestaurant> relation_restaurant_list, Double restaurant_total_taste_score_count, Double restaurant_total_clean_score_count, Double restaurant_total_kind_score_count, TotalRating restaurant_total_rating) {
        this.restaurant_name = restaurant_name;
        this.restaurant_category = restaurant_category;
        this.restaurant_total_score_count = restaurant_total_score_count;
        this.restaurant_total_review_count = restaurant_total_review_count;
        this.restaurant_address = restaurant_address;
        this.restaurant_number = restaurant_number;
        this.restaurant_reservation = restaurant_reservation;
        this.restaurant_memo = restaurant_memo;
        this.restaurant_image_url_list = restaurant_image_url_list;
        this.today_business_state = today_business_state;
        this.business_hours_dto = business_hours_dto;
        this.menu_detail_list = menu_detail_list;
        this.review_image_url_list = review_image_url_list;
        this.relation_restaurant_list = relation_restaurant_list;
        this.restaurant_total_taste_score_count = restaurant_total_taste_score_count;
        this.restaurant_total_clean_score_count = restaurant_total_clean_score_count;
        this.restaurant_total_kind_score_count = restaurant_total_kind_score_count;
        this.restaurant_total_rating = restaurant_total_rating;
    }
}
