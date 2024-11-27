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
    <link rel="stylesheet" href="./css/login.css">
</head>
<body>
<div id="wrap" class="wrap">
    <header class="header">
        <div class="img_box">
            <a href="https://www.naver.com/">
                <img src="./img/logo(500x500).png" alt="">

            </a>
        </div>
    </header>

    <div class="container">
        <div class="contents">
            <form action="#" method="post">
                <input type="text" placeholder="아이디 또는 전화번호"name="user_id" value="" class="input_txt input_id" required>
                <input type="password" placeholder="비밀번호" name="password" value="" class="input_txt input_pw" required>
                <div class="keep_check">
                    <input type="checkbox" id="kp" class="keep keep_btn">
                    <label for="kp" class="keep keep_label">로그인 상태 유지</label>
                </div>
                <button type="submit" class="input_log">로그인</button>
            </form>
        </div>
    </div>

    <div class="find_wrap">
        <ul class="fd_ul">
            <a href="" role="button">
                <li class="find_li">회원가입</li>
            </a>
        </ul>
    </div>

</div>
</body>
</html>
