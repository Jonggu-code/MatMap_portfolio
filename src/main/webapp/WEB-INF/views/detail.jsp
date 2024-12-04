<%--
  Created by IntelliJ IDEA.
  User: joohunkang
  Date: 2024. 12. 2.
  Time: 오후 3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.matjongchan.app.domain.entity.ReviewDto" %>
<%@ page import="com.matjongchan.app.domain.entity.ReviewMenuDto" %>
<%@ page import="com.matjongchan.app.service.ReviewMenuService" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>맛맵 - 음식점 세부정보</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common.css' /> ">
    <link rel="stylesheet" href="<c:url value='/resources/css/detail.css' /> ">

    <!-- 컬러 차트
        메인 컬러 : #ff9625
        헤더 호버 컬러 : #d97f1f
        서브 컬러 : #e5b684
    -->

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
                <a class="site_logo" href="./index.html">MatMap</a>
            </div>
            <div class="search_box" id="search">
                <form id="search_keyword" class="KeywordSearch" action="./index.html">
                    <fieldset class="fld_inside">
                        <legend class="screen_out">검색어 입력폼</legend>
                        <input type="text" class="search_input" id="search.keyword.query" name="search" autocomplete="off" placeholder="무엇을 먹어야 잘 먹었다고 소문날까?" maxlength="100">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M20.87,20.17l-5.59-5.59C16.35,13.35,17,11.75,17,10c0-3.87-3.13-7-7-7s-7,3.13-7,7s3.13,7,7,7c1.75,0,3.35-0.65,4.58-1.71 l5.59,5.59L20.87,20.17z M10,16c-3.31,0-6-2.69-6-6s2.69-6,6-6s6,2.69,6,6S13.31,16,10,16z"></path></g></svg>
                    </fieldset>
                </form>
            </div>


            <!-- 게스트 아이콘 뜨는 박스 -->
            <div class="guest_box">
                <a href="./board.html">게시판</a>
                <a href="./rank_page_score.html">월간 맛집</a>
                <div class="guest_menu" tabindex="-1">
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M21,6H3V5h18V6z M21,11H3v1h18V11z M21,17H3v1h18V17z"></path></g></svg>
                    <div class="guest_icon"></div>
                    <div class="guest_menu_box"></div>
                </div>
            </div>
        </div>
    </header>


    <!-- 메인화면 -->
    <main class="container">
        <div class="banner_main">

            <!-- 메인 배너 -->
            <div class="swiper banner_swiper0 contents_area">
                <div class="swiper-button-prev0 banner_btn_L"><p>&lt</p></div>
                <div class="swiper-button-next0 banner_btn_R"><p>&gt</p></div>
                <div class="main_banner swiper-wrapper">
                    <div class="banner_img swiper-slide count_0"></div>
                    <div class="banner_img swiper-slide count_1"></div>
                    <div class="banner_img swiper-slide count_2"></div>
                    <div class="banner_img swiper-slide count_3"></div>
                    <div class="banner_img swiper-slide count_4"></div>
                    <div class="banner_img swiper-slide count_5"></div>
                    <div class="banner_img swiper-slide count_6"></div>
                    <div class="banner_img swiper-slide count_7"></div>
                    <div class="banner_img swiper-slide count_8"></div>
                    <div class="banner_img swiper-slide count_9"></div>
                    <div class="banner_img swiper-slide count_10"></div>
                    <div class="banner_img swiper-slide count_11"></div>
                </div>
            </div>
        </div>

        <!-- 메인 콘테이너 박스 -->
        <div class="container_main contents_area">
            <div class="container_box" id="main_box">

                <!-- 별점 관련 박스 -->
                <div class="review_box">
                    <!-- 별점 스코어 박스 -->
                    <div class="star_box">
                        <span id="review_star" class="empty_star">
                            <p class="fill_star"></p>
                        </span>
                        <span id="review_score">4.8</span>
                    </div>

                    <!-- 별점 스코어 밑 후기 갯수 관련 -->
                    <div id="review_leng">후기 ${reviewCount}개</div>
                </div>

                <!-- 식당 정보 관련 박스 -->
                <div id="rest_title_box">
                    <p id="rest_title">종찬식당</p>
                    <p id="rest_cate">한식</p>
                    <div class="rest_save">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet">
                            <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"></path>
                        </svg>
                    </div>
                </div>
                <div id="rest_info_box">
                    <p id="rest_d_addr"><i class="rest_addr_icon"></i>서울시 강남구 역삼동 테헤란로</p>
                    <p id="rest_p_num"><i class="rest_num_icon"></i>0507-1316-0924</p>
                    <p id="rest_info"><i class="rest_info_icon"></i>예약가능, 주차가능, 배달가능, 포장불가</p>
                </div>
                <p id="rest_intro">최고, 최상의 맛과 경험을 선사하는 퓨전 한식 전문점 종찬 식당입니다.</p>
            </div>


            <!-- 영업 시간 박스 : 타이틀 -->
            <div class="container_box" id="sub_box">
                <div id="rest_time_box">
                    <p id="time_title">영업시간</p>
                    <div class="time_box">
                        <p class="time_icon"></p>
                        <p id="open_time">영업중</p>
                        <p id="break_time">브레이크 타임</p>
                        <p id="close_time">영업종료</p>
                    </div>
                </div>

                <!-- 영업 시간 정보 -->
                <div class="day_box">
                    <p>월  11:30 ~ 22:00</p>
                    <p>화  11:30 ~ 22:00</p>
                    <p>수  11:30 ~ 22:00</p>
                    <p>목  11:30 ~ 22:00</p>
                    <p>금  11:30 ~ 22:00</p>
                    <p>토  11:30 ~ 18:00</p>
                    <p>일  11:30 ~ 18:00</p>
                    <p>브레이크 타임  14:30 ~ 17:30</p>
                </div>
            </div>

            <div class="container_box" id="menu_container">
                <p class="container_title menu_con_title">종찬식당 메뉴</p>
                <div class="menu_box">
                    <div class="menu_img"></div>
                    <div class="menu_title_box">
                        <p class="menu_title">종찬 국밥</p>
                        <p class="menu_price">27,900 원</p>
                    </div>
                </div>
                <div class="menu_box">
                    <div class="menu_img"></div>
                    <div class="menu_title_box">
                        <p class="menu_title">종찬 국밥</p>
                        <p class="menu_price">27,900 원</p>
                    </div>
                </div>
                <div class="menu_box">
                    <div class="menu_img"></div>
                    <div class="menu_title_box">
                        <p class="menu_title">종찬 국밥</p>
                        <p class="menu_price">27,900 원</p>
                    </div>
                </div>
                <div class="menu_box">
                    <div class="menu_img"></div>
                    <div class="menu_title_box">
                        <p class="menu_title">종찬 국밥</p>
                        <p class="menu_price">27,900 원</p>
                    </div>
                </div>
                <div class="menu_box">
                    <div class="menu_img"></div>
                    <div class="menu_title_box">
                        <p class="menu_title">종찬 국밥</p>
                        <p class="menu_price">27,900 원</p>
                    </div>
                </div>
            </div>


            <!-- 식당 사진 박스 (후기에 올린 이미지들) -->
            <div class="container_box" id="img_container">
                <h1 class="container_title">종찬식당 이미지 89건</h1>

                <!-- 식당 이미지 박스 (제이쿼리 append 넣을 div) -->
                <div class="img_con_box"></div>
                <div class="img_con_more_btn">
                    <p>사진 더 불러오기</p>
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
                </div>
            </div>

            <!-- 현재 식당 태그 관련 추천 맛집 -->
            <div class="container_box" id="reco_container">
                <div class="container_title">
                    <p>한식</p>
                    <p>추천 맛집</p>
                </div>
                <div class="reco_box reco_box1">
                    <div class="reco_title_box">
                        <div class="reco_title">준성식 한식</div>
                        <div class="reco_score">
                            <span id="reco_star" class="reco_empty_star">
                                <p class="reco_fill_star"></p>
                            </span>
                            <span id="reco_score">4.9</span>
                            <span>(93)</span>
                        </div>
                    </div>
                    <div class="reco_addr">경기도 고양시 일산구 유령동</div>
                    <div class="reco_img_box">
                        <div class="reco_img reco_img1"></div>
                        <div class="reco_img reco_img2"></div>
                    </div>
                </div>
                <div class="reco_box reco_box1">
                    <div class="reco_title_box">
                        <div class="reco_title">준성식 한식</div>
                        <div class="reco_score">
                            <span id="reco_star" class="reco_empty_star">
                                <p class="reco_fill_star"></p>
                            </span>
                            <span id="reco_score">4.9</span>
                            <span>(93)</span>
                        </div>
                    </div>
                    <div class="reco_addr">경기도 고양시 일산구 유령동</div>
                    <div class="reco_img_box">
                        <div class="reco_img reco_img1"></div>
                        <div class="reco_img reco_img2"></div>
                    </div>
                </div>
                <div class="reco_box reco_box1">
                    <div class="reco_title_box">
                        <div class="reco_title">준성식 한식</div>
                        <div class="reco_score">
                            <span id="reco_star" class="reco_empty_star">
                                <p class="reco_fill_star"></p>
                            </span>
                            <span id="reco_score">4.9</span>
                            <span>(93)</span>
                        </div>
                    </div>
                    <div class="reco_addr">경기도 고양시 일산구 유령동</div>
                    <div class="reco_img_box">
                        <div class="reco_img reco_img1"></div>
                        <div class="reco_img reco_img2"></div>
                    </div>
                </div>
                <div class="move_top">
                    <p>TOP</p>
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
                </div>
            </div>



            <!-- 식당 후기 박스  -->
            <div class="container_box" id="review_container">
                <div class="rev_con_title">
                    <h1 class="container_title">종찬식당 방문자 평가 ${reviewCount}건</h1>
                    <div href="./review_pdivge_1.html" class="rev_create_btn">후기를 작성해서 맛맵을 지원해주세요.</div>
                </div>


                <!-- 평점 관련단 -->
                <div class="rev_con_score_box">
                    <div class="rev_con_score">
                        <div class="rev_con_score_title">
                            <p>전체</p>
                            <p>고객응대</p>
                            <p>청결도</p>
                            <p>맛</p>
                        </div>
                        <!-- 평점 관련 ( 별 갯수 ) -->
                        <div class="rev_con_score_star">
                            <div class="rev_con_score_starbox">
                                <span class="rev_review_star" class="rev_empty_star">
                                    <p class="rev_fill_star" id="star_ave"></p>
                                </span>
                                <span class="rev_review_score score_ave">${total_score}</span>
                            </div>
                            <div class="rev_con_score_starbox">
                                <span class="rev_review_star" class="rev_empty_star">
                                    <p class="rev_fill_star" id="star_kind"></p>
                                </span>
                                <span class="rev_review_score score_kind">${kind_score}</span>
                            </div>
                            <div class="rev_con_score_starbox">
                                <span class="rev_review_star" class="rev_empty_star">
                                    <p class="rev_fill_star" id="star_clean"></p>
                                </span>
                                <span class="rev_review_score score_clean">${clean_score}</span>
                            </div>
                            <div class="rev_con_score_starbox">
                                <span class="rev_review_star" class="rev_empty_star">
                                    <p class="rev_fill_star" id="star_taste"></p>
                                </span>
                                <span class="rev_review_score score_taste">${taste_score}</span>
                            </div>
                        </div>
                    </div>
                    <!-- 평점 관련 ( 점수 관련 ) -->
                    <div class="rev_con_score_list">
                        <div class="rcs_list_title">
                            <p>매우만족 (20)</p>
                            <p>만족 (1)</p>
                            <p>보통 (1)</p>
                            <p>불만 (1)</p>
                            <p>매우불만 (0)</p>
                        </div>
                        <div class="rcs_list_score">
                            <div class="empty_score">
                                <div class="fill_score" id="score_biggood"></div>
                            </div>
                            <div class="empty_score">
                                <div class="fill_score" id="score_good"></div>
                            </div>
                            <div class="empty_score">
                                <div class="fill_score" id="score_common"></div>
                            </div>
                            <div class="empty_score">
                                <div class="fill_score" id="score_bad"></div>
                            </div>
                            <div class="empty_score">
                                <div class="fill_score" id="score_bigbad"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 유저가 작성한 후기 박스 -->
                <div class="rev_con_main">

                    <c:forEach var="review" items="${reviews}">
                        <div class="rev_con_main_box">
                            <!-- 리뷰 작성 날짜 -->
                            <div class="rc_create_at">${review.create_at}</div>
                            <!-- 유저 정보 -->
                            <div class="rc_user_box">
                                <div class="rc_user_icon"></div>
                                <div class="rc_user_info">
                                    <div id="user_name">${review.reviewer}</div>
                                    <div id="user_info">
                                        <p>평균 별점</p>
                                        <p>3.21</p>
                                        <p>후기</p>
                                        <p>-3000</p>
                                    </div>
                                </div>
                            </div>
                            <!-- 유저가 준 평점 -->
                            <div class="rc_user_score_box">
                                <div class="con1">
                                    <p>전체</p>
                                    <p>${review.total_score}</p>
                                </div>
                                <div class="con1">
                                    <p>고객응대</p>
                                    <p>${review.kind_score}</p>
                                </div>
                                <div class="con1">
                                    <p>청결도</p>
                                    <p>${review.clean_score}</p>
                                </div>
                                <div class="con1">
                                    <p>맛</p>
                                    <p>${review.taste_score}</p>
                                </div>
                            </div>
                            <!-- 리뷰 텍스트 -->
                            <div class="rc_user_txt_box">${review.content}</div>
                            <!-- 유저가 먹은 메뉴 -->
                            <div class="re_menu_box">
                                <c:forEach var="menuName" items="${review.menuNames}">
                                    <div class="review_menu">${menuName}</div>
                                </c:forEach>
                            </div>

                            <!-- 유저가 올린 사진 -->
