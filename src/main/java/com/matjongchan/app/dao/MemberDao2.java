package com.matjongchan.app.dao;


import com.matjongchan.app.domain.FavoriteDto;
import com.matjongchan.app.domain.MemberDto;
import com.matjongchan.app.domain.MemberImageDto;
import com.matjongchan.app.domain.ReviewDto;

import java.util.List;

public interface MemberDao2 {
    int count();
    int deleteAll();
    int insert(MemberDto dto);
    List<MemberDto> selectAll();
    MemberDto select(String user_id);  // user_id 기준으로 조회
    int delete(String user_id);        // user_id 기준으로 삭제
    int update(MemberDto dto);         // user_id를 이용해 업데이트
    // 1) 프로필 이미지 추가
    int insertMemberImage(MemberImageDto dto);

    // 2) 프로필 이미지 조회
    MemberImageDto selectMemberImage(int id);

    // 3) 프로필 이미지 수정
    int updateMemberImage(MemberImageDto dto);

    // 4) 프로필 이미지 삭제
    int deleteMemberImage(int id);

    List<ReviewDto> selectMemberReviews(String user_id); // user_id 기준으로 리뷰 조회
    int deleteMemberReview(String user_id);  // user_id 기준으로 리뷰 삭제
    List<FavoriteDto> selectFavorites(String user_id);  // user_id 기준으로 즐겨찾기 조회
    int deleteFavorite(String user_id);  // user_id 기준으로 즐겨찾기 삭제
}

