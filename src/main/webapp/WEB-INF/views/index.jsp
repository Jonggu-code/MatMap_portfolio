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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css?dt=1"/>

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
                <a class="site_logo" href="<c:url value="/"/>">MatMap</a>
            </div>
            <div class="search_box" id="search">
                <form id="search_keyword" class="KeywordSearch" action='<c:url value="/search/keyword"/>'>
                    <fieldset class="fld_inside">
                        <legend class="screen_out">검색어 입력폼</legend>
                        <input type="text" class="search_input" id="search_keyword_query" name="keyword" autocomplete="off" placeholder="무엇을 먹어야 잘 먹었다고 소문날까?" maxlength="100">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M20.87,20.17l-5.59-5.59C16.35,13.35,17,11.75,17,10c0-3.87-3.13-7-7-7s-7,3.13-7,7s3.13,7,7,7c1.75,0,3.35-0.65,4.58-1.71 l5.59,5.59L20.87,20.17z M10,16c-3.31,0-6-2.69-6-6s2.69-6,6-6s6,2.69,6,6S13.31,16,10,16z"></path></g></svg>
                    </fieldset>
                </form>
            </div>
            <ul class="header_menu">
                <a class="header_menu_item" href="<c:url value="board"/>"><p>게시판</p></a>
                <a class="header_menu_item" href="./rank_page_score.html"><p>월간 맛집</p></a>
            </ul>
        </header>
        <main class="main">
            <c:forEach var="tmp" items="${sr}">
            <!-- 식당들은 main_item 클래스를 가진상태로 나열됨  -->
            <div class="main_item">
                <div class="main_item_box">
                    <div class="main_item_Lbox">
                        <a class="img" href="<c:url value="detail/${tmp.id}"/>">1</a>
                    </div>
                    <div class="main_item_Rbox">
                        <div class="item_title">

                            <!-- 식당 이름 -->
                            <a class="item_name" href="<c:url value="detail/${tmp.id}"/>">${tmp.name}</a>
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
                            <c:choose>
                                <c:when test='${tmp.address != ""}'>
                                    ${tmp.address}
                                </c:when>
                                <c:when test='${tmp.address == ""}'>
                                    식당 정보 없음
                                </c:when>
                            </c:choose>
                        </p>

                        <!-- 식당 영업시간정보 들어오는 단 / 빈칸이면 "영업 정보 없음"-->
                        <p class="item_time">
                            <i class="rest_num_icon"></i>
                            <c:choose>
                                <c:when test='${tmp.number != ""}'>
                                    ${tmp.number}
                                </c:when>
                                <c:when test='${tmp.number == ""}'>
                                    영업 정보 없음
                                </c:when>
                            </c:choose>
                        </p>
                        <!-- 식당 정보 들어오는 단 / 빈칸이면 "매장 정보 없음"-->
                        <p class="item_info">
                            <i class="rest_info_icon"></i>
                            <c:choose>
                                <c:when test='${tmp.reservation != ""}'>
                                    ${tmp.reservation}
                                </c:when>
                                <c:when test='${tmp.reservation == ""}'>
                                    매장 정보 없음
                                </c:when>
                            </c:choose>
                        </p>
                    </div>
                </div>

                <!-- 식당 메뉴 들어오는 단 -->
                <ul class="main_item_bot">
                    <c:forEach var="menu" items="${tmp.menu_name_list}">
                        <li>#${menu}</li>
                    </c:forEach>
                </ul>
                <!-- 리뷰 텍스트 들어오는 단 -->
                <c:choose>
                    <c:when test="${tmp.recentSimpleReview.id != null}">
                        <div class="review_box">
                            <div class="user_title">
                                <div class="user_name">
                                    ${tmp.recentSimpleReview.reviewer}
                                </div>
                                <div class="create_at">
                                    ${tmp.recentSimpleReview.create_at}
                                </div>
                            </div>
                            <div class="review_txt">
                                ${tmp.recentSimpleReview.content}
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </div>
            </c:forEach>
        </main>
    </aside>
    <div id="map">
        <div class="map_header">
            <div class="map_box_L">

                <!-- 지도 표시 지역 선택 -->
                <div class="choose_box choose_location">
                    <p>지도 표시 지역</p>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path d="m4 6 4 4 4-4"></path></svg>
                    <div class="location_box">
                        <ul class="c_address">
                            <li id="Seoul" class="c_addr_name c_addr_act">서울</li>
                            <li id="Gyeonggi" class="c_addr_name">경기</li>
                            <li id="Incheon" class="c_addr_name">인천</li>
                            <li id="Gangwon" class="c_addr_name">강원</li>
                            <li id="Daejeon" class="c_addr_name">대전</li>
                            <li id="Chungnam" class="c_addr_name">충남</li>
                            <li id="Chungbuk" class="c_addr_name">충북</li>
                            <li id="Gwangju" class="c_addr_name">광주</li>
                            <li id="Jeonnam" class="c_addr_name">전남</li>
                            <li id="Jeonbuk" class="c_addr_name">전북</li>
                            <li id="Sejong" class="c_addr_name">세종</li>
                            <li id="Daegu" class="c_addr_name">대구</li>
                            <li id="Gyeongnam" class="c_addr_name">경남</li>
                            <li id="Gyeongbuk" class="c_addr_name">경북</li>
                            <li id="Ulsan" class="c_addr_name">울산</li>
                            <li id="Busan" class="c_addr_name">부산</li>
                            <li id="Jeju" class="c_addr_name">제주</li>
                        </ul>
                        <ul class="d_address">
                            <li id="Seoul_all" class="d_addr_name">서울 전체</li>
                            <li id="Seoul_Gangnam" class="d_addr_name">강남구</li>
                            <li id="Seoul_Gangdong" class="d_addr_name">강동구</li>
                            <li id="Seoul_Gangbuk" class="d_addr_name">강북구</li>
                            <li id="Seoul_Gangseo" class="d_addr_name">강서구</li>
                            <li id="Seoul_Gwanak" class="d_addr_name">관악구</li>
                            <li id="Seoul_Gwangjin" class="d_addr_name">광진구</li>
                            <li id="Seoul_Guro" class="d_addr_name">구로구</li>
                            <li id="Seoul_Geumcheon" class="d_addr_name">금천구</li>
                            <li id="Seoul_Nowon" class="d_addr_name">노원구</li>
                            <li id="Seoul_Dobong" class="d_addr_name">도봉구</li>
                            <li id="Seoul_Dongdaemun" class="d_addr_name">동대문구</li>
                            <li id="Seoul_Dongjak" class="d_addr_name">동작구</li>
                            <li id="Seoul_Mapo" class="d_addr_name">마포구</li>
                            <li id="Seoul_Seodaemun" class="d_addr_name">서대문구</li>
                            <li id="Seoul_Seocho" class="d_addr_name">서초구</li>
                            <li id="Seoul_Seongdong" class="d_addr_name">성동구</li>
                            <li id="Seoul_Seongbuk" class="d_addr_name">성북구</li>
                            <li id="Seoul_Songpa" class="d_addr_name">송파구</li>
                            <li id="Seoul_Yangcheon" class="d_addr_name">양천구</li>
                            <li id="Seoul_Yeongdeungpo" class="d_addr_name">영등포구</li>
                            <li id="Seoul_Yongsan" class="d_addr_name">용산구</li>
                            <li id="Seoul_Eunpyeong" class="d_addr_name">은평구</li>
                            <li id="Seoul_Jongno" class="d_addr_name">종로구</li>
                            <li id="Seoul_Jung" class="d_addr_name">중구</li>
                            <li id="Seoul_Jungnang" class="d_addr_name">중랑구</li>
                        </ul>
                    </div>
                </div>
                <!-- 지도 표시 지역 선택창 -->

                <!-- 정렬 방식 선택 -->
                <div class="choose_box choose_align">
                    <p>맛집 정렬 순서</p>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path d="m4 6 4 4 4-4"></path></svg>

                    <!-- 정렬 방식 선택창 -->
                    <ul class="align_box">
                        <li class="align_menu align_review">리뷰 많은 순</li>
                        <li class="align_menu align_star">별점 높은 순</li>
                    </ul>
                </div>

                <!-- 음식점 태그 선택 -->
                <div class="choose_box choose_tag">
                    <p>전체</p>
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16"><path d="m4 6 4 4 4-4"></path></svg>

                    <!-- 음식점 태그 선택창 -->
                    <ul class="tag_box">
                        <li class="tag_menu tag_korean">한식</li>
                        <li class="tag_menu tag_usa">양식</li>
                        <li class="tag_menu tag_china">중식</li>
                        <li class="tag_menu tag_japan">일식</li>
                        <li class="tag_menu tag_cafe">카페</li>
                        <li class="tag_menu tag_chicken">치킨</li>
                        <li class="tag_menu tag_pizza">피자</li>
                        <li class="tag_menu tag_fastfood">패스트푸드</li>
                        <li class="tag_menu tag_bunsick">분식</li>
                        <li class="tag_menu tag_drink">술집</li>
                    </ul>
                </div>
                <div class="random_food"></div>
            </div>
            <div class="map_box_R">
                <div class="focus_map"></div>
                <div class="guest_menu" tabindex="-1">
                    <svg viewBox="0 0 24 24"><g><path d="M21,6H3V5h18V6z M21,11H3v1h18V11z M21,17H3v1h18V17z"></path></g></svg>
                    <div class="guest_icon"></div>
                    <div class="guest_menu_box"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b4e6868c7b5fe35c80d9b43d3190c872"></script>