<%--                            <div class="rc_img_box">--%>
<%--                                <c:forEach var="img" items="${review.images}">--%>
<%--                                    <div class="review_img">--%>
<%--                                        <img src="${img}" alt="리뷰 이미지">--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
                        </div>
                    </c:forEach>


                    <div class="rev_con_main_box">
                        <div class="rc_create_at">11월 13일</div>
                        <div class="rc_user_box">
                            <div class="rc_user_icon"></div>
                            <div class="rc_user_info">
                                <div id="user_name">무먹잘소</div>
                                <div id="user_info">
                                    <p>평균 별점</p>
                                    <p>4.1</p>
                                    <p>후기</p>
                                    <p>132</p>
                                </div>
                            </div>
                        </div>
                        <!-- 유저가 준 평점 -->
                        <div class="rc_user_score_box">
                            <div class="con1">
                                <p>전체</p>
                                <p>4.8</p>
                            </div>
                            <div class="con1">
                                <p>고객응대</p>
                                <p>4.8</p>
                            </div>
                            <div class="con1">
                                <p>청결도</p>
                                <p>4.8</p>
                            </div>
                            <div class="con1">
                                <p>맛</p>
                                <p>4.8</p>
                            </div>
                        </div>
                        <!-- 유저가 적은 텍스트 -->
                        <div class="rc_user_txt_box">여긴 진짜… 시청역 직장인들의 숨은 맛집, 나만 알고싶은 맛집이 아닐까 생각한다.
                            진한 국물에 우동사리같은 면, 밥이 말아져서 나온다. 파도 듬뿍 올려져있다.

                            애성회관 한우곰탕의 킥은 ‘후추’다.
                            간혹 후추를 뿌리면 본연의 국물 맛이 가려지는 경우가 있는데. 애성회관 곰탕은 배가 된다.

                            24녘 7월 방문 기준
                            일반 곰탕은 고기가 3덩이, 특곰탕은 고기 8덩이 정도가 나온다. 가격은 2000원 차이난다.
                            밥이나 사리면의 양은 차이가 없다고 느껴진다.</div>
                        <!-- 유저가 먹은 메뉴 (후기 작성 페이지에서 선택) -->
                        <div class="re_menu_box">
                            <div class="review_menu">특곰탕</div>
                            <div class="review_menu">한우곰탕</div>
                        </div>
                        <!-- 유저가 올린 사진 (후기 작성 페이지에서 선택) -->
                        <div class="rc_img_box">
                            <div class="review_img"></div>
                            <div class="review_img"></div>
                            <div class="review_img"></div>
                            <div class="review_img">n개 더보기</div>
                        </div>
                    </div>
                </div>
                <div class="rev_con_more_btn">
                    <p>리뷰 더 불러오기</p>
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
                </div>
            </div>
        </div>
    </main>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="<c:url value='/resources/js/common.js' /> "></script>
