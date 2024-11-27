package com.matjongchan.app.controller;

import com.matjongchan.app.domain.entity.RestaurantDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.domain.entity.ReviewMenuDto;
import com.matjongchan.app.service.ReviewMenuService;
import com.matjongchan.app.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class ReviewController {
    @Autowired
    ReviewMenuService reviewMenuService;
    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviewWrite") // 리뷰 작성 첫 페이지 메서드 보여주기
    public String reviewWrite(HttpServletRequest request, RestaurantDto restaurantDto, Model m) {
        m.addAttribute("restaurantDto", restaurantDto);
        log.info(".//.////////////////////////////");
        HttpSession session = request.getSession();

        List<ReviewMenuDto> list = reviewMenuService.getListR(1);
        int listSize = list.size();

        // 로그인 했는지 확인
//        if(!loginChk(session)) {
//            // 로그인 안 했다면..
//            String toURL = "reviewWrite";
//            return "redirect:/login?toUrl="+toURL;
//        }
        return "reviewWrite";
    }

    @PostMapping("/showReviewWrite2")
    public String showReviewWrite2(ReviewDto reviewDto, Model model) {
        model.addAttribute("reviewDto", reviewDto);

        return "reviewWrite2";
    }

    private static final String F_PATH = "/Users/joohunkang/Desktop/Spring/MatMap_portfolio/src/main/webapp/resources/img";

    @PostMapping("/reviewWrite2") // 리뷰 작성 두 번째 페이지 메서드
    public String reviewWriteSubmit(HttpSession session, ReviewDto reviewDto, @RequestParam(value = "files", required = false) MultipartFile[] mf) {
        try {
            log.info(reviewDto.getContent());
//            String reviewer = (String)session.getAttribute("id");
            String reviewer = "김철수";
            reviewDto.setReviewer(reviewer);

            int rowCount = reviewService.write(reviewDto);

            if(rowCount != 1) {
                throw new Exception("글쓰기 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "글쓰기 실패";
        }
        return "forward:/";
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
