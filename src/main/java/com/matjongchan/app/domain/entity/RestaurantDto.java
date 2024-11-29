package com.matjongchan.app.domain.entity;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    
    private Integer id;                     //식당 PK
    private String name;                    //식당이름
    private String c_address;               //도시주소 ex)강남구, 서초구
    private String d_address;               //상세주소 ex) 테헤란로28길 어쩌구저쩌꾸
    private String number;                  //전화번호
    private String reservation;             //예약가능여부 ex) 예약가능
    private String memo;                    //설명 (필요시 넣고 아님말고)
    private Double total_score_count;       //총점 평균점수
    private Integer total_review_count;     //총 리뷰 개수
    private Double loc_x;                   // 경도
    private Double loc_y;                   // 위도
    private String search_tag;              // 식당이름+도시주소+상세주소
    private Integer fk_category;            // 카테고리 id

    // 식당 정보 수정을 위한 메서드
    public void updateInfo(String name,String number, String reservation, String desc) {
        this.name = name;
        this.number = number;
        this.reservation = reservation;
        this.memo = desc;
    }

    @Builder
    public RestaurantDto(String name, String c_address, String d_address,
                         String number, String reservation, String memo, Double total_score_count,
                         Integer total_review_count, Double loc_x, Double loc_y, String search_tag, Integer fk_category) {
        this.name = name;
        this.c_address = c_address;
        this.d_address = d_address;
        this.number = number;
        this.reservation = reservation;
        this.memo = memo;
        this.total_score_count = total_score_count;
        this.total_review_count = total_review_count;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
        this.search_tag = search_tag;
        this.fk_category = fk_category;
    }

}
