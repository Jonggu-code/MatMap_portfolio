package com.matjongchan.app.service;

import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.dto.ReviewDetailSearchCondition;
import com.matjongchan.app.domain.entity.MenuDto;
import com.matjongchan.app.domain.entity.ReviewDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    int getAllCount();

    int getCountR(int fk_restaurant_id);

    double getTotalAvg(int fk_restaurant_id);
    double getKindAvg(int fk_restaurant_id);
    double getCleanAvg(int fk_restaurant_id);
    double getTasteAvg(int fk_restaurant_id);

    int write(ReviewDto dto);

    List<ReviewDto> getListAll();

    List<ReviewDto> getListR(int fk_restaurant_id);
    List<ReviewDto> getListFive(Map<String, Object> params);

    int modify(ReviewDto dto);

    int remove(Integer id, String reviewer);

    List<ReviewDetail> getReviewDetails(ReviewDetailSearchCondition condition);

    List<Double> getTotalScore(int fk_restaurant_id);

    List<Double> getTotalScoreCountList(int fk_restaurant_id);

    public ReviewDto selectOnceReview(Map<String, Object> params);

    List<MenuDto> getMenuList(Integer review_id);

}
