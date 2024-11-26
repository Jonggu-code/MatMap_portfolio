package com.matjongchan.app.service;

import com.matjongchan.app.domain.dto.RestaurantDetail;

public interface RestaurantService {

    //식당 상세페이지 조회기능
    RestaurantDetail getRestaurantDetail(int restaurantId);

    

}
