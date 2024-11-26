package com.matjongchan.app.service;

import com.matjongchan.app.dao.RestaurantDao;
import com.matjongchan.app.dao.ReviewMenuDao;
import com.matjongchan.app.domain.dto.*;
import com.matjongchan.app.domain.entity.OtherImageDto;
import com.matjongchan.app.domain.entity.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDao restaurantDao;
    private final ReviewService reviewService;
    private final ReviewMenuDao reviewMenuDao;

    @Override
    public RestaurantDetail getRestaurantDetail(int restaurantId) {
        return null;
    }

    @Override
    public List<ReviewDetail> getReviewDetails(ReviewDetailSearchCondition condition) {

        List<ReviewDetail> review_detail_list = reviewService.getReviewDetails(condition);
        //이미지,age,gender

        for (ReviewDetail reviewDetail : review_detail_list) {
            List<OtherImageDto> reviewImages = reviewMenuDao.getReviewImages(reviewDetail.getId());
            reviewDetail.setImage(reviewImages);
        }
        //이거 어찌 가져오냐? 회원 나이하고 성별을..누구맘대로!!!!!!!!!!!!!!!!!!!!!

        return List.of();
    }

    @Override
    public int getRestaurantCount() {
        return restaurantDao.getRestaurantCount();
    }

    @Override
    public RestaurantDto getRestaurantById(int id) {
        return restaurantDao.getRestaurantById(id);
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        return restaurantDao.getRestaurantByName(name);
    }

    @Override
    public List<RestaurantDto> totalSearch(SearchCondition searchCondition) {
        return restaurantDao.totalSearch(searchCondition);
    }

    @Override
    public List<RestaurantDto> nearSearch(SearchCondition searchCondition) {
        return restaurantDao.nearSearch(searchCondition);
    }

    @Override
    public List<RestaurantDto> realTotalSearch(SearchCondition searchCondition) {
        return restaurantDao.realTotalSearch(searchCondition);
    }

//    @Override
//    public int truncateAll() {
//        return restaurantDao.truncateAll();
//    }

    @Override
    public int insertRestaurant(RestaurantDto restaurantDto) {
        return restaurantDao.insertRestaurant(restaurantDto);
    }

    @Override
    public int updateRestaurant(RestaurantDto restaurantDto) {
        return restaurantDao.updateRestaurant(restaurantDto);
    }

    @Override
    public int deleteRestaurant(int id) {
        return restaurantDao.deleteRestaurant(id);
    }

    @Override
    public TotalCount getTotalCount(TotalCount totalCount) {
        return restaurantDao.getTotalCount(totalCount);
    }

    @Override
    public int updateTotalCount(TotalCount totalCount) {
        return restaurantDao.updateTotalCount(totalCount);
    }

    @Override
    public List<RestaurantDto> getRestaurantAll() {
        return restaurantDao.getRestaurantAll();
    }
}
