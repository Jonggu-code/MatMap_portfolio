<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>맛맵 - 맛집랭킹</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="<c:url value = '/resources/css/common.css'/>">
    <link rel="stylesheet" href="<c:url value = '/resources/css/list_page_restaurant.css?4'/>">
</head>
<body>
<div id="wrap">

    <!-- 헤더 -->
    <header class="header">

        <!-- 이벤트 -->
        <div class="event">
            <div class="contents_area event_box">
                <p>무엇을 먹어야 잘 먹었다고 소문이 날까?</p>
                <p>무먹잘소 Portfolio '맛맵' 입니다.</p>
                <p>로그인을 하시려면 화면 오른쪽 상단 햄버버튼을 클릭하고</p>
                <p>로그인을 클릭하시면 로그인이 됩니다.</p>
                <p>로그아웃을 하시려면 로그아웃을 누르세요.</p>
                <p>즐거운 맛집탐방 여행 되세요 ^^</p>
                <svg id="btn_close" viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M12.7,12l6.6,6.6l-0.7,0.7L12,12.7l-6.6,6.6l-0.7-0.7l6.6-6.6L4.6,5.4l0.7-0.7l6.6,6.6l6.6-6.6l0.7,0.7L12.7,12z"></path></g></svg>
            </div>
        </div>

        <!-- 헤더 메인 -->
        <div class="header_main contents_area">
            <!-- 로고 -->
            <div class="logo_box">
                <a class="site_logo" href="<c:url value="/"/>">MatMap</a>
            </div>
            <div class="search_box" id="search">
                <form id="search_keyword" class="KeywordSearch" action="<c:url value='/'/>" method="post">
                    <fieldset class="fld_inside">
                        <legend class="screen_out">검색어 입력폼</legend>
                        <input type="text" class="search_input" id="search_keyword_query" name="keyword" autocomplete="off" placeholder="무엇을 먹어야 잘 먹었다고 소문날까?" maxlength="100">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M20.87,20.17l-5.59-5.59C16.35,13.35,17,11.75,17,10c0-3.87-3.13-7-7-7s-7,3.13-7,7s3.13,7,7,7c1.75,0,3.35-0.65,4.58-1.71 l5.59,5.59L20.87,20.17z M10,16c-3.31,0-6-2.69-6-6s2.69-6,6-6s6,2.69,6,6S13.31,16,10,16z"></path></g></svg>
                    </fieldset>
                </form>
            </div>

            <!-- 게스트 아이콘 뜨는 박스 -->
            <div class="guest_box">
                <a href="./board.html">게시판</a>
                <a href="<c:url value="/rank"/>">월간 맛집</a>
                <div class="guest_menu" tabindex="-1">
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M21,6H3V5h18V6z M21,11H3v1h18V11z M21,17H3v1h18V17z"></path></g></svg>
                    <c:if test='${!empty sessionScope.img}'>
                        <div class="guest_icon" style='background: url("<spring:url value='${sessionScope.img}'/>") no-repeat center / cover'></div>
                    </c:if>
                    <c:if test='${empty sessionScope.img}'>
                        <div class="guest_icon" style='background: url("<c:url value='/resources/img/other_img/1.png'/>") no-repeat center / cover'></div>
                    </c:if>
                    <div class="guest_menu_box">
                        <c:choose>
                            <c:when test="${sessionScope.id == null}">
                                <p class="login_com">로그인</p>
                                <p class="register_com">회원가입</p>
                            </c:when>
                            <c:when test="${sessionScope.id != null}">
                                <p class="myPage_com">마이페이지</p>
                                <p class="myFavorite_com">마이 맛집 </p>
                                <p class="myReview_com">마이 후기</p>

                                <p class="logout_com">로그아웃</p>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- 배너단 -->
    <div class="container">
        <!-- 상단 이동 버튼 -->
        <div class="move_top">
            <p>TOP</p>
            <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
        </div>

        <div class="banner">
            <p>내가 찜한 식당</p>
        </div>

        <c:choose>
            <c:when test="${not empty restaurantDetails}">
                <c:forEach var="restaurant" items="${restaurantDetails}" varStatus="status">
                    <div class="container_main contents_area">
                        <div class="container_box main_box">

                            <!-- 찜한 식당 리스트 출력 -->
                            <div class="rank_box">
                                <!-- 순차적으로 식당 번호를 매기기 위해 status.index 사용 -->
                                <p>${status.index + 1}.</p>
