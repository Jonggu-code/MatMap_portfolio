package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.OtherImageDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
