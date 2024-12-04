package com.matjongchan.app.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
/**
 *  카테고리 변경을 위한 객체
 */
public class CategoryChanger {

    private static final List<String> categories =
            List.of("", "한식", "양식", "중식", "일식", "카페", "치킨", "피자", "패스트푸드", "분식", "술집");

    /**
     * 숫자형으로 되어있는 카테고리를 문자로 변경
     * ex) input 1
     *     output "한식"
     *
     * @param number 카테고리 숫자
     * @return 카테고리 이름
     */
    public static String numberIntoCategory(int number) {
        if (number < 0 || number >= categories.size()) {
            throw new IllegalArgumentException("Invalid category number: " + number);
        }
        return categories.get(number);
    }

    /**
     * 문자형으로 되어있는 카테고리를 숫자로 변경
     * ex) input "한식"
     *     output 1
     *
     * @param category 카테고리 이름
     * @return 카테고리 숫자
     */
    public static Integer categoryIntoNumber(String category) {
        if (category == null) {
            return null;
        }
        int index = categories.indexOf(category);
        return index == -1 ? null : index;
    }


}
