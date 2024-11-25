package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.ReviewDto;

import java.util.List;

public interface ReviewDao {

    int count();

    int countR(int fk_restaurant_id);

    int deleteAll();

    int insert(ReviewDto dto);

    List<ReviewDto> selectAll();

    List<ReviewDto> selectR(int fk_restaurant_id);


    ReviewDto select(Integer bno);

    int update(ReviewDto dto);

    int delete(int id, String writer);

}
