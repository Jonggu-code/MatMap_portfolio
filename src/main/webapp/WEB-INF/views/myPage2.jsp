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
    <title>마이페이지</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="<c:url value = '/resources/css/common.css'/>">
    <link rel="stylesheet" href="<c:url value = '/resources/css/mypage2.css?3'/>">

</head>
<body>
<div id="wrap">
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
                <form id="search_keyword" class="KeywordSearch" action="./index.html">
                    <fieldset class="fld_inside">
                        <legend class="screen_out">검색어 입력폼</legend>
                        <input type="text" class="search_input" id="search.keyword.query" name="search" autocomplete="off" placeholder="무엇을 먹어야 잘 먹었다고 소문날까?" maxlength="100">
                        <svg class="search_submit" class="search_submit" viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M20.87,20.17l-5.59-5.59C16.35,13.35,17,11.75,17,10c0-3.87-3.13-7-7-7s-7,3.13-7,7s3.13,7,7,7c1.75,0,3.35-0.65,4.58-1.71 l5.59,5.59L20.87,20.17z M10,16c-3.31,0-6-2.69-6-6s2.69-6,6-6s6,2.69,6,6S13.31,16,10,16z"></path></g></svg>
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
    <div class="container_main contents_area">

        <!-- 좌측 메뉴 -->
        <div class="mypage_box_L">

            <!-- 좌측 프로필 박스-->
            <div class="mp_user_box radius_box shadow_box">
                <div class="mp_user_box_L">
                    <!-- 유저의 이미지 background로 삽입 -->
<%--                    <div class="user_img">--%>
<%--                        <img src="<c:url value='${memberImage.img_url}'/>" class="profile_img">--%>
<%--                    </div>--%>
                    <div class="user_img" style="background: url(${memberImage.img_url}) no-repeat center / cover;"></div>
                    <!-- 유저 네임 -->
                    <p class="user_name">${member.name}</p>
                </div>

                <div class="mp_user_box_R">

                    <!-- 사이트 회원가입 일 -->
                    <div class="title_box" id="user_create_at">
                        <p class="mini_title">맛맵 가입일</p>
                        <p class="big_title">
                            ${fn:split(member.create_at, " ")[0]}
<%--                            ${member.create_at}--%>
                        </p>

                    </div>

                    <!-- 유저가 찜한 맛집 개수 (시간 별로 안들면 기능 추가..)-->
                    <div class="title_box" id="user_favo_rest">
                        <p class="mini_title">찜한 맛집</p>
                        <p class="big_title">${favorites.size()}</p>
                    </div>

                    <!-- 유저가 작성한 후기 개수 -->
                    <div class="title_box" id="user_create_review">
                        <p class="mini_title">작성한 후기</p>
                        <p class="big_title">${reviewCount}</p>
                    </div>

                </div>
            </div>
            <!-- 좌측 인증 정보 -->
            <div class="mp_info_box radius_box">
                <div class="info_box_title">
                    <p>인증 정보</p>
                </div>
                <div class="info_txt_box">
                    <ul class="txt_box_L">
                        <li class="check_menu info_number">번호:</li>
                        <li class="check_menu info_email">이메일:</li>
                        <li class="check_menu info_gender">성별:</li>
                    </ul>
                    <ul class="txt_box_R">
                        <li class="check_list user_number">${member.phone_number}</li>
                        <li class="check_list user_email">${member.email}</li>
                        <li class="check_list user_gender">${member.gender == 'F' ? '여' : '남'}</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- 메인 메뉴 -->
        <main class="mypage_box_R">

            <!-- 1번 프로필 영역 -->
            <div class="profile_box basic_border radius_box shadow_box">

                <!-- 박스 타이틀 공통 박스 -->
                <div class="profile_title_box">
                    <p class="profile_title">자기소개</p>
                    <a href="<c:url value='/changeMemInfo'/>">
                        <input type="button" class="profile_fix_btn mypage_btn" value="프로필 수정하기">
                    </a>

                </div>



                <c:choose>
                    <c:when test = "${not empty member.introduce}">
                        <div class="profile_intro_box">
                                ${member.introduce}
                        </div>
                    </c:when>
                    <c:otherwise>
                        <!-- 빈 박스 (전체 박스 공통) -->
                        <div class="empty_box intro_empty">
                            <p>소개글이 없습니다.</p>
                        </div>
                    </c:otherwise>
                </c:choose>




            </div>

            <!-- 2번 음식점 영역 -->
            <div class="my_rest_box basic_border radius_box shadow_box">

                <!-- 박스 타이틀 공통 박스 -->
                <div class="profile_title_box">
                    <div class="profile_title">내가 찜한 음식점</div>
                    <a href="<c:url value='/myPageRestaurant'/>">
                        <input type="button" value="목록 더보기" class="mypage_btn">
                    </a>
                </div>

                <div class="swiper mypage_swiper0 rest_box_container">
                    <c:choose>
                        <c:when test = "${not empty favorites}">
                        <div class="swiper-wrapper">
                            <c:forEach var="favorite" items="${favorites}" varStatus="status" begin="0" end="4">

                                <div class="rest_box_main swiper-slide">
                                    <div class="rest_box_L">
