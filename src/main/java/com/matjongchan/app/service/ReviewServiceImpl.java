
package com.matjongchan.app.service;
import com.matjongchan.app.dao.ReviewDao;
import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.dto.ReviewDetailSearchCondition;
import com.matjongchan.app.domain.entity.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewDao reviewDao;

    // 모든 리뷰 개수 get 하는 메서드
    public int getAllCount(){
        return reviewDao.count();
    };

    // 특정 음식점 리뷰 개수 get 하는 메서드
    public int getCountR(int fk_restaurant_id){
        return reviewDao.countR(fk_restaurant_id);
    };

    // 전체, 고객응대, 청결도, 맛 평점 get 하는 메서드
    public double getTotalAvg(int fk_restaurant_id){return reviewDao.totalAvgScore(fk_restaurant_id);};
    public double getKindAvg(int fk_restaurant_id){return reviewDao.kindAvgScore(fk_restaurant_id);};
    public double getCleanAvg(int fk_restaurant_id){return reviewDao.cleanAvgScore(fk_restaurant_id);};
    public double getTasteAvg(int fk_restaurant_id){return reviewDao.tasteAvgScore(fk_restaurant_id);};


    // 리뷰 작성하는 하는 메서드
    public int write(ReviewDto dto) {
        return reviewDao.insert(dto);
    };
    
    // 모든 음식점 모든 리뷰 불러오는 메서드
    public List<ReviewDto> getListAll(){return reviewDao.selectAll();};

    // 특정 음식점 모든 리뷰 불러오는 메서드
    public List<ReviewDto> getListR(int fk_restaurant_id){return reviewDao.selectR(fk_restaurant_id);};
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
        List<Double> collect = reviewDao.getTotalScore(fk_restaurant_id)
                .stream()
                .map(BigDecimal::doubleValue)
                .collect(Collectors.toList());
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
}
