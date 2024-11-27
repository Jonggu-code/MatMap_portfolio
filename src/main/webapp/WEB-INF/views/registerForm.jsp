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
  <link rel="stylesheet" href="<c:url value='/resources/css/register.css'/>">

</head>
<body>
<div class="wrap">
  <div class="container">
    <header>
      <div class="logo">
        <a href="<c:url value='/main'/>">
          <img src="<c:url value='/resources/img/logo_400x100.png'/>" alt="로고 이미지">

        </a>

      </div>
    </header>

    <form action="<c:url value='/join' />" id="signup_form" method="post" onsubmit="return validateAndSubmit();"
          enctype="multipart/form-data">

    <!-- 프로필 이미지 영역 -->
    <div class="profile_area">
      <div class="profile_inner">
        <a href="#">
          <img id="profileImage" src="https://static.nid.naver.com/images/web/user/default.png" alt="프로필 이미지" class="profile_img">
        </a>
      </div>
      <div class="change_profile_btn">
        <label for="file_upload" class="change_btn">설정</label>
        <input type="file" id="file_upload" name="profile_image" accept="image/*" style="display: none;" onchange="previewImage(event)" >
      </div>
    </div>

    <!-- 나머지 회원가입 폼 -->
    <div class="content">
      <div id="msg" class="msg">${ URLDecoder.decode(param.msg, "utf-8") }</div>



        <div class="form_contents">
          <div class="form_list form_txt1">


            <div class="ip1"><input type="text" id="user_id" name="user_id" required placeholder="아이디" class="input_txt" value="${ URLDecoder.decode(param.u_id, "utf-8") } " ></div>

            <div class="ip2"><input type="password" id="password"  name="password" required placeholder="비밀번호" class="input_txt" value="${ URLDecoder.decode(param.u_pw, "utf-8") }" ></div>
            <div class="ip3"><input type="email" id="email"  name="email" required placeholder="이메일" class="input_txt" value="${ URLDecoder.decode(param.u_email, "utf-8") }" ></div>
            <div class="ip3"><input type="text" name="address" required placeholder="주소" class="input_txt" value="${ URLDecoder.decode(param.u_address, "utf-8") }" ></div>

          </div>

          <div class="form_list">
            <input type="text" name="name" required placeholder="이름" id="name" class="input_txt1" value="${ URLDecoder.decode(param.u_name, "utf-8") }" >
            <input type="number" name="age" required placeholder="나이" class="input_txt1" value="${ URLDecoder.decode(param.u_age, "utf-8") }" >
            <div class="form_gender">
              <ul class="gender_list">
                <li class="radio_item">
                  <input type="radio" id="gender1" name="gender" value="M" required class="btn_rd" >
                  <label for="gender1">남</label>
                </li>
                <li class="radio_item">
                  <input type="radio" id="gender2" name="gender" value="F" required class="btn_rd">
                  <label for="gender2">여</label>
                </li>
              </ul>
            </div>

            <div class="number_box">
              <input type="tel" id="phone_number" name="phone_number" required placeholder="휴대전화번호" class="number_txt" value="${ URLDecoder.decode(param.u_pnumer, "utf-8") }" >
            </div>
          </div>
        </div>
        <div class="txt_area">
          <label for="introduce">
            <div class="my">자기소개</div>
            <textarea name="introduce" id="introduce" class="intro_txt" rows="3" cols="10" maxlength="50" style="resize: none;">${ URLDecoder.decode(param.u_introduce, "utf-8") }</textarea>
          </label>
        </div>
        <button type="submit" class="btn_sum" >회원가입</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="<c:url value='/resources/js/register.js'/>"></script>


</body>
</html>
