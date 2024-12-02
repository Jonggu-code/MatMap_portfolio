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
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value = '/resources/css/myPage.css'/>">
</head>
<body>




<div id="wrap" class="wrap">
    <div class="inner">
        <!-- 좌측 메뉴 -->
        <div class="aside">
            <!-- 좌측 프로필  -->
            <div class="aside_menu1">
                <div class="profile_imgBox">
                    <img th:src="@{'/resources/img/' + ${memberImage != null ? memberImage.img_url : 'https://static.nid.naver.com/images/web/user/default.png'}}"
                         alt="프로필 이미지" class="profile_img">
                </div>

            <%--                <div class="profile_imgBox">--%>
<%--                    <img src="https://static.nid.naver.com/images/web/user/default.png" alt="" class="profile_img">--%>
<%--                </div>--%>


                <div class="profile_menu">
                    <div>후기</div>
                    <div>${reviewCount}개</div>  <!-- 작성한 리뷰 개수 -->
                    <div>가입기간</div>
                    <div>${member.create_at}</div>  <!-- 가입일 -->
                </div>
            </div>
            <!-- 좌측 인증 정보 -->
            <div class="aside_menu2">
                <div class="menu2_head"><h3>나의 인증 정보</h3></div>
                <div class="certification">
                    <ul class="ctf_checkBox1">
                        <li class="check_menu">전화번호:</li>
                        <li class="check_menu">이메일:</li>
                        <li class="check_menu">성별:</li>
                    </ul>
                    <ul class="ctf_checkBox2">
                        <li class="check_list">${member.phone_number}</li>  <!-- 전화번호 -->
                        <li class="check_list">${member.email}</li> <!-- 이메일 -->
                        <li class="check_list">${member.gender}</li>    <!-- 성별 -->
                    </ul>
                </div>
            </div>
        </div>

        <!-- 메인 메뉴 -->
        <main class="main">
            <!-- 1번 프로필 영역 -->
            <div class="box box1">
                <h2>${member.name}님 소개</h2> <!-- 회원이름 -->
                <input type="button" class="fix_btn" value="프로필 수정하기">
                <div class="text_area">${member.introduce}</div>    <!-- 자기소개 -->
            </div>

            <!-- 2번 음식점 영역 -->
            <div class="box box2">
                <div class="box2_head">
                    <h2>내가 찜한 음식점</h2>
                    <a href="#">
                        <input type="button" value="목록" class="btn_mok">
                    </a>
                </div>

                <div class="contents_area">

                        <c:choose>
                            <c:when test="${favorites.size() > 0}">
                                <!-- 첫 번째 찜한 목록 -->
                                <div class="contentsBox menu1">
                                    <div class="menu_img"></div>
                                    <div class="menu_infor">
                                        <c:set var="firstFavorite" value="${favorites[0]}" />
                                        <div class="txt food">${firstFavorite.restaurant_name}</div>
                                        <div class="txt tag">${firstFavorite.c_address} ${firstFavorite.d_address}</div>

                                        <div class="txt rating">
                                            <strong>별점: ${firstFavorite.total_score_count} / 5</strong>
                                        </div>

                                        <div class="txt stimg">
                                            <img src="img/img/review_star_fill.png" alt="">
                                            <img src="img/img/review_star_fill.png" alt="">
                                            <img src="img/img/review_star_fill.png" alt="">
                                            <img src="img/img/review_star_fill.png" alt="">
                                            <img src="img/img/review_star_fill.png" alt="">
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <!-- 찜한 음식점이 없을 경우 "정보 없음" 표시 -->
                                <div class="contentsBox menu1">
                                    <div class="menu_img"></div>
                                    <div class="menu_infor">
                                        <div class="txt food">정보 없음</div>
                                        <div class="txt tag">찜한 음식점이 없습니다.</div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>


                    <c:choose>
                        <c:when test="${favorites.size() > 1}">
                            <!-- 두 번째 찜한 음식점 -->
                            <div class="contentsBox menu2">
                                <div class="menu_img"></div>
                                <div class="menu_infor">
                                    <c:set var="secondFavorite" value="${favorites[1]}" />
                                    <div class="txt food">${secondFavorite.restaurant_name}</div>
                                    <div class="txt tag">${secondFavorite.c_address} ${secondFavorite.d_address}</div>

                                    <div class="txt rating">
                                        <strong>별점: ${secondFavorite.total_score_count} / 5</strong>
                                    </div>

                                    <div class="txt stimg">
                                        <img src="img/img/review_star_fill.png" alt="">
                                        <img src="img/img/review_star_fill.png" alt="">
                                        <img src="img/img/review_star_fill.png" alt="">
                                        <img src="img/img/review_star_fill.png" alt="">
                                        <img src="img/img/review_star_fill.png" alt="">
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- 두 번째 찜한 음식점이 없을 경우 "정보 없음" 표시 -->
                            <div class="contentsBox menu2">
                                <div class="menu_img"></div>
                                <div class="menu_infor">
                                    <div class="txt food">정보 없음</div>
                                    <div class="txt tag">찜한 음식점이 없습니다.</div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <!-- 3번 리뷰 영역 -->
            <div class="box box3">
                <div class="big_pan">
                    <!-- 첫 번째 리뷰가 있을 경우 -->
                    <c:choose>
                        <c:when test="${not empty reviews[0]}">
                            <div class="review_box">
                                <div class="review_head">
                                    <div class="name">${reviews[0].restaurantName}</div>
                                    <div class="time">${reviews[0].create_at}</div>
                                </div>
                                <div class="review_txt">
                                    <div><strong>${reviews[0].title}</strong></div>
                                    <div>${reviews[0].content}</div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- 첫 번째 리뷰가 없을 경우 "정보 없음" 표시 -->
                            <div class="review_box">
                                <div class="review_head">
                                    <div class="name">정보 없음</div>
                                    <div class="time"></div>
                                </div>
                                <div class="review_txt">
                                    <div>작성한 리뷰가 없습니다.</div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <!-- 두 번째 리뷰가 있을 경우 -->
                    <c:choose>
                        <c:when test="${not empty reviews[1]}">
                            <div class="review_box">
                                <div class="review_head">
                                    <div class="name">${reviews[1].restaurantName}</div>
                                    <div class="time">${reviews[1].create_at}</div>
                                </div>
                                <div class="review_txt">
                                    <div><strong>${reviews[1].title}</strong></div>
                                    <div>${reviews[1].content}</div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- 두 번째 리뷰가 없을 경우 "정보 없음" 표시 -->
                            <div class="review_box">
                                <div class="review_head">
                                    <div class="name">정보 없음</div>
                                    <div class="time"></div>
                                </div>
                                <div class="review_txt">
                                    <div>작성한 리뷰가 없습니다.</div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

        </main>
    </div>
</div>
</body>
</html>
