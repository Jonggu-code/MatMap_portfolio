package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.OtherImageDto;

public interface OtherImageDao {
    int count();

    int countR(int fk_review_id);

    int deleteAll();

    int insert(OtherImageDto dto);
}
