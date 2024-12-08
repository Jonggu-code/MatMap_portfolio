package com.matjongchan.app.controller;

import com.matjongchan.app.dao.OtherImageDao;

import com.matjongchan.app.dao.RestaurantDao;
import com.matjongchan.app.dao.ReviewDao;
import com.matjongchan.app.domain.dto.ReviewDetail;
import com.matjongchan.app.domain.entity.*;
import com.matjongchan.app.service.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import java.util.*;

@Slf4j
@Controller
public class ReviewController {
    @Autowired
    ReviewMenuService reviewMenuService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OtherImageService otherImageService;
    @Autowired
    RestaurantServiceImpl restaurantService;
    @Autowired
    RestaurantDao restaurantDao;
    @Autowired
    ReviewDao reviewDao;

    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    // 파일 경로 확인해주세요
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    private static final String root_path = "C:\\Users\\power\\Desktop\\study\\profile_img\\";



//    @GetMapping("/detail")
//    public String getReviews(RestaurantDto restaurantDto, ReviewDto reviewDto, Model m, ReviewMenuDto reviewMenuDto, OtherImageDto otherImageDto) {
//        int fk_restaurant_id =1;
//        List<ReviewDto> reviews = reviewService.getListR(fk_restaurant_id);
//        m.addAttribute("reviews", reviews);
//
//        for (ReviewDto review : reviews) {
//            List<String> menuNames = reviewMenuService.getMenuNames(review.getId());
//            review.setMenuNames(menuNames); // ReviewDto에 메뉴 리스트 추가
//        }
//
//        for (ReviewDto review : reviews) {
////            List<OtherImageDto> otherImages = otherImageService.
//        }
//
//        m.addAttribute("restaurantDto", restaurantDto);
//        m.addAttribute("reviewDto", reviewDto);
//
//        int review_count = reviewService.getCountR(fk_restaurant_id);
//        m.addAttribute("reviewCount", review_count);
//
//        Double taste_score = reviewService.getTasteAvg(fk_restaurant_id);
//        Double clean_score = reviewService.getCleanAvg(fk_restaurant_id);
//        Double kind_score = reviewService.getKindAvg(fk_restaurant_id);
//        Double total_score = reviewService.getTotalAvg(fk_restaurant_id);
//        m.addAttribute("taste_score", taste_score);
//        m.addAttribute("clean_score", clean_score);
//        m.addAttribute("kind_score", kind_score);
//        m.addAttribute("total_score", total_score);
//
//        m.addAttribute("reviewMenuDto", reviewMenuDto);
//        m.addAttribute("otherImageDto", otherImageDto);
//        return "detail";
//    }


    @GetMapping("/reviewWrite/{id}") // 리뷰 작성 첫 페이지 메서드 보여주기
    public String reviewWrite(@PathVariable("id") int id, HttpServletRequest request, RestaurantDto restaurantDto, Model m) {
        HttpSession session = request.getSession();

        if(session.getAttribute("id") == null){
            return "redirect:/login";
        }

        m.addAttribute("id", id);
        m.addAttribute("restaurantDto", restaurantDto);


        RestaurantDto restaurant = restaurantService.getRestaurantById(id);
        m.addAttribute("restaurant", restaurant);
        String category = CategoryChanger.numberIntoCategory(restaurant.getFk_category());
        m.addAttribute("category", category);

        List<MenuDto> menuDtos = restaurantDao.getMenuDto(id);
        m.addAttribute("menuDtos", menuDtos);


        List<String> menus = restaurantService.getMenu_name_list(id);
        m.addAttribute("menus", menus);

        // 로그인 했는지 확인
        if(!loginChk(session)) {
            // 로그인 안 했다면..
            String toURL = "reviewWrite/{id}";
            return "redirect:/login?toUrl="+toURL;
        }
        return "reviewWrite";
    }

// 리뷰 수정시 사용
    @GetMapping("/reviewWriteUpdate/{id}") // 리뷰 작성 첫 페이지 메서드 보여주기
    public String reviewWriteUpdate(@PathVariable("id") int id, HttpServletRequest request, RestaurantDto restaurantDto, Model m) {

        HttpSession session = request.getSession();
        session.setAttribute("type","update");
        session.setAttribute("restaurant_id",id);
        return reviewWrite(id,request,restaurantDto,m);

    }
    @PostMapping("/showReviewWrite2/{id}")
    public String showReviewWrite2(
            @PathVariable("id") int id,
            ReviewDto reviewDto,
            @RequestParam("selected_menu[]") List<Integer> selectedMenus,
            Model m
    ){
        m.addAttribute("id", id);
        m.addAttribute("reviewDto", reviewDto);
        m.addAttribute("selectedMenus", selectedMenus);


        RestaurantDto restaurant = restaurantService.getRestaurantById(id);
        m.addAttribute("restaurant", restaurant);
        String category = CategoryChanger.numberIntoCategory(restaurant.getFk_category());
        m.addAttribute("category", category);

        return "reviewWrite2";
    }

