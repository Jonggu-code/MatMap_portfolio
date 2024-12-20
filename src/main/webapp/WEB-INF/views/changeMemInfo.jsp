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
    <title>회원 수정</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/register.css?6'/>">

</head>

<body>
<div class="wrap">
    <div class="container">
        <header>
            <div class="logo">
                <a href="#"><img src="./img/logo(400x100).png" alt=""></a>

            </div>
        </header>

        <form action="<c:url value='/changeMemInfo' />" id="signup_form" method="post" onsubmit="return validateAndSubmit();"
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
                <input type="file" id="file_upload" name="profile_image" accept="image/*" style="display: none;" onchange="previewImage(event)">
            </div>
        </div>

        <!-- 나머지 회원가입 폼 -->
        <div class="content">
            <div id="msg" class="msg">${ URLDecoder.decode(param.msg, "utf-8") }</div>



                <div class="form_contents">
                    <div class="form_list form_txt1">


                        <div class="ip1"><input type="text" name="user_id" id="user_id" required placeholder="아이디" class="input_txt" value="${member.user_id}" readonly></div>

                        <div class="ip2"><input type="password" name="password" id="password" required placeholder="비밀번호" class="input_txt" value=""></div>
                        <div class="ip2"><input type="password" id="passwordTrue" required placeholder="비밀번호 확인" class="input_txt" value="${ URLDecoder.decode(param.u_pw, "utf-8") }" ></div>
                        <div id="passwordMessage" style="margin-top: 5px;"></div>


                        <div class="ip3"><input type="email" name="email" id="email" required placeholder="이메일" class="input_txt" value="${member.email}" readonly></div>
                        <div class="ip3"><input type="text" name="address" required placeholder="주소" class="input_txt" value="${member.address}" ></div>


                    </div>

                    <div class="form_list">
                        <div class="form_input_box">
                            <input type="text" name="name" required placeholder="이름" id="name" class="input_txt1" value="${member.name}" readonly>
                            <input type="number" name="age" required placeholder="나이" class="input_txt1" value="${member.age}" readonly>
                        </div>
                        <div class="form_gender">
                            <ul class="gender_list">
                                <li class="radio_item">
                                    <input type="radio" id="gender1" name="gender" value="M" class="btn_rd">
                                    <label for="gender1">남</label>
                                </li>
                                <li class="radio_item">
                                    <input type="radio" id="gender2" name="gender" value="F" class="btn_rd">
                                    <label for="gender2">여</label>
                                </li>
                            </ul>
                        </div>

                        <div class="number_box">
                            <input type="tel" name="phone_number" id="phone_number" required placeholder="휴대전화번호" class="number_txt" value="${member.phone_number}">
                        </div>
                    </div>
                </div>
                <div class="txt_area">
                    <label for="introduce">
                        <div class="my">자기소개</div>
                        <textarea name="introduce" id="introduce" class="intro_txt" rows="3" cols="10" maxlength="50" style="resize: none;">${member.introduce}</textarea>
                    </label>
                </div>
                <div class="reg_btn_box">
                    <button type="submit" class="btn_sum btn_sum1">수정</button>
                    <button type="button" class="btn_sum btn_sum2" onclick="history.back()">취소</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="<c:url value='/resources/js/register.js?2'/>"></script>

</body>
</html>
