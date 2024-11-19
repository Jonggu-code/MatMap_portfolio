package com.matjongchan.app.dao;

import com.matjongchan.app.domain.ReviewDto;

import java.util.List;

public interface ReviewDao {

    int count();

    int deleteAll();

    int insert(ReviewDto dto);

    List<ReviewDto> selectAll();

    ReviewDto select(Integer bno);

    int update(ReviewDto dto);

    int delete(int bno, String writer);

}
