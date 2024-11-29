package com.matjongchan.app.controller;

import com.matjongchan.app.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
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




}
