package com.matjongchan.app.service;

import com.matjongchan.app.dao.MemberDao2;
import com.matjongchan.app.domain.dto.FavoriteWithRestaurantDto;
import com.matjongchan.app.domain.entity.MemberDto;
import com.matjongchan.app.domain.entity.MemberImageDto;
import com.matjongchan.app.domain.entity.MemberReviewsDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberDao2 memberDao;

    // 회원 수 조회
    public int getMemberCount() {
        return memberDao.count();
    }

    // 모든 회원 삭제
    public int deleteAllMembers() {
        return memberDao.deleteAll();
    }

    // 회원 추가
    public int addMember(MemberDto dto) {
        return memberDao.insert(dto);
    }

    // 모든 회원 조회
    public List<MemberDto> getAllMembers() {
        return memberDao.selectAll();
    }

    // 특정 회원 조회
    public MemberDto getMember(String userId) {
        return memberDao.select(userId);
    }

    // 회원 및 이미지 함께 조회
    public MemberImageDto getMemberWithImage(String userId) {
        return memberDao.selectMemberWithImage(userId);
    }

    // 회원 삭제
    public int deleteMember(String userId) {
        return memberDao.delete(userId);
    }

    // 회원 정보 업데이트
    public int updateMember(MemberDto dto) {
        return memberDao.update(dto);
    }

    // 모든 이미지 삭제
    public int deleteAllImages() {
        return memberDao.deleteAllImage();
    }

    // 회원 이미지 추가
    public int addMemberImage(MemberImageDto dto) {
        return memberDao.insertMemberImage(dto);
    }

    // 특정 이미지 조회
    public MemberImageDto getMemberImage(Integer id) {
        return memberDao.selectMemberImage(id);
    }

    // 특정 이미지 삭제
    public int deleteMemberImage(Integer id) {
        return memberDao.deleteMemberImage(id);
    }

    // 모든 이미지 조회
    public List<MemberImageDto> getAllImages() {
        return memberDao.selectAllImage();
    }

    // 회원 이미지 업데이트
    public int updateMemberImage(MemberImageDto dto) {
        return memberDao.updateMemberImage(dto);
    }

    // 회원 리뷰 조회
    public List<ReviewDto> getMemberReviews(String userId) {
        return memberDao.selectMemberReviews(userId);
    }

    // 회원 리뷰 삭제
    public int deleteMemberReview(String userId) {
        return memberDao.deleteMemberReview(userId);
    }

    // 리뷰 개수 조회
    public int getMemberReviewCount() {
        return memberDao.countMemberReview();
    }

    // 모든 리뷰 삭제
    public int deleteAllMemberReviews() {
        return memberDao.deleteAllMemberReview();
    }

    // 회원 리뷰 추가
    public int addMemberReview(MemberReviewsDto dto) {
        return memberDao.insertMemberReview(dto);
    }

    // 모든 리뷰 조회
    public List<MemberReviewsDto> getAllMemberReviews() {
        return memberDao.selectAllMemberReview();
    }

    // 특정 리뷰 조회
    public MemberReviewsDto getMemberReview(Integer id) {
        return memberDao.selectMemberReview(id);
    }

    // 회원 즐겨찾기 조회
    public List<FavoriteWithRestaurantDto> getMemberFavorites(String userId) {
        return memberDao.selectFavorites(userId);
    }

    // 특정 즐겨찾기 삭제
    public int deleteMemberFavorite(String userId) {
        return memberDao.deleteFavorite(userId);
    }
}
