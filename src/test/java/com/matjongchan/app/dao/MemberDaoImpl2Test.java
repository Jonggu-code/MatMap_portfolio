//package com.matjongchan.app.dao;
//
//import com.matjongchan.app.domain.MemberDto;
//import com.matjongchan.app.domain.MemberImageDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class MemberDaoImpl2Test {
//
//    @Autowired
//    MemberDao2 memberDao2;
//
//    @Test
//    public void count() {
//        System.out.println("여기000");
//        memberDao2.deleteAll();
//        System.out.println("여기111");
//        MemberDto memberDto = new MemberDto(null, "user1", "pass1", "User1", "Address1", "user1@example.com", "Intro1", "남", 13, "12345", "now()", 123);
//        memberDao2.insert(memberDto);
//        System.out.println("여기222");
//        int count = memberDao2.count();
//        assertTrue(count == 1);
//
//    }
//
//    @Test
//    public void insert() {
//        memberDao2.deleteAll();
//        MemberDto member = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-0000-0000", "now()", 123);
//        int insert = memberDao2.insert(member);
//        assertTrue(insert == 1);
//    }
//
//    @Test
//    public void selectAll() {
//        memberDao2.deleteAll();
//        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-2222-2222", "now()", 123);
//        memberDao2.insert(member1);
//        MemberDto member2 = new MemberDto(null, "user3", "pass3", "User3", "Address3", "user3@example.com", "Intro3", "남", 25, "010-3333-3333", "now()", 123);
//        memberDao2.insert(member2);
//        assertTrue(memberDao2.selectAll().size() == 2);
//    }
//
//    @Test
//    public void select() {
//        memberDao2.deleteAll();
//        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "여", 19, "010-2222-2222", "now()", 123);
//        memberDao2.insert(member1);
//        MemberDto member2 = new MemberDto(null, "user3", "pass3", "User3", "Address3", "user3@example.com", "Intro3", "남", 25, "010-3333-3333", "now()", 123);
//        memberDao2.insert(member2);
//        assertTrue(memberDao2.select("user3").getAge() == 25);
//    }
//
//    @Test
//    public void delete() {
//        memberDao2.deleteAll();
//        MemberDto member1 = new MemberDto(null, "user5", "pass5", "User5", "Address5", "user5@example.com", "Intro5", "여", 35, "010-5555-5555", "now()", 123);
//        memberDao2.insert(member1);
//        int delete = memberDao2.delete("user5");
//        assertTrue(delete == 1);
//    }
//
//    @Test
//    public void update() {
//        memberDao2.deleteAll();
//        MemberDto member1 = new MemberDto(null, "user0", "pass0", "User0", "Address0", "user0@example.com", "Intro0", "남", 55, "010-6666-6666", "now()", 123);
//        memberDao2.insert(member1);
//        Integer id = memberDao2.selectAll().get(0).getId();
//        MemberDto updateMember1 = new MemberDto(id, "updateUser5", "updatePass5", "updateUser5", "updateAddress5", "updateUser5@example.com", "updateIntro5", "여", 45, "010-6666-6667", "now()", 123);
//        int update = memberDao2.update(updateMember1);
//        assertTrue(update == 1);
//    }
//
//    // 1) 프로필 이미지 추가
//    @Test
//    public void insertMemberImage() {
//        memberDao2.deleteAll();  // 기존 데이터 초기화
//
//        MemberImageDto imageDto = new MemberImageDto(1, "image1.jpg", "image_url", 1);
//        int insertImage = memberDao2.insertMemberImage(imageDto);
//        assertTrue(insertImage == 1);
//    }
//
//    // 2) 프로필 이미지 조회
//    @Test
//    public void selectMemberImage() {
//        memberDao2.deleteAll();  // 기존 데이터 초기화
//
//        MemberImageDto imageDto = new MemberImageDto(1, "image1.jpg", "image_url", 1);
//        memberDao2.insertMemberImage(imageDto);
//
//        MemberImageDto selectedImage = memberDao2.selectMemberImage(1);
//        assertNotNull(selectedImage);
//        assertEquals("image1.jpg", selectedImage.getName());
//    }
//
//    // 3) 프로필 이미지 수정
//    @Test
//    public void updateMemberImage() {
//        memberDao2.deleteAll();  // 기존 데이터 초기화
//
//        MemberImageDto imageDto = new MemberImageDto(1, "image1.jpg", "image_url", 1);
//        memberDao2.insertMemberImage(imageDto);
//
//        // 이미지 정보 수정
//        imageDto.setName("updated_image.jpg");
//        int updateImage = memberDao2.updateMemberImage(imageDto);
//        assertTrue(updateImage == 1);
//
//        // 수정된 이미지 정보 확인
//        MemberImageDto updatedImage = memberDao2.selectMemberImage(1);
//        assertEquals("updated_image.jpg", updatedImage.getName());
//    }
//
//    // 4) 프로필 이미지 삭제
//    @Test
//    public void deleteMemberImage() {
//        memberDao2.deleteAll();  // 기존 데이터 초기화
//
//        MemberImageDto imageDto = new MemberImageDto(1, "image1.jpg", "image_url", 1);
//        memberDao2.insertMemberImage(imageDto);
//
//        int deleteImage = memberDao2.deleteMemberImage(1);
//        assertTrue(deleteImage == 1);
//
//        // 삭제된 이미지 정보 확인
//        MemberImageDto deletedImage = memberDao2.selectMemberImage(1);
//        assertNull(deletedImage);
//    }
//}
