package com.matjongchan.app.controller;


import com.matjongchan.app.domain.dto.SearchCondition;
import com.matjongchan.app.domain.dto.SimpleRestaurant;
import com.matjongchan.app.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final RestaurantService restaurantService;

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model, SearchCondition searchCondition) {
        if(searchCondition.getKeyword() == null){
            searchCondition = SearchCondition.builder()
                    .offset(1)
                    .option("P")
                    .category(null)
                    .c_address(null)
                    .page_size(20)
                    .build();
        }
        List<SimpleRestaurant> simpleRestaurants = restaurantService.SRTotalSearch(searchCondition);
        model.addAttribute("sr",simpleRestaurants);
        return "index";
    }

    @ResponseBody
    @PostMapping("/search/keyword")
    public ResponseEntity<List<SimpleRestaurant>> searchKeyword(HttpSession session, @RequestBody SearchCondition searchCondition) {
        if(searchCondition.getKeyword() == null) {
            searchCondition = SearchCondition.builder()
                    .offset(1)
                    .option("P")
                    .category(null)
                    .c_address(null)
                    .page_size(20)
                    .build();
        }
        log.info("키워드 검색중 ... keyword => "+searchCondition.getKeyword());
        List<SimpleRestaurant> simpleRestaurantList = restaurantService.SRRealTotalSearch(searchCondition);
        log.info("몇개 검색됨? -> "+simpleRestaurantList.size());
        return new ResponseEntity<>(simpleRestaurantList, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/search/category")
    public ResponseEntity<List<SimpleRestaurant>> searchCategory(HttpSession session, @RequestBody SearchCondition searchCondition) {
        if(searchCondition.getOption() == null && searchCondition.getCategory() == null && searchCondition.getC_address() == null){
            searchCondition = SearchCondition.builder()
                    .offset(1)
                    .option("P")
                    .category(null)
                    .c_address(null)
                    .page_size(20)
                    .build();
        }
        return new ResponseEntity<>(restaurantService.SRTotalSearch(searchCondition), HttpStatus.OK);
    }

}
