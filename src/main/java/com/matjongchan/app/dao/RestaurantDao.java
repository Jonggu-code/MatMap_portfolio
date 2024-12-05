package com.matjongchan.app.dao;

import com.matjongchan.app.domain.dto.MenuDetail;
import com.matjongchan.app.domain.dto.TotalCount;
import com.matjongchan.app.domain.entity.BusinessHoursDto;
import com.matjongchan.app.domain.entity.MenuDto;
import com.matjongchan.app.domain.entity.RestaurantDto;
import com.matjongchan.app.domain.dto.SearchCondition;

import java.util.List;

public interface RestaurantDao {
    
    int getRestaurantCount();
    /**
     * Index 번호로 식당 단일 조회
     * @param id
     * @return
     */
    RestaurantDto getRestaurantById(int id);
    
    /**
     * 가게이름으로 식당 단일 조회
     * @param name
     * @return RestaurantDto
     */
    RestaurantDto getRestaurantByName(String name);


    /**
     * 주어진 조건으로 검색기능
     * @param searchCondition
     * @return
     */
    List<RestaurantDto> totalSearch(SearchCondition searchCondition);

    /**
     * 현재 지도 화면 기준 20개 식당 조회
     * @param searchCondition
     * @return
     */
    List<RestaurantDto> nearSearch(SearchCondition searchCondition);

    /**
     * 검색창으로 검색어를 입력하여 조회. 조회는 c_address, d_address, name, category를 합친 키워드안에 있을경우를 조회함. 성능안좋음..
     * @param searchCondition
     * @return
     */
    List<RestaurantDto> realTotalSearch(SearchCondition searchCondition);

    /**
     * 식당 테이블 초기화
     * @return
     */
    int truncateAll();

    /**
     * 식당 추가
     * @param restaurantDto
     * @return
     */
    int insertRestaurant(RestaurantDto restaurantDto);

    /**
     * 식당 정보 수정
     * @param restaurantDto
     * @return
     */
    int updateRestaurant(RestaurantDto restaurantDto);

    /**
     * 식당 PK로 해당 식당 삭제
     * @param id
     * @return
     */
    int deleteRestaurant(int id);

    /**
     * 리뷰작성완료시 동작할 메서드.
     * 리뷰테이블을 조회하여 해당 식당의 평균 총 평점 , 총 리뷰 갯수를 TotalCount에 담아 반환.
     * 단, 매개변수인 TotalCount에는 반드시 restaurant_id, review_id가 있어야만함.
     * @param totalCount
     * @return
     */
    TotalCount getTotalCount(TotalCount totalCount);

    /**
     * 리뷰작성완료시 동작할 메서드
     * 최근 작성된 리뷰가 반영된 식당의 평균 총 평점 , 총 리뷰 갯수를 식당 테이블에 반영한다.
     * 단, 매개변수인 TotalCount에는 반드시 restaurant_id, review_id,
     * total_count_score, total_review_count 가 있어야만한다.
     * @param totalCount
     * @return
     */
    int updateTotalCount(TotalCount totalCount);

    List<RestaurantDto> getRestaurantAll();

    /**
     * 해당 식당 영업시간 조회
     * @param restaurant_id
     * @return
     */
    BusinessHoursDto getBusinessHours(int restaurant_id);

    /**
     * 해당 식당 메뉴를 자세히 가져오기
     * @param restaurant_id
     * @return
     */
    List<MenuDetail> getMenuDetail(int restaurant_id);
    List<MenuDto> getMenuDto(int restaurant_id);

    /**
     * 해당 이미지의 Url 주소를 가져오기
     * @param image_id
     * @return
     */
    String getMenuUrl(int image_id);

    /**
     * 같은 카테고리를 가진 식당 3개를 조회
     * @param restaurant_id
     * @return
     */
    List<RestaurantDto> getRelationRestaurant3 (int restaurant_id);

    /**
     * 랭킹 조회
     * @param searchCondition
     * @return
     */
    List<RestaurantDto> getPopularRestaurant (SearchCondition searchCondition);

    List<RestaurantDto> getAllConsiderRestaurant(SearchCondition searchCondition);

}
