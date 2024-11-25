package com.matjongchan.app.domain.entity;

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
public class MemberReviewsDto {
    private Integer id; // 고유번호
    private Integer fk_member_id; // member 테이블 연결 외래키
    private Integer fk_review_id; // review 테이블 연결 외래키


    @Override
    public String toString() {
        return "MemberReviewsDto{" +
                "id=" + id +
                ", fk_member_id=" + fk_member_id +
                ", fk_review_id=" + fk_review_id +
                '}';
    }
}
