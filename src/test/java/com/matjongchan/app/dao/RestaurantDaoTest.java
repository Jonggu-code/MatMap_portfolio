package com.matjongchan.app.dao;

import com.matjongchan.app.domain.dto.SearchCondition;
import com.matjongchan.app.domain.dto.TotalCount;
import com.matjongchan.app.domain.entity.RestaurantDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class RestaurantDaoTest {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private ReviewDao reviewDao;

    @Before
    public void setUp() throws Exception {
        restaurantDao.truncateAll();
        reviewDao.deleteAll();

        RestaurantDto test1 = RestaurantDto.builder()
                .name("돝고기506")
                .c_address("강남구")
                .d_address("역삼로17길 53 서연빌딩 2,3층")
                .number("0269339506")
                .reservation("예약가능")
                .loc_x(37.4982482701982)
                .loc_y(127.0362061529490)
                .search_tag("돝고기506강남구역삼로17길 53 서연빌딩 2,3층한식")
                .fk_category(1)
                .build();

        RestaurantDto test2 = RestaurantDto.builder()
                .name("대우부대찌개")
                .c_address("강남구")
                .d_address("테헤란로25길 34 1층")
                .number("025521663")
                .loc_x(37.5027556000000)
                .loc_y(127.0352544000000)
                .search_tag("대우부대찌개강남구테헤란로25길 34 1층한식")
                .fk_category(1)
                .build();

        RestaurantDto test3 = RestaurantDto.builder()
                .name("마노디셰프 강남점")
                .c_address("서초구")
                .d_address("테헤란로 129 강남N타워 지하1층")
                .number("025535311")
                .loc_x(37.4997777000000)
                .loc_y(127.0324107000000)
                .search_tag("마노디셰프 강남점강남구테헤란로 129 강남N타워 지하1층양식")
                .fk_category(2)
                .build();

        insertRestaurant(test1);
        insertRestaurant(test2);
        insertRestaurant(test3);

        ReviewDto reviewDto1 = new ReviewDto("테스트리뷰어1", "테스트제목1", "테스트내용1",
                1.1, 1.1, 1.1, 2.5, 1);
        ReviewDto reviewDto2 = new ReviewDto("테스트리뷰어2", "테스트제목2", "테스트내용2",
                2.2, 2.2, 2.2, 3.5, 2);
        ReviewDto reviewDto3 = new ReviewDto("테스트리뷰어3", "테스트제목3", "테스트내용3",
                3.3, 3.3, 3.3, 4.5, 3);

        insertReview(reviewDto1);
        insertReview(reviewDto2);
        insertReview(reviewDto3);
    }

    @Test
    public void getRestaurantCount() {
        int count = restaurantDao.getRestaurantCount();
        Assert.assertEquals(count,3);
    }

    @Test
    public void getRestaurantById() {
        RestaurantDto restaurantDto = restaurantDao.getRestaurantById(1);
        Assert.assertEquals(restaurantDto.getName(),"돝고기506");
    }

    @Test
    public void getRestaurantByName() {
        RestaurantDto restaurantDto = restaurantDao.getRestaurantByName("마노디셰프 강남점");
        Assert.assertEquals(restaurantDto.getD_address(),"테헤란로 129 강남N타워 지하1층");
    }

    @Test
    public void updateRestaurant() {
        RestaurantDto dto = restaurantDao.getRestaurantById(1);
        dto.updateInfo(dto.getName(),"010-0000-1111", dto.getReservation(), dto.getMemo());
        restaurantDao.updateRestaurant(dto);

        restaurantDao.getRestaurantById(1);
        Assert.assertEquals(dto.getNumber(),"010-0000-1111");

    }

    @Test
    public void deleteRestaurant() {
        restaurantDao.deleteRestaurant(1);

        int count = restaurantDao.getRestaurantCount();
        Assert.assertEquals(count,2);
    }

    @Test
    public void updateTotalCount(){
        // 1번식당을 fk_restaurant_id 로 가지고있는 리뷰를 전체조회
        int restaurant_id = 1;
        int fk_restaurant_id = 1;
        TotalCount before_Total_count = new TotalCount(restaurant_id, fk_restaurant_id, null, null);

        TotalCount after_total_count = restaurantDao.getTotalCount(before_Total_count);

        after_total_count.setRestaurant_id(restaurant_id);
        after_total_count.setFk_restaurant(fk_restaurant_id);

        restaurantDao.updateTotalCount(after_total_count);

        RestaurantDto dto = restaurantDao.getRestaurantById(restaurant_id);

        Assert.assertEquals(2.5, Optional.ofNullable(dto.getTotal_score_count()).orElse(0.0),0.01);
    }

    // 주어진 검색 조건으로 식당 검색.
    @Test
    public void totalSearch() {
        SearchCondition searchCondition = new SearchCondition(0, null, null, "강남구");
        //강남구에 해당하는 것만조회
        List<RestaurantDto> restaurantDtos = restaurantDao.totalSearch(searchCondition);
        Assert.assertEquals(restaurantDtos.size(),2);

    }
    // 주어진 좌표값 사이의 식당을 조회
    @Test
    public void nearSearch() {
        restaurantDao.getRestaurantAll();

        SearchCondition searchCondition = new SearchCondition(0, null, null, null,37.0,127.0,37.5,127.04);
        List<RestaurantDto> restaurantDtos = restaurantDao.nearSearch(searchCondition);
        for (RestaurantDto restaurantDto : restaurantDtos) {
            log.info(restaurantDto.toString());
        }
        Assert.assertEquals(restaurantDtos.size(),2);
    }

//    검색창에 입력된 검색 키워드로 조회.
    @Test
    public void realTotalSearch() {
        SearchCondition searchCondition = new SearchCondition(0, "테헤란로");
        List<RestaurantDto> restaurantDtos = restaurantDao.realTotalSearch(searchCondition);

        Assert.assertEquals(restaurantDtos.size(),2);
    }

    public void insertRestaurant(RestaurantDto restaurantDto) {
        restaurantDao.insertRestaurant(restaurantDto);
    }

    public void insertReview(ReviewDto reviewDto) {
        reviewDao.insert(reviewDto);
    }
}