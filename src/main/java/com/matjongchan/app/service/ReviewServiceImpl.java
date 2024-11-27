
package com.matjongchan.app.service;
import com.matjongchan.app.dao.ReviewDao;
import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.entity.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int getTotalAvg(int fk_restaurant_id){return reviewDao.totalAvgScore(fk_restaurant_id);};
    public int getKindAvg(int fk_restaurant_id){return reviewDao.kindAvgScore(fk_restaurant_id);};
    public int getCleanAvg(int fk_restaurant_id){return reviewDao.cleanAvgScore(fk_restaurant_id);};
    public int getTasteAvg(int fk_restaurant_id){return reviewDao.tasteAvgScore(fk_restaurant_id);};


    // 리뷰 작성하는 하는 메서드
    public int write(ReviewDto dto) {
        return reviewDao.insert(dto);
    };
    
    // 모든 음식점 모든 리뷰 불러오는 메서드
    public List<ReviewDto> getListAll(){return reviewDao.selectAll();};

    // 특정 음식점 모든 리뷰 불러오는 메서드
    public List<ReviewDto> getListR(int fk_restaurant_id){return reviewDao.selectR(fk_restaurant_id);};

    // 리뷰 수정 메서드
    public int modify(ReviewDto dto){return reviewDao.update(dto);};

    // 리뷰 삭제 메서드
    public int remove(Integer id, String reviewer){return reviewDao.delete(id, reviewer);}

    @Override
    public List<ReviewDetail> getReviewDetail(Integer review_id) {

        return null;

    }

    ;

}
