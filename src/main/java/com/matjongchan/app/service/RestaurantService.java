package com.matjongchan.app.service;

import com.matjongchan.app.domain.dto.*;
import com.matjongchan.app.domain.entity.FavoriteDto;
import com.matjongchan.app.domain.entity.OtherImageDto;
import com.matjongchan.app.domain.entity.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    // MemberController에서 사용하도록 추가함.
    String getImgUrl(RestaurantDto dto);

    //식당 상세페이지 조회기능
    RestaurantDetail getRestaurantDetail(int restaurantId);

    /**
     * 식당 상세페이지에서 리뷰 조회.
     * @param condition
     * @return
     */
    List<ReviewDetail> getReviewDetails(ReviewDetailSearchCondition condition);

    /**
     * 식당 총 갯수 조회
     * @return
     */
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
     * @return RestaurantDto
     */
    List<RestaurantDto> totalSearch(SearchCondition searchCondition);

    /**
     * 현재 지도 화면 기준 20개 식당 조회
     * @param searchCondition
     * @return RestaurantDto
     */
    List<RestaurantDto> nearSearch(SearchCondition searchCondition);

    /**
     * 검색창으로 검색어를 입력하여 조회. 조회는 c_address, d_address, name, category를 합친 키워드안에 있을경우를 조회함. 성능안좋음..
     * @param searchCondition
     * @return RestaurantDto
     */
    List<RestaurantDto> realTotalSearch(SearchCondition searchCondition);

    /**
     * 주어진 조건으로 검색
     * @param searchCondition
     * @return  SimpleRestaurant
     */
    List<SimpleRestaurant> SRTotalSearch(SearchCondition searchCondition);

    /**
     * 현재 지도 화면 기준 20개 식당 조회
     * @param searchCondition
     * @return SimpleRestaurant
     */
    List<SimpleRestaurant> SRNearSearch(SearchCondition searchCondition);

    /**
     * 검색창으로 검색어를 입력하여 조회. 조회는 c_address, d_address, name, category를 합친 키워드안에 있을경우를 조회함. 성능안좋음..
     * @param searchCondition
     * @return SimpleRestaurant
     */
    List<SimpleRestaurant> SRRealTotalSearch(SearchCondition searchCondition);

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

    /**
     * 식당 전체조회
     * @return
     */
    List<RestaurantDto> getRestaurantAll();

    /**
     * 메인화면에서 식당 리스트(20)개 조회
     * @return
     */
    List<SimpleRestaurant> getSimpleRestaurant(SearchCondition searchCondition);
    /**
     * 랭킹 페이지 조회관련
     * @param searchCondition
     * @return
     */
    List<SimpleRestaurant> getRankDescRestaurant(SearchCondition searchCondition);

    List<SimpleRestaurant> getAllConsiderRestaurant(SearchCondition searchCondition);

    public List<OtherImageDto> getImgUrlList(Integer id);

    int saveFavoriteRestaurant (FavoriteDto favoriteDto);

    int deleteFavoriteRestaurant (FavoriteDto favoriteDto);

}