    @PostMapping("/reviewWrite2/{r_id}") // 리뷰 작성 두 번째 페이지 메서드
    public String reviewWriteSubmit( @PathVariable("r_id") int id, HttpSession session, ReviewDto reviewDto, @RequestParam(value = "files", required = false) List<MultipartFile> files, @RequestParam("selected_menu[]") List<Integer> selectedMenus){

        String type = session.getAttribute("type") == null?"":(String) session.getAttribute("type");
        System.out.println("zzzzzz      reviewWriteSubmit type="+session.getAttribute("type"));

        int orderId = reviewDao.lastOne() + 1;
        int order_no = 1;
        log.info("joshua1");
        OtherImageDto otherImageDto;
        ReviewDto reviewDtoNew = null;

        try {
            log.info(reviewDto.getContent());
            String reviewer = (String)session.getAttribute("id");
            reviewDto.setReviewer(reviewer);
            reviewDto.setFk_restaurant_id(id);

            int rowCount = -1;

            ////////////// 2024-12-08 곽 - update관련 수정
            if(type.equals("update")) {
                Integer restaurant_id = (int) session.getAttribute("restaurant_id");
                Map map = new HashMap();
                map.put("fk_restaurant_id",restaurant_id);
                map.put("reviewer",reviewer);
                reviewDtoNew = reviewService.selectOnceReview(map);
                reviewDto.setId(reviewDtoNew.getId());
                rowCount = reviewService.modify(reviewDto);
            }
            else
            {
                rowCount = reviewService.write(reviewDto);
            }
//            reviewDto.setId(reviewService.getAllCount());

            int reviewId = reviewService.getAllCount();

            ////////////// 2024-12-08 곽 - update관련 수정
            if(type.equals("update")) {
                reviewMenuService.remove(reviewDtoNew.getId());
            }
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

            ////////////// 2024-12-08 곽 - update관련 수정
            if(type.equals("update")) {
                otherImageService.deleteImage(reviewDtoNew.getId());
            }

            List<OtherImageDto> imageList = new ArrayList<>();
            for (MultipartFile file : files) {

                // 원본 파일 이름
                try {
                    String originalFilename = file.getOriginalFilename();

                    // 파일 0개면 안해줌
                    if (Objects.equals(originalFilename,"")){
                        return "redirect:/";
                    }

                    // 밀리초 기반 유니크 파일 이름 생성
                    String savedFilename = System.currentTimeMillis() + "_" + originalFilename;

                    File save_root = new File(root_path , savedFilename);
                    // 파일 저장
                    file.transferTo(save_root);

                    // OtherImageDto에 정보 추가
                    otherImageDto = new OtherImageDto();
                    otherImageDto.setName(savedFilename);
                    otherImageDto.setImg_url("/image/" + savedFilename); // 실제 저장된 경로
                    otherImageDto.setFk_review_id(orderId);
                    otherImageDto.setFk_restaurant_id(id);
                    order_no++;
                    otherImageDto.setOrder_number(order_no);
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


//    @GetMapping ("/remove/{id}") // 게시글 삭제 메서드
//    public String remove(@PathVariable("id") int id, HttpSession session, Model m ) {

      @GetMapping("/remove/{id}") // 리뷰 작성 첫 페이지 메서드 보여주기
      public String remove(@PathVariable("id") int id, HttpSession session, RestaurantDto restaurantDto, Model m) {

          System.out.println("zzzzzzzzzzzzzzzzzzzz");
        try {
            // 글 쓴 사람인지 확인할때 사용할 용도 - 현재 로그인 한 사람 아이디
            String reviewer = (String)session.getAttribute("id");

            Integer restaurant_id = id;//(int) session.getAttribute("restaurant_id");
            Map map = new HashMap();
            map.put("fk_restaurant_id",restaurant_id);
            map.put("reviewer",reviewer);
            ReviewDto reviewDtoNew = reviewService.selectOnceReview(map);

            // bno번 글 읽어와서 boardDto 변수에 저장
            int rowCount = reviewService.remove(reviewDtoNew.getId(), reviewer );

            if(rowCount != 1) {
                throw new Exception("삭제 실패");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/detail/menu/{id}")
    public ResponseEntity<List<MenuDto>> getMenu(@PathVariable int id) {
        return null;
    }


    private boolean loginChk(HttpSession session) {
        return session.getAttribute("id") != null;
    }


}

