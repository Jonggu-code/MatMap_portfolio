package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.MemberDto;
import com.matjongchan.app.domain.entity.MemberImageDto;
import com.matjongchan.app.domain.entity.MemberReviewsDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDaoImpl22Test {

    @Autowired
    MemberDao2 memberDao;
    @Autowired
    ReviewDao reviewDao;

    @Test
    public void count() {
        memberDao.deleteAll();
        MemberDto memberDto = new MemberDto(null, "user1", "pass1", "User1", "Address1", "user1@example.com", "Intro1", "남", 13, "12345","now()",123);
        memberDao.insert(memberDto);
        int count = memberDao.count();
//        System.out.println("aa: " + memberDao.insert(memberDto));
        assertTrue(count == 1);
    }

    @Test
    public void deleteAll(){
        MemberDto memberDto = new MemberDto(null, "user1", "pass1", "User1", "Address1", "user1@example.com", "Intro1", "남", 13, "12345","now()",123);
        memberDao.insert(memberDto);

        memberDao.deleteAll();
        int count = memberDao.count();
        assertTrue(count == 0);
    }
    @Test
    public void insert() {
        memberDao.deleteAll();
        MemberDto member = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-0000-0000","now()",123);
        int insert = memberDao.insert(member);

        assertTrue(insert == 1);

    }

    @Test
    public void selectAll() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-2222-2222","now()",123);
        memberDao.insert(member1);

        MemberDto member2 = new MemberDto(null, "user3", "pass3", "User3", "Address3", "user3@example.com", "Intro3", "남", 25, "010-3333-3333","now()",123);
        memberDao.insert(member2);

        assertTrue(memberDao.selectAll().size() == 2);
    }

    @Test
    public void select() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-2222-2222","now()",123);
        memberDao.insert(member1);

        MemberDto member2 = new MemberDto(null, "user3", "pass3", "User3", "Address3", "user3@example.com", "Intro3", "남", 25, "010-3333-3333","now()",123);
        memberDao.insert(member2);

        assertTrue(memberDao.select("user3").getAge() == 25);
    }

    @Test
    public void selectMemberWithImage() {
        memberDao.deleteAll();
        memberDao.deleteAllImage();

        // 회원 데이터 삽입
        MemberDto member = new MemberDto(null, "user1", "pass1", "User1", "Address1", "user1@example.com", "Intro1", "남", 13, "010-1111-1111", "now()", 1);
        memberDao.insert(member);

        // 이미지 데이터 삽입
        MemberImageDto imageDto = new MemberImageDto(null, "image1.jpg", "image_url", 1);
        memberDao.insertMemberImage(imageDto);


        MemberImageDto memberWithImage = memberDao.selectMemberWithImage("user1");
//        assertNotNull(memberWithImage); // null이 아니어야 합니다

        assertTrue(memberWithImage.getName().equals("image1.jpg"));

    }


    @Test
    public void delete() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user5", "pass5", "User5", "Address5", "user5@example.com", "Intro5", "여", 35, "010-5555-5555","now()",123);
        memberDao.insert(member1);
        int delete = memberDao.delete("user5");
        assertTrue(delete == 1);
    }

    @Test
    public void update() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user0", "pass0", "User0", "Address0", "user0@example.com", "Intro0", "남", 55, "010-5555-5555","now()",123);
        memberDao.insert(member1);

        Integer id = memberDao.selectAll().get(0).getId();


        MemberDto updateMember1 = new MemberDto(id, "user0", "updatePass5", "updateUser5", "updateAddress5", "updateUser5@example.com", "updateIntro5", "여", 45, "010-6666-6667","now()",123);
        int update = memberDao.update(updateMember1);

        assertTrue(update == 1);
    }

    @Test
    public void insertMemberImage() {
        memberDao.deleteAllImage();
        MemberImageDto imageDto = new MemberImageDto(null, "profile.png", "image_url", 2);

        int insert = memberDao.insertMemberImage(imageDto);
        assertTrue(insert == 1);
    }

    @Test
    public void selectMemberImage() {
        memberDao.deleteAllImage();
        MemberImageDto imageDto = new MemberImageDto(null, "profile.png", "image_url", 2);
        memberDao.insertMemberImage(imageDto);

        assertTrue(memberDao.selectMemberImage(1).getName().equals("profile.png"));
    }

    @Test
    public void deleteMemberImage() {
        memberDao.deleteAllImage();

        MemberImageDto imageDto = new MemberImageDto(null, "profile.png", "image_url", 2);
        memberDao.insertMemberImage(imageDto);

        MemberImageDto imageDto2 = new MemberImageDto(null, "profile2.png", "image_url2", 3);
        memberDao.insertMemberImage(imageDto2);

        assertTrue(memberDao.deleteMemberImage(2) == 1);
    }

    @Test
    public void updateMemberImage() {
        memberDao.deleteAllImage();

        MemberImageDto imageDto = new MemberImageDto(null, "profile.png", "image_url", 2);
        memberDao.insertMemberImage(imageDto);

        Integer id = memberDao.selectAllImage().get(0).getId();

        MemberImageDto imageDto2 = new MemberImageDto(id, "newprofile.png", "newimage_url", 3);
        int update = memberDao.updateMemberImage(imageDto2);

        assertTrue(update == 1);

    }

