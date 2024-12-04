package com.matjongchan.app.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchCondition {

    private Integer curr_page = 1;   //현재 연결되어있는 페이지번호
    private Integer offset = 0;     // 몇번째 정보부터 가져오기 시작할건지
    private Integer page_size = null;  // 한번에 몇개의 정보를 가져올건지
    private String option= null;       // 리뷰 갯수 많은순인지, 평균 평점 높은순으로 조회인지를 알기위한 속성
    private String keyword = null;    // 일반적인 통합검색을 위한 속성. 검색은 category + address + name 임.
    private String category = null;   //한식 일식 등등 음식의 카테고리별 조회를 위한 속성
    private String c_address = null;  //city_address  지역별 조회를 위한 속성
    private Double loc_nw_x = null;                // 현재 지도상 남서 끝 경도좌표
    private Double loc_nw_y = null;                // 현재 지도상 남서 끝 위도좌표
    private Double loc_se_x = null;                // 현재 지도상 북동 끝 경도좌표
    private Double loc_se_y = null;                // 현재 지도상 북동 끝 위도좌표
    private Integer category_num = null;

    /**
     * 메인페이지에서 식당관련 검색을 할때 사용할 생성자.
     * @param offset 몇번째 정보부터 가져올 것인지. (페이지네이션관련)
     * @param option 리뷰 갯수 순 = R, 평점 높은 순 = P 로 기입.
     * @param category 식당 카테고리(한식,일식 등)
     * @param c_address 도시별 조회를 위한 카테고리(강남구, 00구 등)
     */
    public SearchCondition(Integer offset, String option, String category, String c_address) {
        this.offset = offset;
        this.option = option;
        this.category = category;
        this.c_address = c_address;
    }
    
    //현재위치에서 재검색
    public SearchCondition(Integer offset, String option, String category,
                           String c_address, Double loc_nw_x, Double loc_nw_y, Double loc_se_x, Double loc_se_y) {
        this.offset = offset;
        this.option = option;
        this.category = category;
        this.c_address = c_address;
        this.loc_nw_x = loc_nw_x;
        this.loc_nw_y = loc_nw_y;
        this.loc_se_x = loc_se_x;
        this.loc_se_y = loc_se_y;
    }
    //키워드로 검색
    public SearchCondition(Integer offset, String keyword) {
        this.offset = offset;
        this.keyword = keyword;
    }
    @Builder
    public SearchCondition(Integer offset, Integer page_size, String option, String keyword, String category, String c_address, Double loc_nw_x, Double loc_nw_y, Double loc_se_x, Double loc_se_y) {
        this.offset = offset;
        this.page_size = page_size;
        this.option = option;
        this.keyword = keyword;
        this.category = category;
        this.c_address = c_address;
        this.loc_nw_x = loc_nw_x;
        this.loc_nw_y = loc_nw_y;
        this.loc_se_x = loc_se_x;
        this.loc_se_y = loc_se_y;
    }
}
