<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="uId" value="${sessionScope.id }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>맛맵 - 메인화면</title>

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css?d=2"/>

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
                        <input type="text" class="search_input" id="search_keyword_query" name="keyword" autocomplete="off" placeholder="무엇을 먹어야 잘 먹었다고 소문날까?" maxlength="100" value="${a_keyword}">
                        <svg class="search_submit" viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M20.87,20.17l-5.59-5.59C16.35,13.35,17,11.75,17,10c0-3.87-3.13-7-7-7s-7,3.13-7,7s3.13,7,7,7c1.75,0,3.35-0.65,4.58-1.71 l5.59,5.59L20.87,20.17z M10,16c-3.31,0-6-2.69-6-6s2.69-6,6-6s6,2.69,6,6S13.31,16,10,16z"></path></g></svg>
                    </fieldset>
                </form>
            </div>
            <ul class="header_menu">
                <a class="header_menu_item" href="<c:url value="board"/>"><p>게시판</p></a>
                <a class="header_menu_item" href="<c:url value="rank"/>"><p>월간 맛집</p></a>
            </ul>
        </header>
        <main class="main">

            <div class="main_up"></div>
            <div class="main_down">
                <div class="pagination">
<%--                    <c:if test="${tmp.show_prev}">--%>
<%--                        <button class="page_btn" id="prev_page">&lt;</button>--%>
<%--                    </c:if>--%>

<%--                    <c:forEach var="i" begin="${tmp.begin_page}" end="${tmp.end_page}">--%>
<%--                        <a class="page ${i==tmp.curr_page ? "page_active" : ""}" id="page_no${i}">${i}</a>--%>
<%--                    </c:forEach>--%>

<%--                    <c:if test="${tmp.show_next}">--%>
<%--                        <button class="page_btn" id="next_page">&gt;</button>--%>
<%--                    </c:if>--%>
                </div>
            </div>
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
                <div class="focus_map">
                    <svg xmlns="http://www.w3.org/2000/svg"><path stroke="#000" stroke-linejoin="round" stroke-width="1.5" d="M20 4 3 11l7 3 3 7 7-17Z"></path></svg>
                </div>
                <div class="guest_menu" tabindex="-1">
                    <svg viewBox="0 0 24 24"><g><path d="M21,6H3V5h18V6z M21,11H3v1h18V11z M21,17H3v1h18V17z"></path></g></svg>
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
    </div>
</div>
</body>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b4e6868c7b5fe35c80d9b43d3190c872"></script>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="<c:url value="resources/js/map_hoho.js"/>"></script>
<script src="<c:url value="resources/js/index_hoho.js"/>"></script>
</html>

