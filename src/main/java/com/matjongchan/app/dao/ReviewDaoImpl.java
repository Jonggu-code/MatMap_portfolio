package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.MemberDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    private SqlSession session;
    private static String namespace="com.matjongchan.app.dao.reviewMapper.";

    public int count() {
        return session.selectOne(namespace + "count");
    }

    public int countR(int fk_restaurant_id) {
        return session.selectOne(namespace + "countR", fk_restaurant_id);
    }

    public List<ReviewDto> select(MemberDto memberDto) {
        return session.selectOne(namespace + "selectM", memberDto);
    }

    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }

    public int insert(ReviewDto dto) {
        return session.insert(namespace+"insert", dto);
    }

    public List<ReviewDto> selectAll() {
        return session.selectList(namespace+"selectAll");
    }
    public List<ReviewDto> selectR(int fk_restaurant_id) {
        return session.selectList(namespace+"selectR",fk_restaurant_id);
    }
    @Override
    public List<ReviewDto> selectM(String reviewer) {
        return session.selectList(namespace+"selectM", reviewer);
    }

    public ReviewDto select(Integer id) {
        return session.selectOne(namespace+"select", id);
    }

    public int update(ReviewDto dto) {
        return session.update(namespace+"update",dto);
    }

    public int delete(int id, String reviewer) {

        // mapper에 보낼수 있는 파라미터의 개수는 한개기 때문에, 두개 이상의 정보를 넘길때는 Map의 형태로 한덩어리로 만들어서 넘겨야 함.
        Map map = new HashMap();
        map.put("id", id);
        map.put("reviewer", reviewer);

        return session.delete(namespace+"delete", map);
    }

}
