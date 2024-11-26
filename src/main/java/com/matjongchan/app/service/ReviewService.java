package com.matjongchan.app.service;

import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.dto.ReviewDetailSearchCondition;
import com.matjongchan.app.domain.entity.ReviewDto;

import java.util.List;

public interface ReviewService {
    int getAllCount();

    int getCountR(int fk_restaurant_id);

    int write(ReviewDto dto);

    List<ReviewDto> getListAll();

    List<ReviewDto> getListR(int fk_restaurant_id);

    int modify(ReviewDto dto);

    int remove(Integer id, String reviewer);

    //식당 상세페이지에서 리뷰조회시 사용할 메서드
    List<ReviewDetail> getReviewDetail(ReviewDetailSearchCondition reviewDetailSearchCondition);
}
