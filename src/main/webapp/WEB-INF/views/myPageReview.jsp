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
    <title>맛맵 - 내가 작성한 후기</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value = '/resources/css/common.css'/>">
    <link rel="stylesheet" href="<c:url value = '/resources/css/list_page_review.css'/>">

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
                <a href="rank_page_score.jsp">월간 맛집</a>
                <div class="guest_menu" tabindex="-1">
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M21,6H3V5h18V6z M21,11H3v1h18V11z M21,17H3v1h18V17z"></path></g></svg>
                    <div class="guest_icon"></div>
                    <div class="guest_menu_box"></div>
                </div>
            </div>
        </div>
    </header>


    <!-- 배너단 -->
    <div class="container">

        <!-- 페이지 상단 이동 버튼 -->
        <div class="move_top">
            <p>TOP</p>
            <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
        </div>

        <div class="banner">
            <!-- <p>맛맵 유저들이 만들어가는 맛맵 랭킹 !</p> -->
            <p>작성한 리뷰</p>
        </div>

        <div class="container_main contents_area">
            <!-- 리뷰가 없는 경우 -->
            <c:if test="${empty reviews}">
                <div class="no_reviews">
                    <p>작성한 리뷰가 없습니다.</p>
                </div>
            </c:if>

            <!-- 리뷰 리스트 출력 -->
            <c:if test="${not empty reviews}">
                <c:forEach var="review" items="${reviews}">
                    <div class="container_box main_box">
                        <div class="rev_con_main">
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
                                            <p>${review.total_score}</p>
                                            <p>후기</p>
<%--                                            <p>${review.reviewCount}</p>--%>
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

                                <!-- 리뷰 내용 -->
                                <div class="rc_user_txt_box">${review.title} <br>${review.content}</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>

    </div>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
</body>
</html>
