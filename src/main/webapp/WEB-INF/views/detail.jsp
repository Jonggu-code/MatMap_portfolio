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
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>맛맵 - 음식점 세부정보</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link rel="stylesheet" href="<c:url value='/resources/css/common.css?1' /> ">
    <link rel="stylesheet" href="<c:url value='/resources/css/detail.css?3' /> ">

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
                            <p class="fill_star" id="star_main"></p>
                        </span>
                        <span id="review_score" class="score_main">${total_score}</span>
                    </div>

                    <!-- 별점 스코어 밑 후기 갯수 관련 -->
                    <div id="review_leng">후기 ${reviewCount}개</div>
                </div>

                <!-- 식당 정보 관련 박스 -->
                <div id="rest_title_box">
                    <p id="rest_title">${restaurantDetail.restaurant_name}</p>
                    <p id="rest_cate">${restaurantDetail.restaurant_category}</p>
                    <div class="rest_save">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet">
                            <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"></path>
                        </svg>
                    </div>
                </div>
                <div id="rest_info_box">
                    <p id="rest_d_addr"><i class="rest_addr_icon"></i>${restaurantDetail.restaurant_address}</p>
                    <p id="rest_p_num"><i class="rest_num_icon"></i>${restaurantDetail.restaurant_number}</p>
                    <p id="rest_info"><i class="rest_info_icon"></i>${restaurantDetail.restaurant_reservation.equals("")?"예약정보 없음":restaurantDetail.restaurant_reservation}</p>
                </div>
                <c:if test="${!empty restaurantDetail.restaurant_memo}">
                    <p id="rest_intro">${restaurantDetail.restaurant_memo}</p>
                </c:if>
            </div>


            <!-- 영업 시간 박스 : 타이틀 -->
            <div class="container_box" id="sub_box">
                <div id="rest_time_box">
                    <p id="time_title">영업시간</p>
                    <div class="time_box">
                        <p class="time_icon"></p>

                        <c:choose>
                            <c:when test="${restaurantDetail.getToday_business_state().equals('열림')}">
                                <p class="open_time">${restaurantDetail.getToday_business_state()}</p>
                            </c:when>
                            <c:when test="${restaurantDetail.getToday_business_state().equals('브레이크 타임')}">
                                <p class="break_time">${restaurantDetail.getToday_business_state()}</p>
                            </c:when>
                            <c:otherwise>
                                <p class="close_time">${restaurantDetail.getToday_business_state()}</p>
                            </c:otherwise>

                        </c:choose>
                    </div>
                </div>

                <!-- 영업 시간 정보 -->
                <div class="day_box">
                    <p>월  ${restaurantDetail.business_hours_dto.mon.equals("")?"휴무":restaurantDetail.business_hours_dto.mon}</p>
                    <p>화  ${restaurantDetail.business_hours_dto.tue.equals("")?"휴무":restaurantDetail.business_hours_dto.tue}</p>
                    <p>수  ${restaurantDetail.business_hours_dto.wed.equals("")?"휴무":restaurantDetail.business_hours_dto.wed}</p>
                    <p>목  ${restaurantDetail.business_hours_dto.thu.equals("")?"휴무":restaurantDetail.business_hours_dto.thu}</p>
                    <p>금  ${restaurantDetail.business_hours_dto.fri.equals("")?"휴무":restaurantDetail.business_hours_dto.fri}</p>
                    <p>토  ${restaurantDetail.business_hours_dto.sat.equals("")?"휴무":restaurantDetail.business_hours_dto.sat}</p>
                    <p>일  ${restaurantDetail.business_hours_dto.sun.equals("")?"휴무":restaurantDetail.business_hours_dto.sun}</p>
                    <p>브레이크 타임:  ${restaurantDetail.business_hours_dto.break_time.equals("")?"X":restaurantDetail.business_hours_dto.break_time}</p>
                </div>
            </div>

            <div class="container_box" id="menu_container">
                <p class="container_title menu_con_title">${restaurantDetail.restaurant_name} 메뉴</p>
                <c:forEach var="menuList" items="${restaurantDetail.menu_detail_list}">
                    <div class="menu_box">
                        <div class="menu_img" style="background : url(<c:url value='${menuList.menu_image_url}'/>) no-repeat center / cover"></div>
                        <div class="menu_title_box">
                            <p class="menu_title">${menuList.menu_name}</p>
                            <p class="menu_price">${menuList.menu_price} 원</p>
                        </div>
                    </div>
                </c:forEach>
            </div>


            <!-- 식당 사진 박스 (후기에 올린 이미지들) -->
            <%--                            ///////////////////////////////////////////////--%>
            <%--                            ///////////////////////////////////////////////--%>
            <%--                            ///////////////////////////////////////////////--%>
            <%--                            리뷰 이미지 확인해주세요--%>
            <%--                            ///////////////////////////////////////////////--%>
            <%--                            ///////////////////////////////////////////////--%>
            <%--                            ///////////////////////////////////////////////--%>
            <div class="container_box" id="img_container">
                <h1 class="container_title">${restaurantDetail.restaurant_name} 이미지 ${otherImagesCount}건</h1>

                <!-- 식당 이미지 박스 (제이쿼리 append 넣을 div) -->
                <div class="img_con_box">
                    <c:forEach var="restaurantImages" items="${restaurantImages}">
                        <div class="rest_img" style="display: none; background: url(<spring:url value='${restaurantImages}' />) red no-repeat center / cover;"></div>
                    </c:forEach>
                </div>
                <div class="img_con_more_btn">
                    <p>사진 더 불러오기</p>
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
                </div>
            </div>

            <!-- 현재 식당 태그 관련 추천 맛집 -->
        <c:choose>
            <c:when test="${relationRestaurant.size()!=0}">
            <div class="container_box" id="reco_container" style="top: 285px">
                <div class="container_title">
                    <p>${restaurantDetail.restaurant_category}</p>
                    <p>추천 맛집</p>
                </div>
                <c:forEach var="relList" items="${relationRestaurant}" begin="0" end="2">
                 <div class="reco_box reco_box1">
                    <div class="reco_title_box">
                        <div class="reco_title">${relList.restaurant_name}</div>
                        <div class="reco_score">
                            <span class="reco_empty_star reco_star">
                                <p class="reco_fill_star"></p>
                            </span>
                            <span class="reco_score">${restaurant_total_score_count}</span>
                            <span>(${restaurant_total_review_count})</span>
                        </div>
                    </div>
                    <div class="reco_addr">${relList.restaurant_address}</div>
                    <div class="reco_img_box">
                        <c:forEach var="tmp_img" items="${relList.restaurant_image_url_list}">
                            <div class="reco_img reco_img1" style="background: url(<c:url value='${tmp_img}'/>) no-repeat center / cover"></div>
                        </c:forEach>
                    </div>
                </div>
                </c:forEach>
                <div class="move_top">
                    <p>TOP</p>
                    <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M18,9l-6,6L6,9H18z"></path></g></svg>
                </div>
            </div>
            </c:when>
        </c:choose>



            <!-- 식당 후기 박스  -->
            <div class="container_box" id="review_container">
                <div class="rev_con_title">
                    <h1 class="container_title">종찬식당 방문자 평가 ${reviewCount==null?"리뷰가 없습니다.":reviewCount}건</h1>
                    <a href="<c:url value='/reviewWrite/${id}'/>">
                        <div class="rev_create_btn">후기를 작성해서 맛맵을 지원해주세요.</div>
                    </a>
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
                                <span class="rev_review_score score_ave">${total_score==null?"리뷰가 없습니다.":total_score}</span>
                            </div>
                            <div class="rev_con_score_starbox">
                                <span class="rev_review_star" class="rev_empty_star">
                                    <p class="rev_fill_star" id="star_kind"></p>
                                </span>
                                <span class="rev_review_score score_kind">${kind_score==null?"리뷰가 없습니다.":kind_score}</span>
                            </div>
                            <div class="rev_con_score_starbox">
                                <span class="rev_review_star" class="rev_empty_star">
                                    <p class="rev_fill_star" id="star_clean"></p>
                                </span>
                                <span class="rev_review_score score_clean">${clean_score==null?"리뷰가 없습니다.":clean_score}</span>
                            </div>
                            <div class="rev_con_score_starbox">
                                <span class="rev_review_star" class="rev_empty_star">
                                    <p class="rev_fill_star" id="star_taste"></p>
                                </span>
                                <span class="rev_review_score score_taste">${taste_score==null?"리뷰가 없습니다.":taste_score}</span>
                            </div>
                        </div>
                    </div>
                    <!-- 평점 관련 ( 점수 관련 ) -->
                    <div class="rev_con_score_list">
                        <div class="rcs_list_title">
                            <p>매우만족 (<i id="bar_biggood">${restaurantDetail.getRestaurant_total_rating().getFifth()}</i>)</p>
                            <p>만족 (<i id="bar_good">${restaurantDetail.getRestaurant_total_rating().getFourth()}</i>)</p>
                            <p>보통 (<i id="bar_common">${restaurantDetail.getRestaurant_total_rating().getThird()}</i>)</p>
                            <p>불만 (<i id="bar_bad">${restaurantDetail.getRestaurant_total_rating().getSecond()}</i>)</p>
                            <p>매우불만 (<i id="bar_bigbad">${restaurantDetail.getRestaurant_total_rating().getFirst()}</i>)</p>
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
                <c:choose>
                <c:when test="${reviewCount != 0}">
                    <!-- 조건2가 참일 때 실행될 내용 -->
                <div class="rev_con_main">
                    <c:forEach var="review" items="${reviews}">
                        <div class="rev_con_main_box tmppp${status.index}">
                            <!-- 리뷰 작성 날짜 -->
                            <div class="rc_create_at">${review.create_at}</div>
                            <!-- 유저 정보 -->
                            <div class="rc_user_box">
                                <div class="rc_user_icon" style='background: url("<spring:url value='${review.reviewerDto.profile}'/>") no-repeat center / cover'></div>
                                <div class="rc_user_info">
                                    <div id="user_name">${review.reviewer}</div>
                                    <div id="user_info">
                                        <p>성별</p>
                                        <c:if test="${review.reviewerDto.gender == 'M'}">
                                            <p>남</p>
                                        </c:if>
                                        <c:if test="${review.reviewerDto.gender == 'F'}">
                                            <p>여</p>
                                        </c:if>
                                        <p>나이</p>
                                        <p>${review.reviewerDto.age}</p>
                                    </div>
                                </div>
                            </div>
                            <!-- 유저가 준 평점 -->
                            <div class="rc_user_score_box">
                                <div class="con1">
                                    <p>전체</p>
                                    <p>${review.total_score==null?0.0:review.total_score}</p>
                                </div>
                                <div class="con1">
                                    <p>고객응대</p>
                                    <p>${review.kind_score==null?0.0:review.kind_score}</p>
                                </div>
                                <div class="con1">
                                    <p>청결도</p>
                                    <p>${review.clean_score==null?0.0:review.clean_score}</p>
                                </div>
                                <div class="con1">
                                    <p>맛</p>
                                    <p>${review.taste_score==null?0.0:review.taste_score}</p>
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
                            <div class="rc_img_box" style="display: flex; gap: 15px; height: auto">
                                <c:forEach var="otherImages" items="${review.otherImages}">
                                    <div class="review_img" style='background: url("<spring:url value='${otherImages}'/>") no-repeat center / cover'></div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                </c:choose>
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
<script src="<c:url value='/resources/js/common.js?4' /> "></script>
<script src="<c:url value='/resources/js/detail.js?6' /> "></script>
<script src="./js/swiper.js"></script>