//    10) 특정 회원의 리뷰 조회 (user_id 기준)
    @Test
    public void selectMemberReviews() {
        // 회원추가
        memberDao.deleteAll();
        reviewDao.deleteAll();
        memberDao.deleteAllMemberReview();


        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-2222-2222","now()",123);
        memberDao.insert(member1);
        String userId = memberDao.selectAll().get(0).getUser_id();

        // 리뷰 추가
        ReviewDto review1 = new ReviewDto(null, userId, "title1", "content1", 3.4,4.5,5.0, (3.4 + 4.5 + 5.0) / 3, null, 123);
//        ReviewDto review2 = new ReviewDto(null, userId, "title2", "content2", 4.5,3.5,3.0, (4.5 + 3.5 + 3.0) / 3, null, 125);

        reviewDao.insert(review1);
//        reviewDao.insert(review2);

        Integer fk_memberId = memberDao.selectAll().get(0).getId();
        Integer fk_reviewId = reviewDao.selectAll().get(0).getId();

        // member_review 테이블 관련 추가
        MemberReviewsDto mrdto = new MemberReviewsDto(null,fk_memberId,fk_reviewId);
        memberDao.insertMemberReview(mrdto);


        List<ReviewDto> reviews = memberDao.selectMemberReviews(userId);
        assertTrue(reviews.size() == 1);

    }

    @Test
    public void deleteMemberReview() {
        // 회원, 리뷰, 관계 테이블 초기화
        memberDao.deleteAll();
        reviewDao.deleteAll();
        memberDao.deleteAllMemberReview();

        // 회원 추가
        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-2222-2222","now()",123);
        memberDao.insert(member1);
        String userId = memberDao.selectAll().get(0).getUser_id();

        // 리뷰 추가
        ReviewDto review1 = new ReviewDto(null, userId, "title1", "content1", 3.4,4.5,5.0, (3.4 + 4.5 + 5.0) / 3, null, 123);
        reviewDao.insert(review1);

        ReviewDto review2 = new ReviewDto(null, userId, "title2", "content2", 4.5,3.5,3.0, (4.5 + 3.5 + 3.0) / 3, null, 125);
        reviewDao.insert(review2);


        Integer fk_memberId = memberDao.selectAll().get(0).getId();
        Integer fk_reviewId = reviewDao.selectAll().get(0).getId();

        // member_review 테이블 관련 추가
        MemberReviewsDto mrdto = new MemberReviewsDto(null,fk_memberId,fk_reviewId);
        memberDao.insertMemberReview(mrdto);

        // 리뷰 삭제 실행
        int rowsDeleted = memberDao.deleteMemberReview(userId);

        // 삭제 확인
        assertTrue(rowsDeleted == 1);


    }

// 회원의 즐겨찾기 조회 관련 TDD는 restaurantMapper 완성되고 하기


//    @Test
//    public void selectFavorites() {
//        // 회원, 리뷰, 관계 테이블 초기화
//        memberDao.deleteAll();
//        reviewDao.deleteAll();
//        memberDao.deleteAllMemberReview();
//
//        // 회원 추가
//        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-2222-2222","now()",123);
//        memberDao.insert(member1);
//        String userId = memberDao.selectAll().get(0).getUser_id();
//
//        // 레스토랑 추가
//
//    }
//
//    @Test
//    public void deleteFavorite() {
//    }
}