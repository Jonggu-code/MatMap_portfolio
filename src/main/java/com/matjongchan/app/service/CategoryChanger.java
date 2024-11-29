package com.matjongchan.app.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
/**
 *  카테고리 변경을 위한 객체
 */
public class CategoryChanger {

    private static final List<String> category =
            List.of("한식","양식","중식","일식","카페","치킨","피자","패스트푸드","분식","술집");

    /**
     * 숫자형으로 되어있는 카테고리를 문자로 변경
     * ex) input 1
     *     output "한식"
     * @param number
     * @return
     */
    public static String numberIntoCategory(int number) {
        return category.get(number);
    }

    /**
     * 문자형으로 되어있는 카테고리를 숫자로 변경
     * ex) input "한식"
     *     output 1
     * @param category
     * @return
     */
    public static int categoryIntoNumber(String category) {
        return category.indexOf(category);
    }


}
