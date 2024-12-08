package com.matjongchan.app.service;

import com.matjongchan.app.dao.OtherImageDao;
import com.matjongchan.app.domain.entity.OtherImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherImageServiceImpl implements OtherImageService {

    @Autowired
    OtherImageDao otherImageDao;

    public int countImage(){
        return otherImageDao.count();
    };

    public int countRImage(int fk_review_id){
        return otherImageDao.countR(fk_review_id);
    };

    public int deleteAllImage(){return otherImageDao.deleteAll();};

    public int insertImage(OtherImageDto dto){return otherImageDao.insert(dto);};

    public List<OtherImageDto> getRestaurantImg(int restaurant_id){return otherImageDao.getRestaurantImages(restaurant_id);};

    public List<OtherImageDto> getRestaurantImg2(int restaurant_id){return otherImageDao.getRestaurantImages2(restaurant_id);};

    public int deleteImage(int fk_review_id){
        return otherImageDao.deleteImage(fk_review_id);
    }
}
