package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.OtherImageDto;
import com.matjongchan.app.domain.entity.ReviewDto;

import java.util.List;

public interface ReviewMenuDao {

    int count();

    int deleteAll();

    int insert(ReviewDto dto);

    List<ReviewDto> selectAll();

    List<ReviewDto> selectR(int fk_restaurant_id);

    List<ReviewDto> selectM(String reviewer);

    ReviewDto select(Integer bno);

    int update(ReviewDto dto);

    int delete(int id, String writer);

    List<OtherImageDto> getReviewImages(int review_id);

}
