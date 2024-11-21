package com.matjongchan.app.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@AllArgsConstructor //모든 필드를 포함하는 생성자를 추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j  //log 편하게 사용
public class ReviewImageDto {
    private int id;
    private String name;
    private String url;
    private int orderNo;
    private int fk_review_id;
    private int fk_restaurant_id;
}