<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="<c:url value="resources/js/map.js?1"/>"></script>
<script src="<c:url value="resources/js/index.js?1"/>"></script>

<script>
    $(document).ready(function (){

        // loadRestaurant();

        $(document).on('keydown', '#search_keyword', function (e){
            if(e.keyCode === 13){
                e.preventDefault(); //폼 제출 방지

                let page_size = 20;
                let offset = 0;
                let option = $('.choose_align').children('p').text();
                let category = $('.choose_tag').children('p').text();
                let c_address = $('.choose_location').children('p').text();
                let keyword = $('#search_keyword_query').val();
                if(option === "맛집 정렬 순서"){
                    option = null;
                }
                if(category === "전체"){
                    category = null;
                }
                if(c_address === "지도 표시 지역"){
                    c_address = null;
                }
                if(keyword === "무엇을 먹어야 잘 먹었다고 소문날까?"){
                    keyword = "";
                }

                $.ajax({
                    type: 'POST',
                    url: '<c:url value="/search/keyword"/>',
                    contentType: 'application/json; charset=UTF-8',
                    data : JSON.stringify({"page_size" : page_size, "offset" : offset, "keyword" : keyword}),
                    // data : JSON.stringify({"page_size" : page_size, "offset" : offset,
                    //         "option" : option, "category" : category, "c_address" : c_address}),
                    success:function (sr){
                        alert("정보 받아오기 성공");
                        console.log(sr);
                        // loadRestaurant();
                        console.log(page_size,offset,keyword)
                    },
                    error: function (request, status, error){
                        alert("정보 받아오기 실패");
                    }
                })

            }
        })

    })

</script>

</body>
</html>