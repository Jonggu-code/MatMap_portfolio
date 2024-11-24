package com.matjongchan.app.controller;

import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviewWrite") // 리뷰 작성 메서드
    public String reviewWrite(HttpServletRequest request, Model m) {
        // 세션 정보 저장
        HttpSession session = request.getSession();

        // 로그인 했는지 확인
        if(!loginChk(session)) {
            // 로그인 안 했다면..
            String toURL = "reviewWrite";
            return "redirect:/login?toUrl="+toURL;
        }
        return "reviewList";
    }

    @PostMapping("/reviewWrite") // 리뷰 작성 메서드
    public String reviewWriteSubmit(HttpSession session, ReviewDto reviewDto) {

        try {
            String reviewer = (String)session.getAttribute("id");
            reviewDto.setReviewer(reviewer);

            int rowCount = reviewService.write(reviewDto);

            if(rowCount != 1) {
                throw new Exception("글쓰기 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "글쓰기 실패";
        }
        return "redirect:/reviewWrite";
    }

    @PostMapping("/modify") // 게시글 수정 메서드
    public String modify(ReviewDto reviewDto, HttpSession session) {
        // 유저 아이디 저장
        String reviewer = (String)session.getAttribute("id");

        reviewDto.setReviewer(reviewer);

        // 서비스단에 넘기기
        reviewService.modify(reviewDto);
        return "redirect:/";
    }


    @PostMapping("/remove") // 게시글 삭제 메서드
    public String remove(HttpSession session, Integer id, Model m) {

        // 글 쓴 사람인지 확인할때 사용할 용도 - 현재 로그인 한 사람 아이디
        String writer = (String) session.getAttribute("id");

        try {
            // bno번 글 읽어와서 boardDto 변수에 저장
            int rowCount = reviewService.remove(id, writer);

            if(rowCount != 1) {
                throw new Exception("삭제 실패");
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "삭제 실패";
        }

        return "redirect:/";
    }


    private boolean loginChk(HttpSession session) {
        return session.getAttribute("id") != null;
    }

}
