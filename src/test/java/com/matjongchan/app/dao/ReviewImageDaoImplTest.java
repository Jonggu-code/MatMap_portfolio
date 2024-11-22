package com.matjongchan.app.dao;

import com.matjongchan.app.domain.ReviewDto;
import com.matjongchan.app.domain.ReviewImageDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReviewImageDaoImplTest {
    @Autowired
    ReviewImageDao reviewImageDao;

    @Test // 전체 이미지 count 테스트
    public void count() {
        // Test1
        reviewImageDao.deleteAll(); // 싹 다 비우고
        assertTrue(reviewImageDao.count()==0); // 0개 남았는지 체크
    }

    @Test // 특정 리뷰의 사진 개수 count 테스트
    public void countR() {
        reviewImageDao.deleteAll();
        insert();
        assertTrue(reviewImageDao.countR(1)==2);
    }

    @Test // 전체 삭제 테스트
    public void deleteAll() {
        reviewImageDao.deleteAll();
        assertTrue(reviewImageDao.count()==0);
    }

    @Test // 하나 삽입 테스트
    public void insert() {
        reviewImageDao.deleteAll();
        ReviewImageDto reviewImageDto;

        // die Miete

        // 이사 관련 단어

        // 이사 -> 세입자

        

        for(int i = 1; i<3; i++){
            reviewImageDto = new ReviewImageDto(null,"사진","pp/"+i, i,1,2);
            reviewImageDao.insert(reviewImageDto);
        }

        assertTrue(reviewImageDao.count()==2);
    }
}