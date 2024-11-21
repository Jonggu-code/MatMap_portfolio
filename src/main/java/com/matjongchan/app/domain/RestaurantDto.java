package com.matjongchan.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    
    private Integer id;
    private String name;        //식당이름
    private String c_address;       //도시주소 ex)강남구, 서초구 
    private String d_address;       //상세주소 ex) 테헤란로28길 어쩌구저쩌꾸
    private String number;          //전화번호
    private String reservation;     //예약가능여부 ex) 예약가능
    private String desc;            //설명 (필요시 넣고 아님말고)
    private Double total_score_count;       //총점 평균점수
    private Integer total_review_count;     //총 리뷰 개수
    private String loc_x;                // 경도
    private String loc_y;                // 위도
    private String search_tag;          // 식당이름+도시주소+상세주소
    private Integer fK_category_id;     // 카테고리 id

    @Builder
    public RestaurantDto(String name, String c_address, String d_address,
                         String number, String reservation, String desc, Double total_score_count,
                         Integer total_review_count, String loc_x, String loc_y, String search_tag) {
        this.name = name;
        this.c_address = c_address;
        this.d_address = d_address;
        this.number = number;
        this.reservation = reservation;
        this.desc = desc;
        this.total_score_count = total_score_count;
        this.total_review_count = total_review_count;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
        this.search_tag = search_tag;
    }
}
