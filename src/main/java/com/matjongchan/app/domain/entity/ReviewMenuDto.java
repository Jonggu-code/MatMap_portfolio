package com.matjongchan.app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewMenuDto {
    private Integer id;
    private Integer fk_review_id;
    private Integer fk_menu_id;

    public ReviewMenuDto(Integer fk_review_id, Integer fk_menu_id) {
        this.fk_review_id = fk_review_id;
        this.fk_menu_id = fk_menu_id;
    }
}
