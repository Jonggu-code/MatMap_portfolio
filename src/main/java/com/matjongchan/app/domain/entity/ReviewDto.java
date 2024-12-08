package com.matjongchan.app.domain.entity;


import com.matjongchan.app.domain.dto.ReviewerDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@AllArgsConstructor //모든 필드를 포함하는 생성자를 추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j  //log 편하게 사용
public class ReviewDto {
    private Integer  id;
    private String reviewer;
    private String title;
    private String content;
    private double taste_score;
    private double clean_score;
    private double kind_score;
    private double total_score;
    private String create_at;
    private Integer fk_restaurant_id;
    private String restaurantName;  // 마이페이지에서 회원이 작성한 리뷰 관련해서, review table의 title, content에 더해, restaurant table의 name도 가져와야해서 컬럼 추가함.
    private List<String> menuNames;
    private List<String> otherImages;

    private ReviewerDto reviewerDto;

    public ReviewDto(String reviewer, String title, String content, double taste_score, double clean_score, double kind_score, double total_score, Integer fk_restaurant_id) {
        this.reviewer = reviewer;
        this.title = title;
        this.content = content;
        this.taste_score = taste_score;
        this.clean_score = clean_score;
        this.kind_score = kind_score;
        this.total_score = total_score;
        this.fk_restaurant_id = fk_restaurant_id;
    }
}
