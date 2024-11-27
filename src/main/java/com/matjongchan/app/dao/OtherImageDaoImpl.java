package com.matjongchan.app.dao;

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

    public int countR(int fk_review_id){
        return session.selectOne(namespace+"countR",fk_review_id);
    };
    public int insert(OtherImageDto dto) {
        return session.insert(namespace+"insert", dto);
    }

    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public List<OtherImageDto> getRestaurantImages(int restaurant_id) {
        return session.selectList(namespace+"getRestaurantImages",restaurant_id);
    }

    @Override
    public List<OtherImageDto> getRestaurantImages2(int restaurant_id) {
        return session.selectList(namespace+"getRestaurantImages2",restaurant_id);
    }
}