<script src="<c:url value='/resources/js/detail.js' /> "></script>
<script src="./js/swiper.js"></script>

<script>
    $(document).ready(function (){
    document.querySelector('.rev_con_more_btn').addEventListener('click', function() {
    offset += limit;
    fetch(`/detail/reviews/${restaurantId}`)
    .then(response => response.json())
    .then(data => {
    data.forEach(review => {
    const reviewHtml = `

    <c:forEach var="review" items="${reviews}">
        <div class="rev_con_main_box">
        <!-- 리뷰 작성 날짜 -->
        <div class="rc_create_at">${review.create_at}</div>
        <!-- 유저 정보 -->
        <div class="rc_user_box">
        <div class="rc_user_icon"></div>
        <div class="rc_user_info">
        <div id="user_name">${review.reviewer}</div>
                                    <div id="user_info">
                                        <p>평균 별점</p>
                                        <p>3.21</p>
                                        <p>후기</p>
                                        <p>-3000</p>
                                    </div>
                                </div>
                            </div>
                            <!-- 유저가 준 평점 -->
                            <div class="rc_user_score_box">
                                <div class="con1">
                                    <p>전체</p>
                                    <p>${review.total_score}</p>
                                </div>
                                <div class="con1">
                                    <p>고객응대</p>
                                    <p>${review.kind_score}</p>
                                </div>
                                <div class="con1">
                                    <p>청결도</p>
                                    <p>${review.clean_score}</p>
                                </div>
                                <div class="con1">
                                    <p>맛</p>
                                    <p>${review.taste_score}</p>
                                </div>
                            </div>
                            <!-- 리뷰 텍스트 -->
                            <div class="rc_user_txt_box">${review.content}</div>
                            <!-- 유저가 먹은 메뉴 -->
                            <div class="re_menu_box">
                                <c:forEach var="menuName" items="${review.menuNames}">
                                    <div class="review_menu">${menuName}</div>
                                </c:forEach>
                            </div>

                            <!-- 유저가 올린 사진 -->
<%--                            <div class="rc_img_box">--%>
<%--                                <c:forEach var="img" items="${review.images}">--%>
<%--                                    <div class="review_img">--%>
<%--                                        <img src="${img}" alt="리뷰 이미지">--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
                        </div>
                    </c:forEach>
                        `;
                        document.querySelector('.rev_con_main').insertAdjacentHTML('beforeend', reviewHtml);
                    });

                    // 모든 리뷰를 다 불러왔다면 버튼 숨기기
                    if (data.length < limit) {
                        document.querySelector('.rev_con_more_btn').style.display = 'none';
                    }
                })
                .catch(error => console.error('Error fetching reviews:', error));
        });

    })
</script>

    </body>
</html>
