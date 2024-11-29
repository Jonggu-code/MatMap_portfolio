package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.OtherImageDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.domain.entity.ReviewMenuDto;

import java.util.List;

public interface ReviewMenuDao {

    int count();

    int countR(int review_id);

    int delete(int review_id);

    int insert(ReviewMenuDto reviewMenuDto);


    int deleteAll();

    List<ReviewMenuDto> selectAll();

    List<ReviewMenuDto> selectR(int reviewId);

    ReviewDto select(Integer bno);

    int update(ReviewDto dto);

    int delete(int id, String writer);

    List<OtherImageDto> getReviewImages(int review_id);

}
