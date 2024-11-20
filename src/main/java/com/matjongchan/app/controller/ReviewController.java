package com.matjongchan.app.controller;

import com.matjongchan.app.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @GetMapping("/reviewWrite") // 전체 리스트 가져오는 메서드(10개)
    public String reviewWrite(HttpServletRequest request, Integer currPage, Integer pageSize, Model m) {
        // 세션 객체 불러오기
        HttpSession session = request.getSession();

        // 세션객체에 현재 로그인 기록이 있는지 확인
        if(!loginChk(session)) {
            // 로그인 안한 상태
            String toURL = "review";
            return "redirect:/login?toUrl="+toURL;
        }

        return "reviewList";
    }

    private boolean loginChk(HttpSession session) {
        // 세션읽어서 id 값 확인
        return session.getAttribute("id") != null;
        // 현재 로그인 한 상태면 true 리턴
        // 로그인 안한 상태면 false 리턴
    }

}
