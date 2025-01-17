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
    <title>로그인</title>
    <link rel="stylesheet" href="<c:url value = '/resources/css/login.css'/>">
</head>
<body>
<div id="wrap" class="wrap">
    <header class="header">
        <div class="img_box">
            <a href="<c:url value = '/'/>">
                <img src="<c:url value='/resources/img/logo_500x500.png'/>" alt="">
            </a>
        </div>
    </header>

    <div class="container">
        <div class="contents">
            <form action="<c:url value = '/login'/>" method="post" onsubmit="return valid_chk(this)">

                <input type = "hidden" name = "toUrl" value="${param.toUrl}">
                <div id="msg" class="msg">${param.msg}</div>
<%--                ${URL.Decoder.decode(param.msg, "utf-8")}--%>
                <input type="text" placeholder="아이디 또는 전화번호" name="user_id" value="${cookie.id.value}" class="input_txt input_id">
                <input type="password" placeholder="비밀번호" name="user_pw" value="" class="input_txt input_pw">
                <div class="keep_check">
                    <input type="checkbox" id="kp" class="keep keep_btn" name = "remember_id" ${empty cookie.id.value?"":"checked"}>
                    <label for="kp" class="keep keep_label">아이디 기억하기</label>
                </div>
                <button type="submit" class="input_log">로그인</button>
            </form>
        </div>
    </div>

    <div class="find_wrap">
        <ul class="fd_ul">
            <a href="<c:url value = '/join'/>" role="button">
                <li class="find_li">회원가입</li>
            </a>
        </ul>
    </div>

</div>
<script src="<c:url value='/resources/js/login.js'/>"></script>
</body>
</html>
