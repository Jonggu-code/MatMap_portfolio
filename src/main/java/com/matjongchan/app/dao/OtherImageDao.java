package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.OtherImageDto;

import java.util.List;

public interface OtherImageDao {
    int count();

    int countR(int fk_review_id);

    int deleteAll();

    int insert(OtherImageDto dto);

    /**
     * 해당 식당 pk로 해당 식당의 이미지 전체 조회
     * @param restaurant_id
     * @return
     */
    List<OtherImageDto> getRestaurantImages(int restaurant_id);

    List<OtherImageDto> getRestaurantImages2(int restaurant_id);

}
