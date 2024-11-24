package com.matjongchan.app.domain.dto;

import lombok.*;
import org.springframework.web.util.UriComponentsBuilder;


@NoArgsConstructor
@Getter
@Setter
public class SearchCondition {

    private Integer currPage = 1;
    private Integer offset = 0;
    private Integer pageSize = 10;
    private String option="";
    private String keyword = "";
    private String category = "";   //한식 일식 등등
    private String c_address = "";  //city_address
    private String d_address = "";  //detail_address

    public SearchCondition(Integer currPage, Integer pageSize) {
        this(currPage, pageSize, "", "");
    }

    public SearchCondition(Integer currPage, Integer pageSize, String option, String keyword) {
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.option = option;
        this.keyword = keyword;
    }

    /////////////////////////////
    // 페이지에서 데이터 뽑기 (?currPage=7&pageSize=10&option=T&keyword=초밥) 부분들
    // 검색 하고 페이징 눌렀을때 글수정, 삭제 같은거 눌렀을때 처럼 기존 검색 옵션을 유지해야 하는경우 사용할거임
    public String getQueryString(Integer currPage) {
        return UriComponentsBuilder.newInstance()
                .queryParam("currPage", currPage)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    @Builder
    public SearchCondition(Integer currPage, Integer offset, Integer pageSize,String option,
                           String keyword, String category, String c_address, String d_address) {
        this.currPage = currPage;
        this.offset = offset;
        this.pageSize = pageSize;
        this.option = option;
        this.keyword = keyword;
        this.category = category;
        this.c_address = c_address;
        this.d_address = d_address;
    }
}
