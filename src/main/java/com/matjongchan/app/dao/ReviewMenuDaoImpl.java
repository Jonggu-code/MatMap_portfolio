package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.domain.entity.ReviewMenuDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public class ReviewMenuDaoImpl implements ReviewMenuDao {

    @Autowired
    private SqlSession session;
    private static String namespace="com.matjongchan.app.dao.reviewMenuMapper.";

    public int count(){return session.selectOne(namespace+"count");};

    public int countR(int review_id){return session.selectOne(namespace+"countR", review_id);};

    public int delete(int reviewId){return session.delete(namespace+"delete",reviewId);};

    public int insert(ReviewMenuDto reviewMenuDto){return session.insert(namespace+"insert",reviewMenuDto);};

    public int deleteAll(){return session.delete(namespace+"deleteAll");};

    public List<ReviewMenuDto> selectAll(){return session.selectList(namespace+"selectAll");};

    public List<ReviewMenuDto> selectR(int reviewId){return session.selectList(namespace+"selectR",reviewId);};

}
