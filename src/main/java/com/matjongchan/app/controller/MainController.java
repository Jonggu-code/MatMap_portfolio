package com.matjongchan.app.controller;


import com.matjongchan.app.domain.dto.SearchCondition;
import com.matjongchan.app.domain.dto.SimpleRestaurant;
import com.matjongchan.app.service.MemberService;
import com.matjongchan.app.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.Authenticator;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final RestaurantService restaurantService;
    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model, SearchCondition searchCondition) {
        if(searchCondition.getKeyword() == null){
            searchCondition = SearchCondition.builder()
                    .offset(1)
                    .option("P")
                    .category("한식")
                    .c_address(null)
                    .page_size(20)
                    .build();
        }
        List<SimpleRestaurant> simpleRestaurants = restaurantService.SRTotalSearch(searchCondition);
        model.addAttribute("sr",simpleRestaurants);
        return "index";
    }

    @PostMapping("/")
    public String searchBar(Model model, String keyword) {
        model.addAttribute("a_keyword",keyword);
        return "index";
    }

    @ResponseBody
    @PostMapping("/search/keyword")
    public ResponseEntity<List<SimpleRestaurant>> searchKeyword(HttpSession session, @RequestBody SearchCondition searchCondition) {
        if(searchCondition.getKeyword() == null) {
            searchCondition = SearchCondition.builder()
                    .offset(1)
                    .option("P")
                    .category("한식")
                    .c_address(null)
                    .page_size(20)
                    .build();
        }
        List<SimpleRestaurant> simpleRestaurantList = restaurantService.SRRealTotalSearch(searchCondition);
        return new ResponseEntity<>(simpleRestaurantList, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/search/category")
    public ResponseEntity<List<SimpleRestaurant>> searchCategory(HttpSession session, @RequestBody SearchCondition searchCondition) {
        if(searchCondition.getOption() == null && searchCondition.getCategory() == null && searchCondition.getC_address() == null){
            searchCondition = SearchCondition.builder()
                    .offset(1)
                    .option("P")
                    .category("한식")
                    .c_address(null)
                    .page_size(20)
                    .build();
        }
        return new ResponseEntity<>(restaurantService.SRTotalSearch(searchCondition), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/search/near")
    public ResponseEntity<List<SimpleRestaurant>> searchNear(HttpSession session, @RequestBody SearchCondition searchCondition) {
        return new ResponseEntity<>(restaurantService.SRNearSearch(searchCondition), HttpStatus.OK);
    }

    @GetMapping("/rank")
    public String rank(HttpSession session, Model model, SearchCondition searchCondition) {
        List<SimpleRestaurant> rankDescRestaurant = restaurantService.getRankDescRestaurant(searchCondition);
        model.addAttribute("rank_list",rankDescRestaurant);
        model.addAttribute("category","");
        return "rank_page_score";
    }

    @ResponseBody
    @GetMapping("/get/rank")
    public ResponseEntity<List<SimpleRestaurant>> getRank(HttpSession session, SearchCondition searchCondition) {
        if(searchCondition.getCategory().isEmpty()){
            searchCondition.setCategory(null);
        }

        return new ResponseEntity<>(restaurantService.getRankDescRestaurant(searchCondition),HttpStatus.OK);
    }
    
    //모두고려
    @ResponseBody
    @PostMapping("/search")
    public ResponseEntity<List<SimpleRestaurant>> search(HttpSession session, @RequestBody SearchCondition searchCondition) {

        return new ResponseEntity<>(restaurantService.getAllConsiderRestaurant(searchCondition),HttpStatus.OK);

    }

}
