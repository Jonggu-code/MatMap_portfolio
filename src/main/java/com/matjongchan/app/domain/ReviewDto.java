package com.matjongchan.app.domain;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@AllArgsConstructor //모든 필드를 포함하는 생성자를 추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j  //log 편하게 사용
public class ReviewDto {

    private Integer  id;
    private String title;
    private String content;
    private Integer taste_score;
    private Integer clean_score;
    private Integer kind_score;
    private String create_at;
    private Integer fk_member_id;
    private Integer Fk_restaurant_id;
    private Integer Fk_image_id;
}
