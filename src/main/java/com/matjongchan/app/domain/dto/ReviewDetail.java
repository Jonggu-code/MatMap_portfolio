package com.matjongchan.app.domain.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
    //리뷰 자세히 보기
    // 식당 상세페이지에서 사용
public class ReviewDetail {

    private Integer id;
    private String reviewer;
    private String title;
    private String content;
    private Double taste_score;
    private Double clean_score;
    private Double kind_score;
    private Double total_score;
    private String create_at;
    private String gender;
    private Integer age;
    private String image_url_list;    //해당 리뷰에 관련된 이미지 리스트

    @Builder
    public ReviewDetail(Integer id, String reviewer, String title, String content, Double taste_score, Double clean_score, Double kind_score, Double total_score, String create_at, String gender, Integer age, String image_url_list) {
        this.id = id;
        this.reviewer = reviewer;
        this.title = title;
        this.content = content;
        this.taste_score = taste_score;
        this.clean_score = clean_score;
        this.kind_score = kind_score;
        this.total_score = total_score;
        this.create_at = create_at;
        this.gender = gender;
        this.age = age;
        this.image_url_list = image_url_list;
    }
}
