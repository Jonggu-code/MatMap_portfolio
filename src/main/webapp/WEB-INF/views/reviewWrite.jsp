<%--
  Created by IntelliJ IDEA.
  User: joohunkang
  Date: 2024. 11. 25.
  Time: 오후 3:15
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 작성 페이지-1</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css'/>" />

    <link rel="stylesheet" href="<c:url value='/resource/css/review_page_1.css' /> ">
    <link rel="stylesheet" href="<c:url value='/resource/css/review_common.css' /> ">

</head>
<body>
<form action="<c:url value='/showReviewWrite2'/>" method="post" id="wrap">
    <fieldset class="review_main">
        <div class="logo_box">
            <a href="./index.html" class="logo">맛맵 - 메인화면</a>
        </div>
        <div class="container contents_area">
            <!-- 식당 이름 칸 -->
            <div class="header">
                <div class="rest_img_box"></div>
                <div class="rest_info_box">
                    <p class="rest_title">종찬식당</p>
                    <span>한식</span>
                    <span>/</span>
                    <span>2024년 11월 25일</span>
                </div>
            </div>

            <!-- 별점 넣는 칸 -->
            <!-- 별 클릭하면 각 점수 설정 가능. -->
            <div class="common_box main_score_box">
                <p class="ms_title">식당에 대한 전체적인 평가를 해주세요.</p>
                <div class="ms_star_box">
                    <div class="review_star empty_star_main">
                        <p class="fill_star star_main"></p>
                    </div>
                    <input type="text" value="0.0" class="score_user_main" readonly name="total_score">
                    <span>/</span>
                    <span class="score_base">5.0</span>
                </div>
            </div>

            <!-- 디테일 별점 -->
            <div class="common_box detail_box">

                <!-- 고객 응대 별점 -->
                <div class="detail_score_box">
                    <p class="ms_title">식당의 고객응대 점수를 평가해주세요.</p>
                    <div class="ms_star_box">
                        <div class="review_star empty_star_kind">
                            <p class="fill_star star_kind"></p>
                        </div>
                        <input type="text" value="0.0" class="score_user_kind" readonly name="kind_score">
                        <span>/</span>
                        <span class="score_base">5.0</span>
                    </div>
                </div>

                <!-- 청결상태 별점 -->
                <div class="detail_score_box">
                    <p class="ms_title">식당의 청결상태를 평가해주세요.</p>
                    <div class="ms_star_box">
                        <div class="review_star empty_star_clean">
                            <p class="fill_star star_clean"></p>
                        </div>
                        <input type="text" value="0.0" class="score_user_clean" readonly name="clean_score">
                        <span>/</span>
                        <span class="score_base">5.0</span>
                    </div>
                </div>

                <!-- 음식 맛 별점 -->
                <div class="detail_score_box">
                    <p class="ms_title">음식의 맛에 대해 평가해주세요.</p>
                    <div class="ms_star_box">
                        <div class="review_star empty_star_taste">
                            <p class="fill_star star_taste"></p>
                        </div>
                        <input type="text" value="0.0" class="score_user_taste" readonly name="taste_score">
                        <span>/</span>
                        <span class="score_base">5.0</span>
                    </div>
                </div>
            </div>
            <div class="common_box menu_box">
                <p>식당에서 드신 메뉴를 선택해주세요.</p>

                <!-- 메뉴 클릭하면 menu_active 클래스 추가되면서 background 색 진해짐. -->
                <ul class="menu_list">
                    <li>닭곰탕</li>
                    <li>곰탕</li>
                    <li>특곰탕</li>
                    <li>수육고기</li>
                    <li>김치전</li>
                    <li>파전</li>
                    <li>사골국밥</li>
                    <li>수육모듬</li>
                    <li>닭개장</li>
                    <li>육개장</li>
                </ul>
            </div>
            <div class="btn_box">
                <input type="submit" value="다음화면">
            </div>
    </fieldset>
</form>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="<c:url value='/resource/js/review_common.js' /> "></script>
<script src="./js/swiper.js"></script>

</body>
</html>