<%--                                        <div class="menu_img"></div>--%>
                                            <!-- 식당 이미지 출력 -->
                                            <div class="menu_img">
                                                <img src="${restaurantImageUrls[status.index]}" alt="${favorite.restaurant_name}" />
                                            </div>
                                    </div>
                                    <div class="rest_box_R">
                                        <div class="rest_title_box">
                                            <a class="item_name" href="./detail.html">${favorite.restaurant_name}</a>
                                            <p class="rest_tag">
<%--                                                    한식--%>
                                            </p>
                                        </div>
                                        <div class="item_star">
                                            <!-- 식당에 관련된 후기 갯수 -->
                                            <div class="item_score">
                                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2Z"></path></svg>
                                                <!-- 식당 전체 평점의 평균 -->
                                                <p class="rest_ave_score">${empty favorite.total_score_count ? '없음' : favorite.total_score_count}</p>
                                            </div>
                                            <p class="rest_review_leng">(${favorite.total_review_count})</p>
                                        </div>
                                        <p class="rest_addr">${favorite.c_address} ${favorite.d_address}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="swiper-pagination"></div>
                        </c:when>
                        <c:otherwise>
                            <!-- 빈 박스 (전체 박스 공통) -->
                            <div class="empty_box rest_empty">
                                <p>아직 찜한 식당이 없습니다.</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <!-- 3번 후기 영역 -->
            <div class="my_review_box basic_border radius_box shadow_box">

                <div class="profile_title_box">
                    <div class="profile_title">내가 작성한 리뷰</div>
                    <a href="<c:url value='/myPageReview'/>">
                        <input type="button" value="리뷰 더보기" class="mypage_btn">
                    </a>
                </div>

                <div class="swiper mypage_swiper1 review_box_container">

                    <c:choose>
                        <c:when test = "${not empty reviews}">
                            <div class="swiper-wrapper">
                                <c:forEach var = "review" items = "${reviews}" varStatus="status" begin="0" end="4">
                                    <div class="review_box swiper-slide">
                                        <div class="review_box_title">


                                            <c:set var = "restaurantDetail" value="${restaurantMap[review.fk_restaurant_id]}" />

                                            <div class="review_rest_name">${restaurantDetail.restaurant_name}</div>
                                            <div class="review_create_at">
<%--                                                    ${review.create_at}--%>
                                                            ${fn:split(review.create_at, " ")[0]}
                                            </div>
                                        </div>

                                        <div class="review_txt">
                                           ${review.content}
                                        </div>
                                    </div>

                                </c:forEach>
                            </div>
                            <div class="swiper-pagination"></div>
                        </c:when>
                        <c:otherwise>
                            <!-- 작성한 리뷰가 없을시 -->
                            <div class="empty_box review_empty">
                                <p>아직 작성한 후기가 없습니다.</p>
                            </div>
                        </c:otherwise>

                    </c:choose>


                </div>
            </div>
        </main>
    </div>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="<c:url value='/resources/js/mypage2.js'/>"></script>
<script src="<c:url value='/resources/js/swiper.js'/>"></script>

</body>
</html>
