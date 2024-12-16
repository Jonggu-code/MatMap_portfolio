package com.matjongchan.app.service;

import com.matjongchan.app.domain.dto.S;
import com.matjongchan.app.domain.entity.OtherImageDto;

import java.util.List;

public interface OtherImageService {
    int countImage();

    int countRImage(int fk_review_id);

    int deleteAllImage();

    int insertImage(OtherImageDto dto);

    List<OtherImageDto> getRestaurantImg(int restaurant_id);

    List<OtherImageDto> getRestaurantImg2(S s);

    int deleteImage(int fk_review_id);
}
