package com.matjongchan.app.controller;

import com.matjongchan.app.dao.OtherImageDao;
import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.entity.OtherImageDto;
import com.matjongchan.app.domain.entity.RestaurantDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.domain.entity.ReviewMenuDto;
import com.matjongchan.app.service.OtherImageService;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class ReviewController {
    @Autowired
    ReviewMenuService reviewMenuService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OtherImageService otherImageService;

    @GetMapping("/detail")
    public String getReviews(RestaurantDto restaurantDto, ReviewDto reviewDto, Model m, ReviewMenuDto reviewMenuDto, OtherImageDto otherImageDto) {
        int fk_restaurant_id = 1;
        List<ReviewDto> reviews = reviewService.getListR(fk_restaurant_id);
        m.addAttribute("reviews", reviews);

        List<ReviewMenuDto> menus = reviewMenuService.getListR(1002);

        m.addAttribute("restaurantDto", restaurantDto);
        m.addAttribute("reviewDto", reviewDto);

        int review_count = reviewService.getCountR(fk_restaurant_id);
        m.addAttribute("reviewCount", review_count);

        Double taste_score = reviewService.getTasteAvg(fk_restaurant_id);
        Double clean_score = reviewService.getCleanAvg(fk_restaurant_id);
        Double kind_score = reviewService.getKindAvg(fk_restaurant_id);
        Double total_score = reviewService.getTotalAvg(fk_restaurant_id);
        m.addAttribute("taste_score", taste_score);
        m.addAttribute("clean_score", clean_score);
        m.addAttribute("kind_score", kind_score);
        m.addAttribute("total_score", total_score);

        m.addAttribute("reviewMenuDto", reviewMenuDto);
        m.addAttribute("otherImageDto", otherImageDto);
        return "detail";
    }


    @GetMapping("/reviewWrite") // 리뷰 작성 첫 페이지 메서드 보여주기
    public String reviewWrite(HttpServletRequest request, RestaurantDto restaurantDto, Model m) {
        m.addAttribute("restaurantDto", restaurantDto);

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
    public String showReviewWrite2(
            ReviewDto reviewDto,
            @RequestParam("selected_menu[]") List<Integer> selectedMenus,
            Model model
    ){
        model.addAttribute("reviewDto", reviewDto);
        model.addAttribute("selectedMenus", selectedMenus);

        return "reviewWrite2";
    }

    private static final String F_PATH = "/Users/joohunkang/Desktop/Spring/MatMap_portfolio/src/main/webapp/resources/img";

    @PostMapping("/reviewWrite2") // 리뷰 작성 두 번째 페이지 메서드
    public String reviewWriteSubmit(HttpSession session, ReviewDto reviewDto, @RequestParam(value = "files", required = false) List<MultipartFile> files, @RequestParam("selected_menu[]") List<Integer> selectedMenus){
        int order_no = 1;
        log.info("joshua1");
        OtherImageDto otherImageDto;
        try {
            log.info(reviewDto.getContent());
//            String reviewer = (String)session.getAttribute("id");
            String reviewer = "김철수";
            int fk_restaurant_id = 1;
            reviewDto.setReviewer(reviewer);
            reviewDto.setFk_restaurant_id(fk_restaurant_id);

            int rowCount = reviewService.write(reviewDto);
            reviewDto.setId(reviewService.getAllCount());

            int reviewId = reviewService.getAllCount();

            // 2. 선택한 메뉴 저장
            for (Integer menuId : selectedMenus) {
                ReviewMenuDto reviewMenuDto = new ReviewMenuDto(reviewId, menuId);
                reviewMenuService.write(reviewMenuDto);
            }


            if(rowCount != 1) {
                throw new Exception("글쓰기 실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "글쓰기 실패";
        }
        if (files != null && files.size() > 0) {
            log.info("joshua2");
            List<OtherImageDto> imageList = new ArrayList<>();
            for (MultipartFile file : files) {
                log.info("joshua3");

                // 원본 파일 이름
                try {
                    String originalFilename = file.getOriginalFilename();

                    // 파일 0개면 안해줌
                    if (Objects.equals(originalFilename,"")){
                        return "redirect:/";
                    }

                    // 밀리초 기반 유니크 파일 이름 생성
                    String savedFilename = System.currentTimeMillis() + "_" + originalFilename;
                    String saveFile = F_PATH + System.currentTimeMillis() + "_" + originalFilename; // 저장경로 설정

                    file.transferTo(new File(saveFile));

                    // OtherImageDto에 정보 추가
                    otherImageDto = new OtherImageDto();
                    otherImageDto.setName(savedFilename);
                    otherImageDto.setImg_url(saveFile); // 실제 저장된 경로
                    otherImageDto.setOrder_number(order_no);
                    otherImageDto.setFk_review_id(reviewDto.getId());
                    otherImageDto.setFk_restaurant_id(1);
                    order_no++;
                    imageList.add(otherImageDto);
                    otherImageService.insertImage(otherImageDto);
                }catch (IOException e) {
                    e.printStackTrace();
                    // 파일 업로드 실패 시 처리
                    return "redirect:/join?msg=파일 업로드 실패";
                }

            }

        }




        return "redirect:/";




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
