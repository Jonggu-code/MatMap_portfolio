
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
    <title>맛맵 - 맛집랭킹</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="<c:url value="/resources/css/common.css?2"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/rank_page_score.css?2"/>">
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
                    <div class="guest_icon"></div>
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
        <div class="banner">
            <p>맛맵 유저들이 만들어가는 맛맵 랭킹 !</p>
            <p>맛맵 <b>월간</b> 맛집랭킹</p>
        </div>

        <!-- 메인화면단 -->
        <div class="container_main contents_area">
        <c:forEach var="tmp" items="${rank_list}">
            <div class="container_box main_box">
                <!-- 맛집 랭킹단 -->
                <div class="rank_box">
                    <p>TOP 1</p>
                    <div class="time_box">
                        <p class="time_icon"></p>
                        <c:choose>
                            <c:when test="${tmp.today_business_state == '영업중'}">
                                <p class="open_time">영업중</p>
                            </c:when>
                            <c:when test="${tmp.today_business_state == '영업종료'}">
                                <p class="close_time">영업종료</p>
                            </c:when>
                            <c:otherwise>
                                <p class="break_time">영업 정보 없음</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <!-- 배너 = 스위퍼 -->
                <div class="container_banner swiper rank_swiper0">
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
                <!-- 메인 박스의 메인 -->
                <div class="title_box">

                    <div class="review_box">
                        <!-- 별점 스코어 박스 -->
                        <div class="star_box">
                            <span class="empty_star review_star">
                                <p class="fill_star"></p>
                            </span>
                            <span class="review_score">${tmp.total_score_count}</span>
                        </div>

                        <!-- 별점 스코어 밑 후기 갯수 관련 -->
                        <div class="review_leng">후기 ${tmp.total_review_count}개</div>

                        <!-- 별점 스코어 밑 후기 갯수 관련 -->
                    </div>

                    <div class="rest_title_box">
                        <a href="./detail/${tmp.id}" class="rest_title">${tmp.name}</a>
                        <p class="rest_cate">${tmp.category}</p>
                    </div>
                    <div class="rest_info_box">
                        <p class="rest_d_addr">
                            <i class="rest_addr_icon"></i>
                            <c:if test="${tmp.address != ''}">
                                ${tmp.address}
                            </c:if>
                            <c:if test="${tmp.address == ''}">
                                주소정보 없음
                            </c:if>
                        </p>
                        <p class="rest_p_num">
                            <i class="rest_num_icon"></i>
                            <c:if test="${tmp.number != ''}">
                                ${tmp.number}
                            </c:if>
                            <c:if test="${tmp.number == ''}">
                                번호정보 없음
                            </c:if>
                        </p>
                        <p class="rest_info">
                            <i class="rest_info_icon"></i>
                            <c:if test="${tmp.reservation != ''}">
                                ${tmp.reservation}
                            </c:if>
                            <c:if test="${tmp.reservation == ''}">
                                영업정보 없음
                            </c:if>
                        </p>
                    </div>

                    <c:if test="${tmp.memo != 'null' && tmp.memo != ''}">
                        <div class="rest_intro">
                            <p>${tmp.memo}</p>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:forEach>

        </div>
    </div>
    <div class="move_top">
        <p>TOP</p>
        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
    </div>
</div>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
<script src="<c:url value='/resources/js/rank_page_score.js?1'/>"></script>
<script src="<c:url value="/resources/js/swiper.js"/>"></script>

<script>
$(document).ready(function () {
    //검색어 폼으로 검색
    $(document).on('keydown', '#search_keyword', function (e) {
        if (e.keyCode === 13) {
            e.preventDefault(); //폼 제출 방지
            $(".KeywordSearch").submit();
        }
    })

    let banner_img_length = $('.banner_img').length

    // 배너에 이미지 넣어주는 반복문
    for(i=0; i<banner_img_length; i++){
        $(`.count_${'${i}'}`).css({
            background: `url(<c:url value="/resources/img/dummy_img/${'${i+1}'}.jpg"/>) no-repeat center / cover`
        })
    }



})
</script>


<%--    // <div className="container_box main_box">--%>
<%--    //--%>
<%--    //     <!-- 맛집 랭킹단 -->--%>
<%--    //     <div className="rank_box">--%>
<%--    //         <p>TOP 1</p>--%>
<%--    //         <div className="time_box">--%>
<%--    //             <p className="time_icon"></p>--%>
<%--    //             <p className="open_time">영업중</p>--%>
<%--    //             <p className="break_time">브레이크 타임</p>--%>
<%--    //             <p className="close_time">영업종료</p>--%>
<%--    //         </div>--%>
<%--    //     </div>--%>
<%--    //--%>
<%--    //     <!-- 배너 = 스위퍼 -->--%>
<%--    //     <div className="container_banner swiper rank_swiper0">--%>
<%--    //         <div className="swiper-button-prev0 banner_btn_L"><p>&lt</p></div>--%>
<%--    //         <div className="swiper-button-next0 banner_btn_R"><p>&gt</p></div>--%>
<%--    //         <div className="main_banner swiper-wrapper">--%>
<%--    //             <div className="banner_img swiper-slide count_0"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_1"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_2"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_3"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_4"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_5"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_6"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_7"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_8"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_9"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_10"></div>--%>
<%--    //             <div className="banner_img swiper-slide count_11"></div>--%>
<%--    //         </div>--%>
<%--    //     </div>--%>
<%--    //--%>
<%--    //     <!-- 메인 박스의 메인 -->--%>
<%--    //     <div className="title_box">--%>
<%--    //--%>
<%--    //         <div className="review_box">--%>
<%--    //             <!-- 별점 스코어 박스 -->--%>
<%--    //             <div className="star_box">--%>
<%--    //                         <span className="empty_star review_star">--%>
<%--    //                             <p className="fill_star"></p>--%>
<%--    //                         </span>--%>
<%--    //                 <span className="review_score"></span>--%>
<%--    //             </div>--%>
<%--    //--%>
<%--    //             <!-- 별점 스코어 밑 후기 갯수 관련 -->--%>
<%--    //             <div className="review_leng">후기 23개</div>--%>
<%--    //--%>
<%--    //             <!-- 별점 스코어 밑 후기 갯수 관련 -->--%>
<%--    //         </div>--%>
<%--    //--%>
<%--    //         <div className="rest_title_box">--%>
<%--    //             <a href="./detail.html" className="rest_title">종찬식당</a>--%>
<%--    //             <p className="rest_cate">한식</p>--%>
<%--    //         </div>--%>
<%--    //         <div className="rest_info_box">--%>
<%--    //             <p className="rest_d_addr"><i className="rest_addr_icon"></i>서울시 강남구 역삼동 테헤란로</p>--%>
<%--    //             <p className="rest_p_num"><i className="rest_num_icon"></i>0507-1316-0924</p>--%>
<%--    //             <p className="rest_info"><i className="rest_info_icon"></i>예약가능, 주차가능, 배달가능, 포장불가</p>--%>
<%--    //             <p className="rest_time"><i className="rest_time_icon"></i>화요일 09:00 ~ 22:00</p>--%>
<%--    //         </div>--%>
<%--    //--%>
<%--    //--%>
<%--    //         <div className="rest_intro">--%>
<%--    //             <p>최고, 최상의 맛과 경험을 선사하는 퓨전 한식 전문점 종찬 식당입니다.</p>--%>
<%--    //         </div>--%>
<%--    //     </div>--%>
<%--    // </div>--%>

</body>
</html>