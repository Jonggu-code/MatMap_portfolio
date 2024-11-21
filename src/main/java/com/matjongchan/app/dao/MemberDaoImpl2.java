package com.matjongchan.app.dao;

import com.matjongchan.app.domain.MemberDto;
import com.matjongchan.app.domain.MemberImageDto;
import com.matjongchan.app.domain.ReviewDto;
import com.matjongchan.app.domain.FavoriteDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl2 implements MemberDao2 {
    @Autowired
    private SqlSession session;

    private final String namespace = "com.matjongchan.app.dao.memberMapper2.";

    @Override
    public int count() {
        return session.selectOne(namespace + "count2");
    }

    @Override
    public int deleteAll() {
        System.out.println("99999");
        return session.delete(namespace + "deleteAll2");
    }

    @Override
    public int insert(MemberDto dto) {
        return session.insert(namespace + "insert2", dto);
    }

    @Override
    public List<MemberDto> selectAll() {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public MemberDto select(String user_id) {
        // select 메서드는 user_id를 파라미터로 받아서 해당 회원 정보를 조회
        return session.selectOne(namespace + "selectMemberWithImage", user_id);
    }

    @Override
    public int delete(String user_id) {
        return session.delete(namespace + "delete", user_id);
    }

    @Override
    public int update(MemberDto dto) {
        return session.update(namespace + "updateMember", dto);
    }

    // 1) 프로필 이미지 추가
    @Override
    public int insertMemberImage(MemberImageDto dto) {
        return session.insert(namespace + "insertMemberImage", dto);
    }

    // 2) 프로필 이미지 조회
    @Override
    public MemberImageDto selectMemberImage(int id) {
        return session.selectOne(namespace + "selectMemberImage", id);
    }

    // 3) 프로필 이미지 수정
    @Override
    public int updateMemberImage(MemberImageDto dto) {
        return session.update(namespace + "updateMemberImage", dto);
    }

    // 4) 프로필 이미지 삭제
    @Override
    public int deleteMemberImage(int id) {
        return session.delete(namespace + "deleteMemberImage", id);
    }

    @Override
    public List<ReviewDto> selectMemberReviews(String user_id) {
        // 특정 회원의 리뷰 조회는 user_id를 기준으로 조회
        return session.selectList(namespace + "selectMemberReviews", user_id);
    }

    @Override
    public int deleteMemberReview(String user_id) {
        // 특정 회원의 리뷰 삭제는 user_id를 기준으로 삭제
        return session.delete(namespace + "deleteMemberReview", user_id);
    }

    @Override
    public List<FavoriteDto> selectFavorites(String user_id) {
        // 특정 회원의 즐겨찾기 조회는 user_id를 기준으로 조회
        return session.selectList(namespace + "selectFavorites", user_id);
    }

    @Override
    public int deleteFavorite(String user_id) {
        // 특정 회원의 즐겨찾기 삭제는 user_id를 기준으로 삭제
        return session.delete(namespace + "deleteFavorite", user_id);
    }
}
