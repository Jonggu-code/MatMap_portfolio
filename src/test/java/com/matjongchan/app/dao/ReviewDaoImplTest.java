package com.matjongchan.app.dao;

import com.matjongchan.app.domain.ReviewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReviewDaoImplTest {

    @Autowired
    ReviewDao reviewDao;

    @Test
    public void count() {
        // Test1
        reviewDao.deleteAll(); // 싹 다 비우고
        assertTrue(reviewDao.count()==0); // 0개 남았는지 체크
    }

    @Test
    public void deleteAll() {
        reviewDao.deleteAll();
        assertTrue(reviewDao.count()==0);
    }

    @Test
    public void insert() {
        reviewDao.deleteAll();
        ReviewDto reviewDto = new ReviewDto();

        reviewDto = new ReviewDto("asdf","title1", "content1", 5,3,5, 4,1,1);

        assertTrue(reviewDao.insert(reviewDto) == 1); // 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewDao.count()==1); // 비우고 1명 넣은거니까 1 와야함

        reviewDto = new ReviewDto("qwer","title2", "content2", 5,3,5, 4, 2,2);
        assertTrue(reviewDao.insert(reviewDto) == 1);// 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewDao.count()==2); // 저 위에서 한명 넣고, 방금 또 넣었으니까 2 와야함
    }

    @Test
    public void selectAll() {
        reviewDao.deleteAll();
        insert();

        List<ReviewDto> list = reviewDao.selectAll();
        assertTrue(list.size() == 2);

        assertTrue(list.size() > 0);
    }

}