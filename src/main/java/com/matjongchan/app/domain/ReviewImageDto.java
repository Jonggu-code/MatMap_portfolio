package com.matjongchan.app.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j  //log 편하게 사용
public class ReviewImageDto {
    private Integer id;
    private String name;
    private String img_url;
    private Integer order_number;
    private int fk_review_id;
    private int fk_restaurant_id;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return img_url;
    }

    public Integer getOrder_number() {
        return order_number;
    }

    public int getFk_review_id() {
        return fk_review_id;
    }

    public int getFk_restaurant_id() {
        return fk_restaurant_id;
    }

    public ReviewImageDto(Integer id, String name, String img_url, Integer order_number, int fk_review_id, int fk_restaurant_id) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.order_number = order_number;
        this.fk_review_id = fk_review_id;
        this.fk_restaurant_id = fk_restaurant_id;
    }
    public ReviewImageDto(String name, String img_url, int fk_review_id, int fk_restaurant_id) {
        this.name = name;
        this.img_url = img_url;
        this.fk_review_id = fk_review_id;
        this.fk_restaurant_id = fk_restaurant_id;
    }
}

