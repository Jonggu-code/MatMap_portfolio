package com.matjongchan.app.service;

import com.matjongchan.app.dao.OtherImageDao;
import com.matjongchan.app.dao.RestaurantDao;
import com.matjongchan.app.dao.ReviewMenuDao;
import com.matjongchan.app.domain.dto.*;
import com.matjongchan.app.domain.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDao restaurantDao;
    private final ReviewService reviewService;
    private final ReviewMenuDao reviewMenuDao;
    private final MemberService memberService;
    private final OtherImageDao otherImageDao;

    @Override
    public List<SimpleRestaurant> getSimpleRestaurant(SearchCondition searchCondition) {
        return List.of();
    }

    @Override
    public List<ReviewDetail> getReviewDetails(ReviewDetailSearchCondition condition) {
        List<ReviewDetail> review_detail_list = reviewService.getReviewDetails(condition);

        for (ReviewDetail reviewDetail : review_detail_list) {
            //이미지 조회후 객체에 넣어주기
            List<OtherImageDto> reviewImages = reviewMenuDao.getReviewImages(reviewDetail.getId());
            reviewDetail.setImage(reviewImages);
            //reviewer와 user_id로 조회후 객체에 넣어주기
            MemberDto member = memberService.getMember(reviewDetail.getReviewer());
            reviewDetail.setAge(member.getAge());
            reviewDetail.setGender(member.getGender());
        }

        return review_detail_list;
    }

    @Override
    public int getRestaurantCount() {
        return restaurantDao.getRestaurantCount();
    }

    @Override
    public RestaurantDto getRestaurantById(int id) {
        return restaurantDao.getRestaurantById(id);
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        return restaurantDao.getRestaurantByName(name);
    }

    @Override
    public List<RestaurantDto> totalSearch(SearchCondition searchCondition) {
        return restaurantDao.totalSearch(searchCondition);
    }

    @Override
    public List<RestaurantDto> nearSearch(SearchCondition searchCondition) {
        return restaurantDao.nearSearch(searchCondition);
    }

    @Override
    public List<RestaurantDto> realTotalSearch(SearchCondition searchCondition) {
        return restaurantDao.realTotalSearch(searchCondition);
    }

    @Override
    public int insertRestaurant(RestaurantDto restaurantDto) {
        return restaurantDao.insertRestaurant(restaurantDto);
    }

    @Override
    public int updateRestaurant(RestaurantDto restaurantDto) {
        return restaurantDao.updateRestaurant(restaurantDto);
    }

    @Override
    public int deleteRestaurant(int id) {
        return restaurantDao.deleteRestaurant(id);
    }

    @Override
    public TotalCount getTotalCount(TotalCount totalCount) {
        return restaurantDao.getTotalCount(totalCount);
    }

    @Override
    public int updateTotalCount(TotalCount totalCount) {
        return restaurantDao.updateTotalCount(totalCount);
    }

    @Override
    public List<RestaurantDto> getRestaurantAll() {
        return restaurantDao.getRestaurantAll();
    }

    @Override
    public List<SimpleRestaurant> SRTotalSearch(SearchCondition searchCondition) {
        Integer number = CategoryChanger.categoryIntoNumber(searchCondition.getCategory());
        searchCondition.setCategory_num(number);

        List<RestaurantDto> dtoList = restaurantDao.totalSearch(searchCondition);
        return getSimpleRestaurantList(dtoList, searchCondition);
    }

    @Override
    public List<SimpleRestaurant> SRNearSearch(SearchCondition searchCondition) {
        searchCondition.setCategory_num(CategoryChanger.categoryIntoNumber(searchCondition.getCategory()));
        List<RestaurantDto> dtoList = restaurantDao.nearSearch(searchCondition);

        return getSimpleRestaurantList(dtoList, searchCondition);
    }


    @Override
    public List<SimpleRestaurant> SRRealTotalSearch(SearchCondition searchCondition) {
        List<RestaurantDto> dtoList = restaurantDao.realTotalSearch(searchCondition);
        return getSimpleRestaurantList(dtoList, searchCondition);
    }


    @Override
    public RestaurantDetail getRestaurantDetail(int restaurantId) {

        RestaurantDto dto = restaurantDao.getRestaurantById(restaurantId);
        List<String> image_url_list = otherImageDao.getRestaurantImages(restaurantId)
                .stream()
                .map(OtherImageDto::getImg_url)
                .collect(Collectors.toList());

        BusinessHoursDto hoursDto = restaurantDao.getBusinessHours(restaurantId);
        List<MenuDetail> menuDetail = restaurantDao.getMenuDetail(restaurantId);
        SearchCondition searchCondition = SearchCondition.builder()
                .category(dto.getFk_category().toString())
                .offset(1)
                .page_size(3)
                .build();

        List<RestaurantDto> restaurant_dto_list = restaurantDao.getRelationRestaurant3(searchCondition);

        int i = 0;
        List<RelationRestaurant> relationRestaurantList = new ArrayList<>();
        for (RestaurantDto restaurantDto : restaurant_dto_list) {
            S s = S.builder().page_size(2).id(restaurantId).offset(i).build();
            i+=2;
            List<String> img_url_list = otherImageDao.getRestaurantImages2(s)
                    .stream()
                    .map(OtherImageDto::getImg_url)
                    .collect(Collectors.toList());

            RelationRestaurant relationRestaurant = RelationRestaurant.builder()
                    .restaurant_name(restaurantDto.getName())
                    .restaurant_total_score_count(restaurantDto.getTotal_score_count())
                    .restaurant_total_review_count(restaurantDto.getTotal_review_count())
                    .restaurant_address(restaurantDto.getC_address() + restaurantDto.getD_address())
                    .restaurant_image_url_list(img_url_list)
                    .build();

            relationRestaurantList.add(relationRestaurant);
        }


        List<Double> totalScore = reviewService.getTotalScore(restaurantId);
        List<Double> totalScoreCountList = reviewService.getTotalScoreCountList(restaurantId);

        return RestaurantDetail.builder()
                .restaurant_name(dto.getName())
                .restaurant_category(CategoryChanger.numberIntoCategory(dto.getFk_category()))
                .restaurant_total_score_count(dto.getTotal_score_count())
                .restaurant_total_review_count(dto.getTotal_review_count())
                .restaurant_address(dto.getC_address()+dto.getD_address())
                .restaurant_number(dto.getNumber())
                .restaurant_reservation(dto.getReservation())
                .restaurant_memo(dto.getMemo())
                .restaurant_image_url_list(image_url_list)
                .today_business_state("열림") //오늘 영업 유무..
                .business_hours_dto(hoursDto)
                .menu_detail_list(menuDetail) //메뉴 이름,가격,사진등등..
                .review_image_url_list(image_url_list)
                .relation_restaurant_list(relationRestaurantList)
                .restaurant_total_taste_score_count(totalScore.get(0))
                .restaurant_total_clean_score_count(totalScore.get(1))
                .restaurant_total_kind_score_count(totalScore.get(2))
                .restaurant_total_rating(TotalRating.listToTotalRating(totalScoreCountList))
                .build();
    }

    private static String getNowOpen(BusinessHoursDto hoursDto) {
        if(hoursDto == null){
            return "영업정보없음";
        }
        String business_hour = "";
        String now_open = "영업정보없음";
        Calendar calendar = Calendar.getInstance();
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                business_hour =  hoursDto.getSun();
            case 2:
                business_hour =  hoursDto.getMon();
            case 3:
                business_hour =  hoursDto.getTue();
            case 4:
                business_hour =  hoursDto.getWed();
            case 5:
                business_hour =  hoursDto.getThu();
            case 6:
                business_hour =  hoursDto.getFri();
            case 7:
                business_hour =  hoursDto.getSat();
        }
        if(StringUtils.isEmpty(business_hour)){
            business_hour="";
        }
        if(!business_hour.equals("")){
            int now_hour = calendar.get(Calendar.HOUR_OF_DAY);
            int now_minute = calendar.get(Calendar.MINUTE);
            int now_time = now_hour * 60 + now_minute;

            String[] business_hour_split = business_hour.split("~");
            int business_open_time = 0;
            int business_close_time = 0;
            String open = business_hour_split[0].strip().replaceAll("'", "");
            String[] split = open.split(":");
            business_open_time += Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);

            String close = business_hour_split[1].strip().replaceAll("'", "");
            split = open.split(":");
            business_close_time += Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);

            if(business_open_time < now_time && business_close_time > now_time){
                now_open = "영업종료";
            }else{
                now_open = "영업중";
            }
        }
        return now_open;
    }

    private List<SimpleRestaurant> getSimpleRestaurantList(List<RestaurantDto> dtoList, SearchCondition searchCondition) {
        List<SimpleRestaurant> simpleRestaurantList = new ArrayList<>();

        if(searchCondition.getCurr_page() == null){
            int total_count = restaurantDao.allConsiderSearchGetTotalCount(searchCondition);
            searchCondition.setTotal_count(total_count);
            searchCondition.setPage_size(20);
            searchCondition.setCurr_page(1);
            SearchCondition.updateCondition(searchCondition);
        }else{
            int total_count = restaurantDao.allConsiderSearchGetTotalCount(searchCondition);
            searchCondition.setTotal_count(total_count);
            searchCondition.setPage_size(searchCondition.getPage_size());
            searchCondition.setCurr_page(searchCondition.getCurr_page());
            SearchCondition.updateCondition(searchCondition);
        }

        for (RestaurantDto dto : dtoList) {
            SimpleRestaurant simpleRestaurant = SimpleRestaurant.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .image_url(getImgUrl(dto))
                    .address(dto.getC_address() + dto.getD_address())
                    .total_score_count(dto.getTotal_score_count() == null ? 0 : dto.getTotal_score_count())
                    .total_review_count(dto.getTotal_review_count() == null ? 0 : dto.getTotal_review_count())
                    .today_business_state(getNowOpen(restaurantDao.getBusinessHours(dto.getId())))
                    .reservation(dto.getReservation())
                    .number(dto.getNumber())
                    .loc_x(dto.getLoc_x())
                    .loc_y(dto.getLoc_y())
                    .menu_name_list(getMenu_name_list(dto))
                    .recentSimpleReview(getReviewDto(dto))
                    .searchCondition(searchCondition)
                    .build();
            simpleRestaurantList.add(simpleRestaurant);
        }

        return simpleRestaurantList;
    }

    public String getImgUrl(RestaurantDto dto) {
        List<OtherImageDto> images = otherImageDao.getRestaurantImages(dto.getId());
        return images.isEmpty() ? null : images.get(0).getImg_url();
    }

    public List<OtherImageDto> getImgUrlList(Integer id){
        List<OtherImageDto> images = otherImageDao.getRestaurantImages(id);
        return images.isEmpty() ? List.of() : images;
    }

    public List<String> getMenu_name_list(RestaurantDto dto) {
        return restaurantDao.getMenuDetail(
                        dto.getId())
                .stream()
                .map(MenuDetail::getMenu_name)
                .collect(Collectors.toList());
    }
    public List<String> getMenu_name_list(int restaurantId) {
        return restaurantDao.getMenuDetail(
                        restaurantId)
                .stream()
                .map(MenuDetail::getMenu_name)
                .collect(Collectors.toList());
    }

    private ReviewDto getReviewDto(RestaurantDto dto) {
        List<ReviewDto> listR = reviewService.getListR(dto.getId());
        return listR.isEmpty() ? null : listR.get(0);
    }


    @Override
    public List<SimpleRestaurant> getRankDescRestaurant(SearchCondition searchCondition) {
        searchCondition.setPage_size(10);
        Integer number = CategoryChanger.categoryIntoNumber(searchCondition.getCategory());
        searchCondition.setCategory_num(number);
        if(searchCondition.getOffset() == null){
            searchCondition.setOffset(0);
        }
        List<RestaurantDto> popularRestaurant = restaurantDao.getPopularRestaurant(searchCondition);
        List<SimpleRestaurant> simpleRestaurantList = new ArrayList<>();
        for (RestaurantDto dto : popularRestaurant) {
            SimpleRestaurant simpleRestaurant = SimpleRestaurant.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .category(CategoryChanger.numberIntoCategory(dto.getFk_category()))
                    .address(dto.getC_address() +" "+ dto.getD_address())
                    .other_image_list(getImgUrlList(dto.getId()))
                    .number(dto.getNumber())
                    .reservation(dto.getReservation())
                    .total_score_count(dto.getTotal_score_count())
                    .total_review_count(dto.getTotal_review_count())
                    .today_business_state(getNowOpen(restaurantDao.getBusinessHours(dto.getId())))
                    .memo(dto.getMemo())
                    .build();
            simpleRestaurantList.add(simpleRestaurant);
        }
        return simpleRestaurantList;
    }

    @Override
    public List<SimpleRestaurant> getAllConsiderRestaurant(SearchCondition searchCondition) {
        searchCondition.setCategory_num(CategoryChanger.categoryIntoNumber(searchCondition.getCategory()));

        if(searchCondition.getOffset() == null){
            searchCondition.setOffset(0);
        }

        List<RestaurantDto> restaurantDtoList = restaurantDao.getAllConsiderRestaurant(searchCondition);

        if(searchCondition.getCurr_page() == null){
            int total_count = restaurantDao.allConsiderSearchGetTotalCount(searchCondition);
            searchCondition.setTotal_count(total_count);
            searchCondition.setPage_size(20);
            searchCondition.setCurr_page(1);
            SearchCondition.updateCondition(searchCondition);
        }else{
            int total_count = restaurantDao.allConsiderSearchGetTotalCount(searchCondition);
            searchCondition.setTotal_count(total_count);
            searchCondition.setPage_size(searchCondition.getPage_size());
            searchCondition.setCurr_page(searchCondition.getCurr_page());
            SearchCondition.updateCondition(searchCondition);
        }

        List<SimpleRestaurant> simpleRestaurantList = new ArrayList<>();

        for (RestaurantDto dto : restaurantDtoList) {
            SimpleRestaurant simpleRestaurant = SimpleRestaurant.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .category(CategoryChanger.numberIntoCategory(dto.getFk_category()))
                    .address(dto.getC_address() +" "+ dto.getD_address())
                    .number(dto.getNumber())
                    .image_url(getImgUrl(dto))
                    .menu_name_list(getMenu_name_list(dto))
                    .reservation(dto.getReservation())
                    .loc_x(dto.getLoc_x())
                    .loc_y(dto.getLoc_y())
                    .total_score_count(dto.getTotal_score_count())
                    .total_review_count(dto.getTotal_review_count())
                    .today_business_state(getNowOpen(restaurantDao.getBusinessHours(dto.getId())))
                    .recentSimpleReview(getReviewDto(dto))
                    .memo(dto.getMemo())
                    .searchCondition(searchCondition)
                    .build();
            simpleRestaurantList.add(simpleRestaurant);
        }
        return simpleRestaurantList;
    }

    @Override
    public int saveFavoriteRestaurant(FavoriteDto favoriteDto) {
        return restaurantDao.saveFavoriteRestaurant(favoriteDto);
    }

    @Override
    public int deleteFavoriteRestaurant(FavoriteDto favoriteDto) {
        return restaurantDao.deleteFavoriteRestaurant(favoriteDto);
    }
}
