package com.matjongchan.app.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 식당 상세 페이지에서 사용할 객체.
 * 메뉴정보 + 해당 이미지
 */
public class MenuDetail {
    private Integer id;
    private String menu_name;
    private String menu_price;
    private String menu_memo;
    private Integer restaurant_id;
    private String menu_image_url;

}
