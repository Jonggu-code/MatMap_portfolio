package com.matjongchan.app.service;

import com.matjongchan.app.domain.ReviewDto;

import java.util.List;

public interface ReviewService {
    int getAllCount();

    int getCountR(int fk_restaurant_id);

    // 쓰기
    int write(ReviewDto dto);

    List<ReviewDto> getListAll();

    List<ReviewDto> getListR(int fk_restaurant_id);

    int modify(ReviewDto dto);

    int remove(Integer id, String reviewer);


}