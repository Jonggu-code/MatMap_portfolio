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
    private String reviewer;
    private String title;
    private String content;
    private double taste_score;
    private double clean_score;
    private double kind_score;
    private double total_score;
    private String create_at;
    private Integer fk_restaurant_id;

    public Integer getId() {
        return id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public double getTaste_score() {
        return taste_score;
    }

    public double getClean_score() {
        return clean_score;
    }

    public double getKind_score() {
        return kind_score;
    }

    public double getTotal_score() {
        return total_score;
    }

    public String getCreate_at() {
        return create_at;
    }


    public Integer getFk_restaurant_id() {
        return fk_restaurant_id;
    }


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
