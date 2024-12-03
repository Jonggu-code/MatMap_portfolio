package com.matjongchan.app.service;

import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.entity.MenuDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.domain.entity.ReviewMenuDto;

import java.util.List;

public interface ReviewMenuService {

    int getAllCount();

    int getCountR(int review_id);

    int remove(int review_id);

    int write(ReviewMenuDto reviewMenuDto);


    List<ReviewMenuDto> getListAll();

    List<ReviewMenuDto> getListR(int reviewId);

}