<%--                                <div class="time_box">--%>
<%--                                    <p class="time_icon"></p>--%>
<%--                                    <p class="open_time">영업중</p>--%>
<%--                                    <p class="break_time">브레이크 타임</p>--%>
<%--                                    <p class="close_time">영업종료</p>--%>
<%--                                </div>--%>
                            </div>

                            <div class="container_banner swiper rank_swiper0">
                                <div class="swiper-button-prev0 banner_btn_L"><p>&lt</p></div>
                                <div class="swiper-button-next0 banner_btn_R"><p>&gt</p></div>
                                <div class="main_banner swiper-wrapper">
<%--                                    <div class="banner_img swiper-slide count_0"></div>--%>
<%--                                    <div class="banner_img swiper-slide count_1"></div>--%>
<%--                                    <!-- 배너 이미지들 -->--%>

                                        <!-- banner_img를 restaurantImageUrls와 연결 -->
                                        <c:forEach var="imageUrl" items="${restaurantImageUrls}" varStatus="imageStatus">
                                            <c:if test="${imageStatus.index == status.index}">
                                                <div class="banner_img swiper-slide count_${imageStatus.index}">
                                                    <img src="${imageUrl}" alt="${restaurant.restaurant_name}">
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                </div>
                            </div>

                            <div class="title_box">
                                <div class="review_box">
                                    <div class="star_box">
                                <span class="empty_star review_star">
                                    <p class="fill_star"></p>
                                </span>

                                    <span class="review_score">
                                            ${restaurant.restaurant_total_score_count != null ? restaurant.restaurant_total_score_count : '평점 정보 없음'}
                                    </span>
                                </div>
                                <div class="review_leng">
                                    후기 ${restaurant.restaurant_total_review_count != null ? restaurant.restaurant_total_review_count : '0'}개
                                </div>

                            </div>

                                <div class="rest_title_box">
                                    <a href="./detail.html" class="rest_title">
                                            ${restaurant.restaurant_name != null && !restaurant.restaurant_name.isEmpty() ? restaurant.restaurant_name : '식당명 정보 없음'}
                                    </a>
                                    <p class="rest_cate">
                                            ${restaurant.restaurant_category != null && !restaurant.restaurant_category.isEmpty() ? restaurant.restaurant_category : '카테고리 정보 없음'}
                                    </p>
                                </div>
                                <div class="rest_info_box">
                                    <p class="rest_d_addr"><i class="rest_addr_icon"></i>${restaurant.restaurant_address != null && !restaurant.restaurant_address.isEmpty() ? restaurant.restaurant_address : '주소 정보 없음1'}</p>
                                    <p class="rest_p_num"><i class="rest_num_icon"></i>${restaurant.restaurant_number != null && !restaurant.restaurant_number.isEmpty() ? restaurant.restaurant_number : '전화번호 정보 없음'}</p>
                                    <p class="rest_info"><i class="rest_info_icon"></i>${restaurant.restaurant_category != null && !restaurant.restaurant_category.isEmpty() ? restaurant.restaurant_category : '카테고리 정보 없음'}</p>
                                    <p class="rest_time"><i class="rest_time_icon"></i>${restaurant.restaurant_reservation != null && !restaurant.restaurant_reservation.isEmpty() ? restaurant.restaurant_reservation : '예약 정보 없음'}</p>
                                </div>

                                <c:if test="${not empty restaurant.restaurant_memo}">
                                    <div class="rest_intro">
                                        <p>${restaurant.restaurant_memo}</p>
                                    </div>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
<%--                <p>찜한 식당이 없습니다.</p>--%>
                <div class="empty_box">
                    <img class="empty_icon" src="./img/empty_img.png" alt="">
                    <p class="empty_favo_rest">
                       '내가 찜한 맛집이 없습니다.'
                    </p>
                </div>

            </c:otherwise>
        </c:choose>



    </div>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
<script src="<c:url value='/resources/js/rank_page_score.js'/>"></script>
<script src="<c:url value='/resources/js/swiper.js'/>"></script>

</body>
</html>