<script>
    $(document).ready(function (){
        let target_1 = document.getElementsByClassName('map_box_L')[0];
        let config = { attributes: true, childList: true, subtree: true };

        // 다른창에서 검색창으로 검색시 -------
        if("${a_keyword}" != null && "${a_keyword}" !== ''){
            loadAjax();
        }else{
            // 처음 불러올떄 호출---------------------
            $.ajax({
                method: 'POST',
                cache: false,
                url: 'http://localhost:8080/search',
                contentType: 'application/json; charset=UTF-8',
                data : JSON.stringify({"page_size" : 20, "offset" : 1,
                    "option" : null, "category" : null, "c_address" : null}),
                success:function (sr){
                    $('.main_up').html(loadRestaurant(sr));
                    $('.pagination').html(loadPagination(sr));
                    xy_location(sr);
                    create_marker();
                },
                error: function (request, status, error){
                    alert("정보 받아오기 실패 1");
                }
            })
        }

        let callback = function (mutationsList, observer){
            for (let mutation of mutationsList){
                if(mutation.type == 'childList'){
                    loadAjax();
                }
            }
        };
        let observer = new MutationObserver(callback);
        observer.observe(target_1,config);

        //검색어 창으로 검색어 넣은 검색 ---------------------------
        $(document).on('keydown', '#search_keyword', function (e){
            if(e.keyCode === 13){
                e.preventDefault(); //폼 제출 방지
                loadAjax();
            }
        })

        // 현재 보고있는 지도 기반 검색-----------------------------
        $('.focus_map').click(function (){
            let page_size = 20;
            let offset = 0;
            let option = $('.choose_align').children('p').text();
            let category = $('.choose_tag').children('p').text();
            let c_address = $('.choose_location').children('p').text();
            if(option === "맛집 정렬 순서"){
                option = null;
            }
            if(category === "전체"){
                category = null;
            }
            if(c_address === "지도 표시 지역"){
                c_address = null;
            }
            let loc = getInfo();

            let sw_x = loc.swLatLng.getLat();
            let sw_y = loc.swLatLng.getLng();
            let ne_x = loc.neLatLng.getLat();
            let ne_y = loc.neLatLng.getLng();
            $.ajax({
                method: 'POST',
                cache: false,
                url: 'http://localhost:8080/search/near',
                contentType: 'application/json; charset=UTF-8',
                data : JSON.stringify({"page_size" : page_size, "offset" : offset,
                    "option" : option, "category" : category, "c_address" : c_address,
                    "loc_nw_x" : ne_x, "loc_nw_y" : ne_y, "loc_se_x" : sw_x, "loc_se_y" : sw_y}),
                success:function (sr){
                    $('.main_up').html(loadRestaurant(sr));
                    $('.pagination').html(loadPagination(sr));
                    xy_location(sr);
                    create_marker();
                },
                error: function (request, status, error){
                    alert("정보 받아오기 실패 2");
                }

            })
        })
        //     검색버튼누르면 검색기능으로
    })

    function loadRestaurant(sr){
        let tmp_html= "";
        if(sr.length < 1){
            tmp_html+=`
                <!-- 검색결과가 안나올때 표시되는 화면 -->
                <div class="no_search_box">
                    <img class="no_search_img" src="<c:url value="/resources/img/dummy_img/no_search.png"/>" alt="">
                    <p class="no_search_main">검색결과가 없어요.</p>
                    <p class="no_search_sub">
                        검색어가 정확한지 다시 한번 확인해 주세요.<br>
                        식당 이름이 정확하지 않아도 검색이 가능해요.
                    </p>
                </div>
            `
        }
        else{
            sr.forEach(function (tmp){

                let tmp_address = tmp.address || "식당 정보 없음";
                let tmp_time = tmp.number || "영업 정보 없음" ;
                let tmp_info = tmp.reservation || "매장 정보 없음";
                let tmp_review = tmp.recentSimpleReview || null;
                let tmp_total_score = tmp.total_score_count || 0.0;
                let tmp_total_review = tmp.total_review_count || 0;

                tmp_html+=`
                <div class="main_item">
                    <div class="main_item_box">
                        <div class="main_item_Lbox" style="background: url('<c:url value="${'${tmp.image_url}'}"/>') no-repeat center / cover">
                            <a class="img" href="<c:url value="detail/${'${tmp.id}'}"/>">1</a>
                        </div>
                        <div class="main_item_Rbox">
                            <div class="item_title">

                                <!-- 식당 이름 -->
                                <a class="item_name" href="<c:url value="detail/${'${tmp.id}'}"/>">${'${tmp.name}'}</a>
                                <div class="item_star">
                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2Z"></path></svg>

                                    <!-- 식당 전체 평점의 평균 -->
                                    <p>${'${tmp_total_score}'}</p>

                                    <!-- 식당에 관련된 후기 갯수 -->
                                    <p>(${'${tmp_total_review}'})</p>
                                </div>
                            </div>

                            <!-- 식당 주소정보 들어오는 단 -->
                            <p class="item_addr">
                                <i class="rest_addr_icon"></i>
                                ${'${tmp_address}'}
                            </p>

                            <!-- 식당 영업시간정보 들어오는 단 / 빈칸이면 "영업 정보 없음"-->
                            <p class="item_time">
                                <i class="rest_num_icon"></i>
                                ${'${tmp_time}'}
                            </p>
                            <!-- 식당 정보 들어오는 단 / 빈칸이면 "매장 정보 없음"-->
                            <p class="item_info">
                                <i class="rest_info_icon"></i>
                                ${'${tmp_info}'}
                            </p>
                        </div>
                    </div>
                    <!-- 식당 메뉴 들어오는 단 -->
                    <ul class="main_item_bot">`
                tmp.menu_name_list.forEach(function (menu){
                    tmp_html+=`<li>#${"${menu}"}</li>`
                })
                tmp_html+=`</ul>`;
                if(tmp_review !== null){
                    tmp_html+=`
                    <!-- 리뷰 텍스트 들어오는 단 -->
                    <c:choose>
                        <c:when test="${'${tmp.recentSimpleReview.id}' != null}">
                            <div class="review_box">
                                <div class="user_title">
                                    <div class="user_name">
                                            ${"${tmp.recentSimpleReview.reviewer}"}
                                    </div>
                                    <div class="create_at">
                                            ${"${tmp.recentSimpleReview.create_at}"}
                                    </div>
                                </div>
                                <div class="review_txt">
                                        ${"${tmp.recentSimpleReview.content}"}
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                    `
                }
                tmp_html+= `</div>`;
            });
        }
        return tmp_html;
    }
    function loadPagination(sr){
        if(sr.length < 1){
            return "";
        }
        let show_prev = sr[0].searchCondition.show_prev == null ? false : sr[0].searchCondition.show_prev;
        let show_next = sr[0].searchCondition.show_next;
        let begin_page = sr[0].searchCondition.begin_page;
        let end_page = sr[0].searchCondition.end_page;
        let curr_page = sr[0].searchCondition.curr_page;
        tmp_html = "";

        if(show_prev){
            tmp_html+= `<button class="page_btn" id="prev_page">&lt;</button>`
        }
        for(let i = begin_page; i<=end_page; i++){
            if(i === curr_page){
                tmp_html+= `<a class="page page_act" id="page_no${'${i}'}">${'${i}'}</a>`
            }else{
                tmp_html+= `<a class="page" id="page_no${'${i}'}">${'${i}'}</a>`
            }
        }
        if(show_next){
            tmp_html+= `<button class="page_btn" id="next_page">&gt;</button>`
        }
        return tmp_html;
    }
    //이전
    $(document).on('click', '#prev_page', function(){
        let first_num = $('.pagination a').first().text();
        loadAjax(Number(first_num)-1);
    })
    //다음
    $(document).on('click', '#next_page', function(){
        let last_num = $('.pagination a').last().text();
        loadAjax(Number(last_num)+1);
    })
    // pagination 관련 함수
    $(document).on('click', '.page', function(){
        $('.page').removeClass('page_act')
        $(this).addClass('page_act')
        let click_number = $(this).text()
        //여기에서 AJAX 호출해야해
        loadAjax(click_number);
    })
    function loadAjax(tmp_curr_page){
        let curr_page = tmp_curr_page != null ? tmp_curr_page : 0;
        let page_size = 20;
        let offset = (tmp_curr_page-1) * page_size == null ? 0 : (tmp_curr_page-1) * page_size;
        let keyword = $('#search_keyword_query').val();
        let option = $('.choose_align').children('p').html();
        let category = $('.choose_tag').children('p').html();
        let c_address = $('.choose_location').children('p').html();
        if(keyword === "무엇을 먹어야 잘 먹었다고 소문날까?"){
            keyword = "";
        }
        if(option === "맛집 정렬 순서"){
            option = null;
        }
        if(category === "전체"){
            category = null;
        }
        if(c_address === "지도 표시 지역"){
            c_address = null;
        }
        if(option === "리뷰 많은 순"){
            option = "R";
        }else{
            option = "P";
        }
        $.ajax({
            method: 'POST',
            cache: false,
            url: 'http://localhost:8080/search',
            contentType: 'application/json; charset=UTF-8',
            data : JSON.stringify({"page_size" : page_size, "offset" : offset, "keyword" : keyword,
                "option" : option, "category" : category, "c_address" : c_address, "curr_page" : curr_page}),
            success:function (sr){
                $('.main_up').html(loadRestaurant(sr));
                $('.pagination').html(loadPagination(sr));
                xy_location(sr);
                create_marker();
                $('.main').scrollTop();
            },
            error: function (request, status, error){
                alert("정보 받아오기 실패 3");
            }
        })
    }

</script>