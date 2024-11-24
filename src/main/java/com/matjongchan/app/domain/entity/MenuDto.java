package com.matjongchan.app.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuDto {

    private Integer id;
    private String name;
    private String price;
    private Integer fk_restaurant_id;
    private Integer fk_image_id;

}
