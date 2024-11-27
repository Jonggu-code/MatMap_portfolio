package com.matjongchan.app.service;

import com.matjongchan.app.dao.ReviewDao;
import com.matjongchan.app.dao.ReviewMenuDao;
import com.matjongchan.app.domain.entity.ReviewMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewMenuServiceImpl implements ReviewMenuService {
    @Autowired
    ReviewMenuDao reviewMenuDao;

    public int getAllCount(){
        return reviewMenuDao.count();
    };

    public int getCountR(int review_id){
        return reviewMenuDao.countR(review_id);
    };

    public int remove(int review_id){
        return reviewMenuDao.delete(review_id);
    };

    public int write(ReviewMenuDto reviewMenuDto){
        return reviewMenuDao.insert(reviewMenuDto);
    };


    public List<ReviewMenuDto> getListAll(){
        return reviewMenuDao.selectAll();
    };

    public List<ReviewMenuDto> getListR(int reviewId){
        return reviewMenuDao.selectR(reviewId);
    };

}
