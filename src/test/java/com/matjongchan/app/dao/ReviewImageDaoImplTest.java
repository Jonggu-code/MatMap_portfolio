package com.matjongchan.app.dao;

import com.matjongchan.app.domain.ReviewDto;
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

    @Test // 전체 리뷰 count 테스트
    public void count() {
        // Test1
        reviewImageDao.deleteAll(); // 싹 다 비우고
        assertTrue(reviewImageDao.count()==0); // 0개 남았는지 체크
    }

    @Test // 특정 음식점 리뷰 count 테스트
    public void countR() {
        reviewImageDao.deleteAll();
//        insertALot();
        assertTrue(reviewImageDao.countR(1)==0);
    }

    @Test // 전체 삭제 테스트
    public void deleteAll() {
        reviewImageDao.deleteAll();
        assertTrue(reviewImageDao.count()==0);
    }

    @Test // 하나 삽입 테스트
    public void insert() {
        reviewDao.deleteAll();
        ReviewDto reviewDto = new ReviewDto();


        reviewDto = new ReviewDto("asdf","title1", "content1", 5,3.2,5, 4.4f,1);

        assertTrue(reviewDao.insert(reviewDto) == 1); // 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewDao.count()==1); // 비우고 1명 넣은거니까 1 와야함

        reviewDto = new ReviewDto("qwer","title2", "content2", 5,3,5, 4.6, 2);
        assertTrue(reviewDao.insert(reviewDto) == 1);// 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewDao.count()==2); // 저 위에서 한명 넣고, 방금 또 넣었으니까 2 와야함
    }


}