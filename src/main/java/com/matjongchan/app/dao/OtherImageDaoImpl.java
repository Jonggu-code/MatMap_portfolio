package com.matjongchan.app.dao;

import com.matjongchan.app.domain.dto.S;
import com.matjongchan.app.domain.entity.OtherImageDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OtherImageDaoImpl implements OtherImageDao {
    @Autowired
    private SqlSession session;
    private static String namespace="com.matjongchan.app.dao.reviewImageMapper.";

    public int count(){return session.selectOne(namespace+"count");};

    public int countRes(int fk_restaurant_id){
        return session.selectOne(namespace+"countRes", fk_restaurant_id);
    };

    public int countR(int fk_review_id){
        return session.selectOne(namespace+"countR",fk_review_id);
    };
    public int insert(OtherImageDto dto) {
        return session.insert(namespace+"insert", dto);
    }

    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }

    public List<String> getReviewImages(int fk_review_id){
        return session.selectList(namespace+"getReviewImages",fk_review_id);
    };

    @Override
    public List<OtherImageDto> getRestaurantImages(int restaurant_id) {
        return session.selectList(namespace+"getRestaurantImages",restaurant_id);
    }

    @Override
    public List<String> getRestaurantUrl (int fk_restaurant_id){
        return session.selectList(namespace+"getRestaurantImagesUrl",fk_restaurant_id);
    };

    @Override
    public List<OtherImageDto> getRestaurantImages2(S s) {
        return session.selectList(namespace+"getRestaurantImages2",s);
    }

    @Override
    public int deleteImage(int fk_review_id){
        return session.delete(namespace+"deleteImage",fk_review_id);
    }
}
