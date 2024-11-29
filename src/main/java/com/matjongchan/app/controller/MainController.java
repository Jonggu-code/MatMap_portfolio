package com.matjongchan.app.controller;


import com.matjongchan.app.domain.dto.SearchCondition;
import com.matjongchan.app.domain.dto.SimpleRestaurant;
import com.matjongchan.app.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class MainController {

    private final RestaurantService restaurantService;

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model, SearchCondition searchCondition) {
        if(searchCondition.getPage_size() == null){
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



}
