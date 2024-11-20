package com.matjongchan.app.dao;

import com.matjongchan.app.domain.ReviewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;

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
    public void countR() {
        reviewDao.deleteAll();
        insertALot();
        assertTrue(reviewDao.countR(1)==30);
    }

    @Test
    public void deleteAll() {
        reviewDao.deleteAll();
        assertTrue(reviewDao.count()==0);
    }


    @Test
    public void delete() {
        reviewDao.deleteAll();
        insert();
        reviewDao.delete(1,"asdf");

        assertTrue(reviewDao.count() == 1);

    }

    @Test
    public void insert() {
        reviewDao.deleteAll();
        ReviewDto reviewDto = new ReviewDto();


        reviewDto = new ReviewDto("asdf","title1", "content1", 5,3.2,5, 4.4f,1,1);

        assertTrue(reviewDao.insert(reviewDto) == 1); // 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewDao.count()==1); // 비우고 1명 넣은거니까 1 와야함

        reviewDto = new ReviewDto("qwer","title2", "content2", 5,3,5, 4.6, 2,2);
        assertTrue(reviewDao.insert(reviewDto) == 1);// 한명 넣었으니깐 1 오면 된거임
        assertTrue(reviewDao.count()==2); // 저 위에서 한명 넣고, 방금 또 넣었으니까 2 와야함
    }

    @Test
    public void insertALot(){

        reviewDao.deleteAll();
        ReviewDto reviewDto;
        for (int i = 1; i <= 30; i++) {
            reviewDto = new ReviewDto("asdf","title"+i, "content"+i, 5,3.2,5, 4.4f,1,1);
            reviewDao.insert(reviewDto);
        }
        for (int i = 1; i <= 30; i++) {
            reviewDto = new ReviewDto("asdf","title"+(i+30), "content"+i, 5,3.2,5, 4.4f,2,1);
            reviewDao.insert(reviewDto);
        }
        for (int i = 1; i <= 30; i++) {
            reviewDto = new ReviewDto("asdf","title"+(i+60), "content"+i, 5,3.2,5, 4.4f,3,1);
            reviewDao.insert(reviewDto);
        }
        assertTrue(reviewDao.count()==90); // 비우고 1명 넣은거니까 1 와야함
    }



    @Test
    public void selectAll() {
        reviewDao.deleteAll();
        insert();

        List<ReviewDto> list = reviewDao.selectAll();
        assertTrue(list.size() == 2);

        assertTrue(list.size() > 0);
    }
    @Test
    public void selectR() {
        reviewDao.deleteAll();
        insertALot();

        List<ReviewDto> list = reviewDao.selectR(1);
        assertTrue(list.size() == 30);

        assertTrue(list.size() > 0);
    }

    @Test
    public void select() {
        reviewDao.deleteAll();

        ReviewDto reviewDto = new ReviewDto("asdf","title1", "content1", 5,3,5, 4,1,1);

        reviewDao.insert(reviewDto);

        Integer id = reviewDao.selectAll().get(0).getId();
        ReviewDto reviewDto2 = reviewDao.select(id);

        assertTrue(Objects.equals(reviewDto2.getId(), id));
    }

    @Test
    public void update() {
        reviewDao.deleteAll();
        insert();

        ReviewDto reviewDto = new ReviewDto("asdf","title1", "content1", 5,3,5, 4,1,1);
        reviewDao.insert(reviewDto);

        Integer id = reviewDao.selectAll().get(0).getId();
        reviewDto.setId(id);
        reviewDto.setTitle("new Title");
        assertTrue(reviewDao.update(reviewDto) == 1);


    }
}