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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css?after"/>

    <!-- 컬러 차트
        메인 컬러 : #ff9625 
        헤더 호버 컬러 : #d97f1f
        서브 컬러 : #e5b684
    -->

</head>
<%--<script>--%>
<%--    console.log("${sr[0].id}")--%>
<%--    console.log("${sr[0].name}")--%>
<%--    console.log("${sr[0].image_url}")--%>
<%--    console.log("${sr[0].address}")--%>
<%--    console.log("${sr[0].total_score_count}")--%>
<%--    console.log("${sr[0].total_review_count}")--%>
<%--    console.log("${sr[0].today_business_state}")--%>
<%--    console.log("${sr[0].reservation}")--%>
<%--    console.log("${sr[0].number}")--%>
<%--    console.log("${sr[0].loc_x}")--%>
<%--    console.log("${sr[0].loc_y}")--%>
<%--    console.log("${sr[0].menu_name_list}")--%>
<%--    console.log("${sr[0].recentSimpleReview}")--%>
<%--</script>--%>
<body>
<div id="wrap">
    <aside class="sidebar">
        <header class="header">
            <div class="header_top">
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
            <ul class="header_menu">
                <a class="header_menu_item" href="./board.html"><p>게시판</p></a>
                <a class="header_menu_item" href="./rank_page_score.html"><p>월간 맛집</p></a>
            </ul>
        </header>
        <main class="main">
            <c:forEach var="tmp" items="${sr}">
            <!-- 식당들은 main_item 클래스를 가진상태로 나열됨  -->
            <div class="main_item">
                <div class="main_item_box">
                    <div class="main_item_Lbox">
                        <a class="img" href="./detail.html">1</a>
                    </div>
                    <div class="main_item_Rbox">
                        <div class="item_title">

                            <!-- 식당 이름 -->
                            <a class="item_name" href="./detail.html">${tmp.name}</a>
                            <div class="item_star">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2Z"></path></svg>

                                <!-- 식당 전체 평점의 평균 -->
                                <p>${tmp.total_score_count}</p>

                                <!-- 식당에 관련된 후기 갯수 -->
                                <p>${tmp.total_review_count}</p>
                            </div>
                        </div>

                        <!-- 식당 주소정보 들어오는 단 -->
                        <p class="item_addr">
                            <i class="rest_addr_icon"></i>
                            ${tmp.address}
                        </p>

                        <!-- 식당 영업시간정보 들어오는 단 / 빈칸이면 "영업 정보 없음"-->
                        <p class="item_time">
                            <i class="rest_num_icon"></i>
                            ${tmp.number}
<%--                            화요일 09:00 ~ 22:00--%>
                        </p>

                        <!-- 식당 정보 들어오는 단 / 빈칸이면 "매장 정보 없음"-->
                        <p class="item_info">
                            <i class="rest_info_icon"></i>
                            ${tmp.reservation}
                        </p>
                    </div>
                </div>

                <!-- 식당 메뉴 들어오는 단 -->
                <ul class="main_item_bot">
<%--                    <c:forEach var="tmp" items="${sr}">--%>
                    <c:forEach var="menu" items="${tmp.menu_name_list}">
                        <li>#${menu}</li>
                    </c:forEach>
                </ul>
                <!-- 리뷰 텍스트 들어오는 단 -->
                <div class="review_box">
                    <div class="review_box">
                        <div class="user_title">
                            <div class="user_name">
                                11
                            </div>
                            <div class="create_at">
                                11
                            </div>
                        </div>
                        <div class="review_txt">
                            oo
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
<%--      하나임!!      --%>

        </main>
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
                <div class="guest_menu" tabindex="-1">
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M21,6H3V5h18V6z M21,11H3v1h18V11z M21,17H3v1h18V17z"></path></g></svg>
                    <div class="guest_icon"></div>
                    <div class="guest_menu_box"></div>
                </div>
            </div>
        </div> 
    </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b4e6868c7b5fe35c80d9b43d3190c872"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="<c:url value="resources/js/map.js"/>"></script>
<script src="<c:url value="resources/js/index.js"/>"></script>

</body>
</html>