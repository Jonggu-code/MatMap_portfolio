<%--
  Created by IntelliJ IDEA.
  User: joohunkang
  Date: 2024. 11. 26.
  Time: 오후 2:28
  To change this template use File | Settings | File Templates.
--%>
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
  <title>리뷰 작성 페이지-2</title>

  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="<c:url value='https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css'/>" />

  <link rel="stylesheet" href="<c:url value='/resources/css/review_page_2.css' /> ">
  <link rel="stylesheet" href="<c:url value='/resources/css/review_common.css' /> ">


  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>

<form action="<c:url value='/reviewWrite2' />" method="post" id="wrap2" enctype="multipart/form-data">

  <div class="selected_menu_list" style="display: none">
    <p>선택한 메뉴:</p>
    <ul>
      <c:forEach items="${paramValues['selected_menu[]']}" var="menuId">
        <li>메뉴 ID: ${menuId}</li>
        <input type="hidden" name="selected_menu[]" value="${menuId}">
      </c:forEach>
    </ul>
  </div>

  <input type="hidden" name="total_score" value="${reviewDto.total_score}">
  <input type="hidden" name="kind_score" value="${reviewDto.kind_score}">
  <input type="hidden" name="clean_score" value="${reviewDto.clean_score}">
  <input type="hidden" name="taste_score" value="${reviewDto.taste_score}">
  <script>
    console.log('${reviewDto.total_score}')
  </script>

  <fieldset class="review_main">
    <div class="logo_box">
      <a href="index.jsp" class="logo">맛맵 - 메인화면</a>
    </div>
    <!-- 메인 화면단 -->
    <div class="container contents_area">

      <!-- 식당 정보 나오는 단 -->
      <div class="header">
        <div class="rest_img_box"></div>
        <div class="rest_info_box">
          <p class="rest_title">종찬식당</p>
          <span>한식</span>
          <span>/</span>
          <span>2024년 11월 25일</span>
        </div>
      </div>

      <!-- 리뷰 사진 넣는 단 -->
      <div class="common_box img_box">
        <p>다녀오신 식당에서 촬영한 사진을 추가해주세요 !</p>
        <div class="add_img_btn">
          <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet"><g><path d="M12.7,12l6.6,6.6l-0.7,0.7L12,12.7l-6.6,6.6l-0.7-0.7l6.6-6.6L4.6,5.4l0.7-0.7l6.6,6.6l6.6-6.6l0.7,0.7L12.7,12z"></path></g></svg>
        </div>
        <input type="file" multiple="multiple" id="add_img" name="files">
      </div>

      <!-- 리뷰 텍스트 넣는 단 -->
      <div class="common_box txt_box">

        <textarea name="content" id="review_txt" maxlength="300" minlength="15" cols="6" placeholder="리뷰 작성시 리뷰를 보는 사용자와 사업자에게 욕설, 비방, 명예훼손성 표현은 자제 부탁드립니다 !" required></textarea>
        <div class="txt_limit">
          <span>0</span>
          <span>/</span>
          <span>300</span>
        </div>
      </div>


      <!-- 이전 버튼 / 등록 버튼 단 -->
      <div class="btn_box">
        <a href="./review_page_1.html">&lt; 이전 화면</a>
        <input type="submit" name="submit"  id="ddd" value="후기 등록">
      </div>
    </div>
  </fieldset>

</form>


<%--<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>--%>
<script src="<c:url value='/resources/js/review_common.js' /> "></script>
<%--<script src="../js/swiper.js"></script>--%>

</body>
</html>