<script>
    $(document).ready(function (){
        let countTmp = 0;
        let curTmp=1;
        let revTmp = document.getElementsByClassName('rev_con_main_box').length;


        let imgCountTmp = 0;
        let imgCurTmp=1;
        let imgTmp = document.getElementsByClassName('rest_img').length;

        if ($('.rest_img').length <=9){
            $('.rest_img').css({display:'block'})
            $('.img_con_more_btn').css({display:'none'})
        }
        else{
            for(let i=0; i<9; i++) {
                document.getElementsByClassName('rest_img')[i].style.display = 'flex';
            }
            imgCountTmp += 9;
        }
        document.querySelector('.img_con_more_btn').addEventListener('click', function() {
            if (imgCurTmp < Math.floor(imgTmp/9)){
                for(let i=imgCountTmp; i<imgCountTmp+9; i++) {
                    document.getElementsByClassName('rest_img')[i].style.display = 'flex';
                }
                imgCountTmp += 9;
                imgCurTmp ++;
            }
            else if (imgCurTmp == Math.floor(imgTmp/9)){
                $('.img_con_more_btn').css({display:'none'})
                for(let i=imgCountTmp; i<imgCountTmp+9; i++) {
                    document.getElementsByClassName('rest_img')[i].style.display = 'flex';
                }
            }
        })

        if ($('.rev_con_main_box').length<=5){
            $('.rev_con_main_box').css({display: 'flex'})
            $('.rev_con_more_btn').css({display:'none'})
        }
        else{
                for(let i=0; i<5; i++) {
                    document.getElementsByClassName('rev_con_main_box')[i].style.display = 'flex';
                }
                countTmp += 5;
        }

        document.querySelector('.rev_con_more_btn').addEventListener('click', function() {
            console.log(document.getElementsByClassName('rev_con_main_box').length)

            if (curTmp < Math.floor(revTmp/5)){
                for(let i=countTmp; i<countTmp+5; i++) {
                    document.getElementsByClassName('rev_con_main_box')[i].style.display = 'flex';
                }
                countTmp += 5;
                curTmp ++;
            }
            else if (curTmp == Math.floor(revTmp/5)){
                $('.rev_con_more_btn').css({display:'none'})
                for(let i=countTmp; i<countTmp+5; i++) {
                    document.getElementsByClassName('rev_con_main_box')[i].style.display = 'flex';
                }
            }
        })
    })
</script>

    </body>
</html>
