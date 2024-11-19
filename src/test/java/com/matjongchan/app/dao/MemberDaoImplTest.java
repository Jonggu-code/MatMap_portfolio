package com.matjongchan.app.dao;

import com.matjongchan.app.domain.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
/* 할 일: MemberDaoImpl 로직 제대로 동작하는지 확인
 * 다하고 나면, service 만들러 가기
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDaoImplTest {


    @Autowired
    MemberDao memberDao;

    @Test
    public void count() {
        memberDao.deleteAll();
        MemberDto memberDto = new MemberDto(null, "user1", "pass1", "User1", "Address1", "user1@example.com", "Intro1", "12345", "now()", 123);
        int count = memberDao.insert(memberDto);
//        System.out.println("aa: " + memberDao.insert(memberDto));
        assertTrue(count == 1);
    }


    @Test
    public void insert() {
        memberDao.deleteAll();
        MemberDto member = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "010-2222-2222", "now()", 22);
        int insert = memberDao.insert(member);

        assertTrue(insert == 1);
    }

    @Test
    public void selectAll() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "010-2222-2222", "now()", 22);
        memberDao.insert(member1);

        MemberDto member2 = new MemberDto(null, "user3", "pass3", "User3", "Address3", "user3@example.com", "Intro3", "010-3333-3333", "now()", 33);
        memberDao.insert(member2);

        assertTrue(memberDao.selectAll().size() == 2);
    }

    @Test
    public void select() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user2", "pass2", "User2", "Address2", "user2@example.com", "Intro2", "010-2222-2222", "now()", 22);
        memberDao.insert(member1);

        MemberDto member2 = new MemberDto(null, "user3", "pass3", "User3", "Address3", "user3@example.com", "Intro3", "010-3333-3333", "now()", 33);
        memberDao.insert(member2);

        assertTrue(memberDao.select(1).getId() == 1);
    }

    @Test
    public void delete() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user5", "pass5", "User5", "Address5", "user5@example.com", "Intro5", "010-5555-5555", "now()", 55);
        memberDao.insert(member1);
        int delete = memberDao.delete(1);
        assertTrue(delete == 1);

    }

    @Test
    public void update() {
        memberDao.deleteAll();
        MemberDto member1 = new MemberDto(null, "user0", "pass0", "User0", "Address0", "user0@example.com", "Intro0", "010-0000-0000", "now()", 00);
        memberDao.insert(member1);

        Integer id = memberDao.selectAll().get(0).getId();


        MemberDto updateMember1 = new MemberDto(id, "updateUser5", "updatePass5", "updateUser5", "updateAddress5", "updateUser5@example.com", "updateIntro5", "010-5555-5555", "now()", 55);
        int update = memberDao.update(updateMember1);

        assertTrue(update == 1);
    }
}