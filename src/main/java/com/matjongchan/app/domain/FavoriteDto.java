package com.matjongchan.app.domain;

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
public class FavoriteDto {
    private Integer id;                 // 즐겨찾기 ID
    private int fk_member_id;         // 회원 ID (fk_member_id)
    private int fk_restaurant_id;     // 식당 ID (fk_restaurant_id)

    @Override
    public String toString() {
        return "FavoriteDto{" +
                "id=" + id +
                ", fk_member_id=" + fk_member_id +
                ", fk_restaurant_id=" + fk_restaurant_id +
                '}';
    }
}

