package com.matjongchan.app.dao;

import com.matjongchan.app.domain.RestaurantDto;
import com.matjongchan.app.domain.SearchCondition;

import java.util.List;

public interface RestaurantDao {
    /**
     * Index 번호로 조회
     * @param id
     * @return
     */
    RestaurantDto getRestaurantById(int id);

    /**
     * 가게이름으로 조회
     * @param name
     * @return RestaurantDto
     */
    RestaurantDto getRestaurantByName(String name);

    /**
     * 제공되는 형식으로 페이지네이션 조회를 위한 메서드
     * SearchCondition 에서는
     * category (식당 종류)
     * option (총평점순 or 리뷰많은순)
     * c_address (**시 )
     * d_address (상세주소)
     * current_page (연결된 페이지)
     * offset (몇개 페이지 가져올지)
     * @param searchCondition
     * @return
     */
    List<RestaurantDto> searchRestaurantsBySearchCondition(SearchCondition searchCondition);

}
