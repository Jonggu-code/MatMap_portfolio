package com.matjongchan.app.controller;

import com.matjongchan.app.domain.dto.RestaurantDetail;
import com.matjongchan.app.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class RestaurantController {

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
    public String restaurantDetail(@PathVariable("id") int id, Model model){

        RestaurantDetail restaurantDetail = restaurantService.getRestaurantDetail(id);
        model.addAttribute("rd",restaurantDetail);

        return "detail";

    }


}
