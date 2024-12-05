package com.matjongchan.app.dao;

import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.dto.ReviewDetailSearchCondition;
import com.matjongchan.app.domain.entity.ReviewDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReviewDao {

    int count();

    int countR(int fk_restaurant_id);

    double totalAvgScore(int fk_restaurant_id);
    double kindAvgScore(int fk_restaurant_id);
    double cleanAvgScore(int fk_restaurant_id);
    double tasteAvgScore(int fk_restaurant_id);

    int deleteAll();

    int insert(ReviewDto dto);

    List<ReviewDto> selectAll();

    List<ReviewDto> selectR(int fk_restaurant_id);

    List<ReviewDto> selectFive(Map<String, Object> params);

    List<ReviewDto> selectM(String reviewer);

    ReviewDto selectOne(int review_id);


    int update(ReviewDto dto);

    int delete(int id, String writer);

    List<ReviewDetail> getRestaurantReview3(ReviewDetailSearchCondition condition);

    List<BigDecimal> getTotalScore(int fk_restaurant_id);

    List<BigDecimal> getTotalScoreCountList(int fk_restaurant_id);



}
