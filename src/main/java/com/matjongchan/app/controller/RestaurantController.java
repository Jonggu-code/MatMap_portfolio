package com.matjongchan.app.controller;

import com.matjongchan.app.dao.ReviewDao;
import com.matjongchan.app.domain.dto.RestaurantDetail;
import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.dto.ReviewDetailSearchCondition;
import com.matjongchan.app.domain.entity.OtherImageDto;
import com.matjongchan.app.domain.entity.RestaurantDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.domain.entity.ReviewMenuDto;
import com.matjongchan.app.service.RestaurantService;
import com.matjongchan.app.service.ReviewMenuService;
import com.matjongchan.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class RestaurantController {

    @Autowired
    ReviewMenuService reviewMenuService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewDao reviewDao;

    private final RestaurantService restaurantService;

    @GetMapping(value = "/test1",produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<String>> test1(){
        List<String> testList = new ArrayList<>();
        testList.add("나는테");
        testList.add("스트를");
        testList.add("하는데");
        return ResponseEntity.ok(testList);
    }

    @GetMapping("/detail/{id}")
    public String restaurantDetail(@PathVariable("id") int id, RestaurantDto restaurantDto, ReviewDto reviewDto, Model m, ReviewMenuDto reviewMenuDto, OtherImageDto otherImageDto){

        m.addAttribute("id", id);

        RestaurantDetail restaurantDetail = restaurantService.getRestaurantDetail(id);
        m.addAttribute("restaurantDetail", restaurantDetail);

        Map<String, Object> param = new HashMap<>();
        param.put("offset", 0);
        param.put("limit", 5);
        param.put("fk_restaurant_id", id);

        List<ReviewDto> reviews = null;

        reviews = reviewService.getListR(id);
        m.addAttribute("reviews", reviews);

        for (ReviewDto review : reviews) {
            List<String> menuNames = reviewMenuService.getMenuNames(review.getId());
            review.setMenuNames(menuNames); // ReviewDto에 메뉴 리스트 추가

//            Double tasteS = reviewDao.tasteS(review);
//            Double cleanS = reviewDao.cleanS(review);
//            Double kindS = reviewDao.kindS(review);
//            Double totalS = reviewDao.totalS(review);
        }

//        for (ReviewDto review : reviews) {
////            List<OtherImageDto> otherImages = otherImageService.
//        }

        m.addAttribute("restaurantDto", restaurantDto);
        m.addAttribute("reviewDto", reviewDto);

        Integer reviewCount = reviewService.getCountR(id);
        m.addAttribute("reviewCount", reviewCount);

        Double taste_score = reviewService.getTasteAvg(id);
        Double clean_score = reviewService.getCleanAvg(id);
        Double kind_score = reviewService.getKindAvg(id);
        Double total_score = reviewService.getTotalAvg(id);

        m.addAttribute("taste_score", taste_score);
        m.addAttribute("clean_score", clean_score);
        m.addAttribute("kind_score", kind_score);
        m.addAttribute("total_score", total_score);

        m.addAttribute("reviewMenuDto", reviewMenuDto);
        m.addAttribute("otherImageDto", otherImageDto);
        return "detail";

    }

    @ResponseBody
    @GetMapping("/detail/review/{id}")
    public ResponseEntity<List<ReviewDetail>> restaurantDetailReview(@PathVariable("id") int id){

        ReviewDetailSearchCondition reviewDetailSearchCondition = new ReviewDetailSearchCondition(id,1,3);
        List<ReviewDetail> reviewDetails = restaurantService.getReviewDetails(reviewDetailSearchCondition);

        return new ResponseEntity<>(reviewDetails, HttpStatus.OK);

    }

}
