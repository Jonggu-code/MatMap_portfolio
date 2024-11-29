package com.matjongchan.app.service;

import com.matjongchan.app.domain.dto.FavoriteWithRestaurantDto;
import com.matjongchan.app.domain.entity.MemberDto;
import com.matjongchan.app.domain.entity.MemberImageDto;
import com.matjongchan.app.domain.entity.MemberReviewsDto;
import com.matjongchan.app.domain.entity.ReviewDto;

import java.util.List;

public interface MemberService {
    // 회원 수 조회
    public int getMemberCount();

    // 모든 회원 삭제
    public int deleteAllMembers();

    // 회원 추가
    public int addMember(MemberDto dto);
    // 모든 회원 조회
    public List<MemberDto> getAllMembers();
    // 특정 회원 조회
    public MemberDto getMember(String userId);

    // 회원 및 이미지 함께 조회
    public MemberImageDto getMemberWithImage(String userId);

    // 회원 삭제
    public int deleteMember(String userId);

    // 회원 정보 업데이트
    public int updateMember(MemberDto dto);

    // 모든 이미지 삭제
    public int deleteAllImages();

    // 회원 이미지 추가
    public int addMemberImage(MemberImageDto dto);

    // 특정 이미지 조회
    public MemberImageDto getMemberImage(Integer id);
    // 특정 이미지 삭제
    public int deleteMemberImage(Integer id);

    // 모든 이미지 조회
    public List<MemberImageDto> getAllImages();

    // 회원 이미지 업데이트
    public int updateMemberImage(MemberImageDto dto);

    // 회원 리뷰 조회
    public List<ReviewDto> getMemberReviews(String userId);

    // 특정 회원의 리뷰 개수 조회
    public int selectMemberReviewCount(String userId);

    // 회원 리뷰 삭제
    public int deleteMemberReview(String userId);

    // 리뷰 개수 조회
    public int getMemberReviewCount();

    // 모든 리뷰 삭제
    public int deleteAllMemberReviews();

    // 회원 리뷰 추가
    public int addMemberReview(MemberReviewsDto dto);
    // 모든 리뷰 조회
    public List<MemberReviewsDto> getAllMemberReviews();
    // 특정 리뷰 조회
    public MemberReviewsDto getMemberReview(Integer id);

    // 회원 즐겨찾기 조회
    public List<FavoriteWithRestaurantDto> getMemberFavorites(String userId);

    // 특정 즐겨찾기 삭제

    public int deleteMemberFavorite(String userId);
}
