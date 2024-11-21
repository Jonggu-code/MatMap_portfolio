
package com.matjongchan.app.service;
import com.matjongchan.app.dao.ReviewDao;
import com.matjongchan.app.domain.ReviewDto;
import org.checkerframework.checker.units.qual.A;
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
    public int remove(Integer id, String reviewer){return reviewDao.delete(id, reviewer);};

}
