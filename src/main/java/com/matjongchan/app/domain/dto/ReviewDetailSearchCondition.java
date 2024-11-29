package com.matjongchan.app.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDetailSearchCondition {

    private Integer restaurant_id;
    private Integer offset = 0;     // 몇번째 정보부터 가져오기 시작할건지
    private Integer page_size = 3;  // 한번에 몇개의 정보를 가져올건지

}
