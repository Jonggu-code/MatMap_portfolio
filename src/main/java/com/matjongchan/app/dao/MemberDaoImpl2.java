package com.matjongchan.app.dao;

import com.matjongchan.app.domain.dto.FavoriteWithRestaurantDto;
import com.matjongchan.app.domain.dto.ReviewerDto;
import com.matjongchan.app.domain.entity.*;
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
        return session.selectOne(namespace + "count");
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int insert(MemberDto dto) {
        return session.insert(namespace + "insert", dto);
    }

    @Override
    public List<MemberDto> selectAll() {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public MemberDto select(String user_id){
        return session.selectOne(namespace + "select", user_id);
    }

    @Override
    public MemberImageDto selectMemberWithImage(String user_id) {
        return session.selectOne(namespace + "selectMemberWithImage", user_id);
    }

    @Override
    public int delete(String user_id) {
        return session.delete(namespace + "delete", user_id);
    }

    @Override
    public int update(MemberDto dto) {
        return session.update(namespace + "update", dto);
    }

    @Override
    public int deleteAllImage(){
        return session.delete(namespace + "deleteAllImage");
    }
    @Override
    public int insertMemberImage(MemberImageDto dto) {
        return session.insert(namespace + "insertMemberImage", dto);
    }

    @Override
    public MemberImageDto selectMemberImage(Integer id) {
        return session.selectOne(namespace + "selectMemberImage", id);
    }

    @Override
    public int deleteMemberImage(Integer id) {
        return session.delete(namespace + "deleteMemberImage", id);
    }

    @Override
    public List<MemberImageDto> selectAllImage(){
        return session.selectList(namespace + "selectAllImage");
    }
    @Override
    public int updateMemberImage(MemberImageDto dto) {
        return session.update(namespace + "updateMemberImage", dto);
    }

    @Override
    public MemberImageDto selectImageOne(Integer id) {
        return session.selectOne(namespace + "selectOne", id);
    }

    @Override
    public List<ReviewDto> selectMemberReviews(String user_id) {
        return session.selectList(namespace + "selectMemberReviews", user_id);
    }

    @Override
    public int selectMemberReviewCount(String user_id){
        return session.selectOne(namespace + "selectMemberReviewCount", user_id);
    }

    @Override
    public int deleteMemberReview(String user_id) {
        return session.delete(namespace + "deleteMemberReview", user_id);
    }



    @Override
    public int countMemberReview(){
        return session.selectOne(namespace + "countMemberReview");
    }

    @Override
    public int deleteAllMemberReview(){
        return session.delete(namespace + "deleteAllMemberReview");
    }

    @Override
    public int insertMemberReview(MemberReviewsDto dto){
        return session.insert(namespace + "insertMemberReview", dto);
    }

    @Override
    public List<MemberReviewsDto> selectAllMemberReview(){
        return session.selectOne(namespace + "selectAllMemberReview");
    }

    @Override
    public MemberReviewsDto selectMemberReview(Integer id){
        return session.selectOne(namespace + "selectMemberReview", id);
    }

    @Override
    public MemberImageDto selectMemberImageOne(String img_name){
        return session.selectOne(namespace+"selectOne",img_name);
    }

    @Override
    public  List<FavoriteWithRestaurantDto> selectFavorites(String user_id) {
        return session.selectList(namespace + "selectFavorites", user_id);
    }

    @Override
    public int deleteFavorite(String user_id) {
        return session.delete(namespace + "deleteFavorite", user_id);
    }

    @Override
    public ReviewerDto getReviewerProfileImg(String user_id) {
        return session.selectOne(namespace + "getReviewerProfileImg", user_id);
    }
}
