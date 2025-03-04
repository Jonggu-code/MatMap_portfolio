
package com.matjongchan.app.service;
import com.matjongchan.app.dao.OtherImageDao;
import com.matjongchan.app.dao.ReviewDao;
import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.dto.ReviewDetailSearchCondition;
import com.matjongchan.app.domain.entity.MenuDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewDao reviewDao;
    @Autowired
    ReviewService reviewService;

    @Autowired
    OtherImageDao otherImageDao;

    // 모든 리뷰 개수 get 하는 메서드
    public int getAllCount(){
        return reviewDao.count();
    };

    // 특정 음식점 리뷰 개수 get 하는 메서드
    public int getCountR(int fk_restaurant_id){
        return reviewDao.countR(fk_restaurant_id);
    };

    // 전체, 고객응대, 청결도, 맛 평점 get 하는 메서드
    public double getTotalAvg(int fk_restaurant_id){
        if (reviewService.getListR(fk_restaurant_id).size() == 0){return 0.0;}
        return reviewDao.totalAvgScore(fk_restaurant_id);};
    public double getKindAvg(int fk_restaurant_id){
        if (reviewService.getListR(fk_restaurant_id).size() == 0){return 0.0;}
        return reviewDao.kindAvgScore(fk_restaurant_id);};
    public double getCleanAvg(int fk_restaurant_id){
        if (reviewService.getListR(fk_restaurant_id).size() == 0){return 0.0;}
        return reviewDao.cleanAvgScore(fk_restaurant_id);};
    public double getTasteAvg(int fk_restaurant_id){
        if (reviewService.getListR(fk_restaurant_id).size() == 0){return 0.0;}
        return reviewDao.tasteAvgScore(fk_restaurant_id);};


    // 리뷰 작성하는 하는 메서드
    public int write(ReviewDto dto) {
        return reviewDao.insert(dto);
    };
    
    // 모든 음식점 모든 리뷰 불러오는 메서드
    public List<ReviewDto> getListAll(){return reviewDao.selectAll();};

    // 특정 음식점 모든 리뷰 불러오는 메서드
    public List<ReviewDto> getListR(int fk_restaurant_id){
        List<ReviewDto> reviewDtos = reviewDao.selectR(fk_restaurant_id);
        for (ReviewDto reviewDto : reviewDtos) {
            List<String> reviewImages = otherImageDao.getReviewImages(reviewDto.getId());
            if(!reviewImages.isEmpty()){
                reviewDto.setOtherImages(reviewImages);
            }
            List<MenuDto> menuList = reviewDao.getMenuList(reviewDto.getId());
            if(!menuList.isEmpty()){
                reviewDto.setMenuNames(menuList.stream().map(MenuDto::getName).collect(Collectors.toList()));
            }

        }

        return reviewDtos;
    };

    public List<ReviewDto> getListFive(Map<String, Object> params){
        return reviewDao.selectFive(params);
    };

    // 리뷰 수정 메서드
    public int modify(ReviewDto dto){return reviewDao.update(dto);};

    // 리뷰 삭제 메서드
    public int remove(Integer id, String reviewer){return reviewDao.delete(id, reviewer);}

    // 식당 상세 페이지에서 사용할 메서드. 식당과 연관관계가 있는 리뷰 3개를 가져옴
    @Override
    public List<ReviewDetail> getReviewDetails(ReviewDetailSearchCondition condition) {
        return reviewDao.getRestaurantReview3(condition);
    }

    @Override
    public List<Double> getTotalScore(int fk_restaurant_id) {
        List<Double> collect = new ArrayList<>();
        try{
            collect = reviewDao.getTotalScore(fk_restaurant_id)
                    .stream()
                    .map(BigDecimal::doubleValue)
                    .collect(Collectors.toList());
        }catch (NullPointerException e){
            return List.of(0.0,0.0,0.0);
        }
        if(collect.size() > 2){
            return collect;
        }else{
            return List.of(0.0,0.0,0.0);
        }
    }

    @Override
    public List<Double> getTotalScoreCountList(int fk_restaurant_id) {
        return reviewDao.getTotalScoreCountList(fk_restaurant_id)
                .stream().map(BigDecimal::doubleValue)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto selectOnceReview(Map<String, Object> params){
        return reviewDao.selectOnceReview(params);
    }

    @Override
    public List<MenuDto> getMenuList(Integer review_id) {
        return List.of();
    }
}
