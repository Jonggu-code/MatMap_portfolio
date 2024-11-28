package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.domain.entity.ReviewMenuDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReviewMenuDaoImplTest {
    @Autowired
    ReviewMenuDao reviewMenuDao;

    @Test
    public void count() {
        reviewMenuDao.deleteAll(); // 싹 다 비우고
        assertTrue(reviewMenuDao.count()==0); // 0개 남았는지 체크
    }

    @Test
    public void countR() {
        reviewMenuDao.deleteAll();
        insert();
        assertTrue(reviewMenuDao.countR(1)==2);
    }

    @Test // 전체 삭제 테스트
    public void deleteAll() {
        reviewMenuDao.deleteAll();
        assertTrue(reviewMenuDao.count()==0);
    }


    @Test // 하나 삭제 테스트
    public void delete() {
        reviewMenuDao.deleteAll();
        insert();
        reviewMenuDao.delete(1);

        assertTrue(reviewMenuDao.count() == 1);

    }
    @Test // 하나 삽입 테스트
    public void insert() {
        reviewMenuDao.deleteAll();
        ReviewMenuDto reviewMenuDto = new ReviewMenuDto();

        reviewMenuDto = new ReviewMenuDto(1,1);
        assertTrue(reviewMenuDao.insert(reviewMenuDto) == 1); // 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewMenuDao.count()==1); // 비우고 1명 넣은거니까 1 와야함

        reviewMenuDto = new ReviewMenuDto(1,2);
        assertTrue(reviewMenuDao.insert(reviewMenuDto) == 1);// 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewMenuDao.count()==2); // 저 위에서 한명 넣고, 방금 또 넣었으니까 2 와야함
    }

    @Test
    public void selectAll() {
        reviewMenuDao.deleteAll();
        insert();

        List<ReviewMenuDto> list = reviewMenuDao.selectAll();
        assertTrue(list.size() == 2);

        assertTrue(list.size() > 0);
    }

    @Test
    public void selectR() {
        reviewMenuDao.deleteAll();
        insert();

        List<ReviewMenuDto> list = reviewMenuDao.selectR(1);
        assertTrue(list.size() == 2);

        assertTrue(list.size() > 0);
    }

}