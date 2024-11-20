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

    public int getAllCount(){
        return reviewDao.count();
    };

    public int getCountR(int fk_restaurant_id){
        return reviewDao.countR(fk_restaurant_id);
    };

    public int write(ReviewDto dto) {
        return reviewDao.insert(dto);
    };

    public List<ReviewDto> getListAll(){return reviewDao.selectAll();};

    public List<ReviewDto> getListR(int fk_restaurant_id){return reviewDao.selectR(fk_restaurant_id);};

    public int modify(ReviewDto dto){return reviewDao.update(dto);};

    public int remove(Integer id, String reviewer){return reviewDao.delete(id, reviewer);};

}
