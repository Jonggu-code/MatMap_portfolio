package com.matjongchan.app.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@AllArgsConstructor //모든 필드를 포함하는 생성자를 추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j

public class FavoriteWithRestaurantDto {
    private Integer id;                // favorite 테이블의 ID
    private Integer fk_member_id;      // favorite 테이블의 회원 ID
    @Getter
    private Integer fk_restaurant_id;  // favorite 테이블의 레스토랑 ID
    private String restaurant_name;    // restaurant 테이블의 이름 (r.name)
    private String c_address;          // restaurant 테이블의 시/군/구 주소 (r.c_address)
    private String d_address;          // restaurant 테이블의 상세 주소 (r.d_address)

//     추가함.
    private String number;
    private String reservation;
    private String memo;
    private String total_score_count;
    private String total_review_count;
    private String search_tag;


    @Override
    public String toString() {
        return "FavoriteWithRestaurantDto{" +
                "id=" + id +
                ", fk_member_id=" + fk_member_id +
                ", fk_restaurant_id=" + fk_restaurant_id +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", c_address='" + c_address + '\'' +
                ", d_address='" + d_address + '\'' +
                ", number='" + number + '\'' +
                ", reservation='" + reservation + '\'' +
                ", memo='" + memo + '\'' +
                ", total_score_count='" + total_score_count + '\'' +
                ", total_review_count='" + total_review_count + '\'' +
                ", search_tag='" + search_tag + '\'' +
                '}';
    }
}