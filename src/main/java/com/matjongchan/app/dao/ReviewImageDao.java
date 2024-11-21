package com.matjongchan.app.dao;

import com.matjongchan.app.domain.ReviewImageDto;

public interface ReviewImageDao {
    int count();

    int countR(int fk_review_id);

    int deleteAll();

    int insert(ReviewImageDto dto);
}
