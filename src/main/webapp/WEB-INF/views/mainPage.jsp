<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>맛맵 - 메인화면</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="./css/index.css">

    <!-- 컬러 차트
        메인 컬러 : #ff9625
        헤더 호버 컬러 : #d97f1f
        서브 컬러 : #e5b684
    -->

</head>
<body>
<div id="wrap">
    <aside class="sidebar">
        <header class="header">
            <div class="header_top">
                <a class="site_logo" href="index.jsp">MatMap</a>
            </div>
            <div class="search_box" id="search">
                <form id="search_keyword" class="KeywordSearch" action="#">
                    <fieldset class="fld_inside">
                        <legend class="screen_out">검색어 입력폼</legend>
                        <input type="text" class="search_input" id="search.keyword.query" name="search" autocomplete="off" placeholder="무엇을 먹어야 잘 먹었다고 소문날까?" maxlength="100">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M20.87,20.17l-5.59-5.59C16.35,13.35,17,11.75,17,10c0-3.87-3.13-7-7-7s-7,3.13-7,7s3.13,7,7,7c1.75,0,3.35-0.65,4.58-1.71 l5.59,5.59L20.87,20.17z M10,16c-3.31,0-6-2.69-6-6s2.69-6,6-6s6,2.69,6,6S13.31,16,10,16z"></path></g></svg>
                    </fieldset>
                </form>
            </div>
            <ul class="header_menu">
                <li class="header_menu_item"><p># 메인화면</p></li>
                <li class="header_menu_item"><p># 이구역 랭킹</p></li>
                <li class="header_menu_item"><p># 나만의 랭킹</p></li>
            </ul>
        </header>
        <main class="main">
            <div class="main_item">
                <div class="main_item_box">
                    <div class="main_item_Lbox">
                        <a class="img" href="./detail.html"> 1</a>
                    </div>
                    <div class="main_item_Rbox">
                        <div class="item_title">
                            <a class="item_name" href="./detail.html">종찬식당</a>
                            <div class="item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2Z"></path></svg>
                                <p>4.9</p>
                                <p>(21)</p>
                            </div>
                        </div>
                        <p class="item_addr">서울시 강남구 역삼동 테헤란로</p>
                        <p class="item_info">영업중 / 휴무일 월,수,금</p>
                        <p class="item_time">평일 09:00 ~ 18:00 / 주말 09:00 ~ 18:00</p>
                    </div>
                </div>
                <ul class="main_item_bot">
                    <li>#한식</li>
                    <li>#김치찌개</li>
                    <li>#돈까스</li>
                    <li>#계란말이</li>
                    <li>#한식</li>
                    <li>#김치찌개</li>
                    <li>#돈까스</li>
                    <li>#계란말이</li>
                </ul>
                <div class="review_box"></div>
            </div>
        </main>
        <footer class="footer"></footer>
    </aside>
    <div id="result"></div>
    <div id="map">
        <div class="map_header">
            <div class="map_box_L">
                <div class="choose_location">
                    <p>지도 표시 지역</p>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path d="m4 6 4 4 4-4"></path></svg>
                </div>
                <div class="choose_align">
                    <p>리뷰 갯수 많은순</p>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path d="m4 6 4 4 4-4"></path></svg>
                </div>
                <div class="choose_tag">
                    <p>전체</p>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path d="m4 6 4 4 4-4"></path></svg>
                </div>
                <div class="random_food"></div>
            </div>
            <div class="map_box_R">
                <div class="focus_map"></div>
                <div class="curr_location"></div>
                <div class="guest_login"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b4e6868c7b5fe35c80d9b43d3190c872"></script>
<script src="./js/map.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="./js/index.js"></script>
</body>
</html>