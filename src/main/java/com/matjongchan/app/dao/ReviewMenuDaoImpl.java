package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.OtherImageDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ReviewMenuDaoImpl implements ReviewMenuDao {

    @Autowired
    private SqlSession session;

    private final String namespace="com.matjongchan.app.mapper.reviewMenuMapper.";

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int insert(ReviewDto dto) {
        return 0;
    }

    @Override
    public List<ReviewDto> selectAll() {
        return List.of();
    }

    @Override
    public List<ReviewDto> selectR(int fk_restaurant_id) {
        return List.of();
    }

    @Override
    public List<ReviewDto> selectM(String reviewer) {
        return List.of();
    }

    @Override
    public ReviewDto select(Integer bno) {
        return null;
    }

    @Override
    public int update(ReviewDto dto) {
        return 0;
    }

    @Override
    public int delete(int id, String writer) {
        return 0;
    }

    // 해당 review_id 에 해당하는 fk_review_id를 가진 이미지 전체 조회
    @Override
    public List<OtherImageDto> getReviewImages(int review_id) {
        return session.selectList(namespace+"selectReviewImage",review_id);
    }
}
