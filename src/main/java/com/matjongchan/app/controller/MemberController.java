package com.matjongchan.app.controller;



/* 1. 로그인 컨트롤러
 *  2. 회원가입 컨트롤러
 * */


import com.matjongchan.app.domain.dto.FavoriteWithRestaurantDto;
import com.matjongchan.app.domain.dto.RestaurantDetail;
import com.matjongchan.app.domain.entity.MemberDto;
import com.matjongchan.app.domain.dto.MemberLoginDto;
import com.matjongchan.app.domain.entity.MemberImageDto;
import com.matjongchan.app.domain.entity.RestaurantDto;
import com.matjongchan.app.domain.entity.ReviewDto;
import com.matjongchan.app.service.MemberService;
import com.matjongchan.app.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    RestaurantService restaurantService;

//    private final String root_path = "C:\\Users\\power\\Desktop\\study\\MatMap_portfolio\\src\\main\\webapp\\resources\\img\\profile_img";
    private final String root_path = "C:\\Users\\power\\Desktop\\study\\profile_img\\";
    private boolean isValid(String id) {
        MemberDto member = memberService.getMember(id);

        if (member != null) return false;
        return true;
    }

    /*
    1. 로그인 컨트롤러 - get, post 방식
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm"; //loginForm jsp 파일로 연결
    }

    @PostMapping("/login")
    public String login(MemberLoginDto memberlogin, String toUrl, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String user_id = memberlogin.getUser_id();
        String user_pw = memberlogin.getUser_pw();
        boolean r_id = memberlogin.isRemember_id();

        // 유효성 검사
        if (!loginChk(user_id, user_pw)) {
            String msg = URLEncoder.encode("일치하는 회원 정보가 없습니다.", "UTF-8");
            return "redirect:/login?msg=" + msg;
        }
        // 세션 생성
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("id", user_id);

        MemberDto member = memberService.getMember(user_id);
        String img_url = memberService.getMemberImage(member.getFk_image_id()).getImg_url();
        httpSession.setAttribute("img", img_url);

        // 쿠키 생성
        Cookie cookie = new Cookie("id", user_id);
        // 하루동안 쿠키 유지
        cookie.setMaxAge(r_id ? 60 * 60 * 24 : 0);
        response.addCookie(cookie);

        //다른 페이지 가려다가 로그인 안돼서 로그인창으로 넘어온 경우, 원래 창으로 돌려보내줌.
        toUrl = toUrl.equals("") ? "" : toUrl;

        return "redirect:/" + toUrl;

    }

    private boolean loginChk(String user_id, String user_pw) {
        // 입력 받은 id, pw와 매칭되는 회원이 있는지, DB의 member 테이블에서 select 해오기
        // 정보가 있으면, 회원이기에 true
        // 정보가 없으면, 비회원이기에 false

        // 아이디 확인
        MemberDto member = memberService.getMember(user_id);
        if (member == null) return false;
        // 비밀번호 확인
        return member.getPassword().equals(user_pw);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    2. 회원가입 컨트롤러 - get, post 방식
     */
    @GetMapping("/join")
    public String registerForm() {
        return "registerForm";
    }

//    private static final String F_PATH = "C:/Users/82109/Desktop/spring/matjongchan_git/MatMap_portfolio/src/main/webapp/resources/img/profile_img/";
    private static final int defaultImageId = 1; // 기본 이미지의 id값 1

@PostMapping("/join")
public String register(MemberDto memberDto, Model m, @RequestParam(value = "profile_image", required = false) MultipartFile mf) {
    String user_id = memberDto.getUser_id().trim();

    // 아이디 중복 검사
    if (!isValid(user_id)) {
        String msg = null;
        try {
            msg = URLEncoder.encode("사용 중인 아이디입니다.", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        m.addAttribute("u_seqno", memberDto.getId());
        m.addAttribute("u_id", memberDto.getUser_id());
        m.addAttribute("u_pw", memberDto.getPassword());
        m.addAttribute("u_name", memberDto.getName());
        m.addAttribute("u_address", memberDto.getAddress());
        m.addAttribute("u_email", memberDto.getEmail());
        m.addAttribute("u_introduce", memberDto.getIntroduce());
        m.addAttribute("u_gender", memberDto.getGender());
        m.addAttribute("u_age", memberDto.getAge());
        m.addAttribute("u_pnumer", memberDto.getPhone_number());

        return "redirect:/join?msg=" + msg;
    }

    // 이미지 업로드 처리 (mf가 비어있지 않은 경우에만 실행)
    if (mf != null && !mf.isEmpty()) {
        try {
            String originalName = mf.getOriginalFilename();
            String uniqueFileName = System.currentTimeMillis() + "_" + originalName;

            // 상대 경로로 변경 (resources/static/img/)
            File saveFile = new File(root_path , uniqueFileName);
            // 파일 저장
            mf.transferTo(saveFile);

            // 새로운 이미지 정보를 MemberImageDto에 설정
            MemberImageDto memberImageDto = new MemberImageDto();
            memberImageDto.setName(uniqueFileName);
            memberImageDto.setImg_url("/image/" + uniqueFileName);  // 웹 경로로 설정
            memberImageDto.setOrder_number(1);

            // DB에 행 삽입 후 id 가져오기
            int newImageId = memberService.addMemberImage(memberImageDto);

            Integer memberImageId = memberService.selectRecentImageOne(uniqueFileName).getId();
            // member_image table의 id를 memberDto에 설정
            memberDto.setFk_image_id(memberImageId);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/join?msg=파일 업로드 실패";
        }
    } else {
        // 사용자가 사진을 첨부하지 않으면 기본 이미지 id 설정
        if(memberDto.getGender().equals("F")) {
            memberDto.setFk_image_id(1);
        }else{
            memberDto.setFk_image_id(2);
        }
    }

    // DB에 저장
    if (memberService.addMember(memberDto) == 1) {
        return "redirect:/";
    } else {
        String msg = null;
        try {
            msg = URLEncoder.encode("문제가 발생했습니다. 잠시 후에 다시 시도하세요.", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/join?msg=" + msg;
    }
}

    @GetMapping("/myPageRestaurant")
    public String myPageRestaurant(HttpSession session, Model model) {

        // 1. 세션에 있는 id 값 가져오기
        String userId = (String) session.getAttribute("id");

        // 로그인하지 않은 사용자는 로그인페이지로 리다이렉트
        if (userId == null) {
            return "redirect:/login";
        }

        // 2. userId 이용해서 List<FavoriteWithRestaurantDto> favorites 생성
        List<FavoriteWithRestaurantDto> favorites = memberService.getMemberFavorites(userId);

        // 3. 리스트인 favorites의 각 요소들의 'fk_restaurant_id' 구하기
        List<Integer> restaurantIds = new ArrayList<>();
        for (FavoriteWithRestaurantDto favorite : favorites) {
            restaurantIds.add(favorite.getFk_restaurant_id());
        }

        // 4. fk_restaurant_id를 이용해서 restaurant 테이블의 id 구하기
        List<RestaurantDetail> restaurantDetails = new ArrayList<>();
        List<String> restaurantImageUrls = new ArrayList<>(); // 이미지 URL 리스트

        for (Integer restaurantId : restaurantIds) {
            // 5. 구한 restaurant 테이블의 id로 RestaurantDetail getRestaurantDetail(int restaurantId) 서비스 접근
            RestaurantDetail restaurantDetail = restaurantService.getRestaurantDetail(restaurantId);
            if (restaurantDetail != null) {
                restaurantDetails.add(restaurantDetail); // 상세 정보 리스트에 추가

                // RestaurantDto를 생성하여 이미지 URL 구하기
                RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
                if (restaurantDto != null) {
                    String imgUrl = restaurantService.getImgUrl(restaurantDto);
                    restaurantImageUrls.add(imgUrl); // 이미지 URL 추가
                }

            }
        }


        // 6. 모델에 보내기
        model.addAttribute("restaurantDetails", restaurantDetails);
        model.addAttribute("restaurantImageUrls", restaurantImageUrls); // 이미지 URL 데이터

        return "myPageRestaurant";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    3. 마이페이지 - '작성한 리뷰' 목록
     */
    @GetMapping("/myPageReview")
    public String myPageReview(HttpSession session, Model model){
        // 1. 세션에 있는 id 값 가져오기
        String userId = (String) session.getAttribute("id");

        // 로그인하지 않은 사용자는 로그인페이지로 리다이렉트
        if (userId == null) {
            return "redirect:/login";
        }

        // 2. 회원이 작성한 리뷰 개수 조회
        int reviewCount = memberService.selectMemberReviewCount(userId);
        model.addAttribute("reviewCount", reviewCount);


        // 3. 회원 리뷰 조회(reviewer, taste_score, clean_score, kind_score, total_score, create_at, title, content)
        List<ReviewDto> reviews = memberService.getMemberReviews(userId);
        model.addAttribute("reviews", reviews);

        // 4. 리뷰와 매칭되는 레스토랑 정보 조회 및 저장
        Map<Integer, RestaurantDetail> restaurantMap = new HashMap<>();
        for(ReviewDto review: reviews){
            int restaurantId = review.getfk_restaurant_id();
            RestaurantDetail restaurantDetail = restaurantService.getRestaurantDetail(restaurantId);
            if(restaurantDetail != null){
                restaurantMap.put(restaurantId, restaurantDetail);
            }
        }
        model.addAttribute("restaurantMap", restaurantMap);


        return "myPageReview";
    }

        /*
    4. 마이페이지 - '프로필 수정하기'
     */
    @GetMapping("/changeMemInfo")
    public String changeMemInfo(HttpSession session, Model model){
        // 1. 세션에 있는 id 값 가져오기
        String userId = (String) session.getAttribute("id");

        // 로그인하지 않은 사용자는 로그인페이지로 리다이렉트
        if (userId == null) {
            return "redirect:/login";
        }

        // 2. 로그인된 사용자의 마이페이지 정보 처리
        MemberDto member = memberService.getMember(userId);
        model.addAttribute("member", member);

        return "changeMemInfo";
    }

//    4 - 1) 프로필 수정 사항 업데이트하기
    @PostMapping("/changeMemInfo")
    public String changeMemInfo(MemberDto memberDto, @RequestParam(value = "profile_image", required = false) MultipartFile mf,HttpSession session) {
        String userId = (String) session.getAttribute("id");
        MemberDto member = memberService.getMember(userId);
        if(userId == null){
            return "redirect:/login";
        }
        // 이미지 업로드 처리 (mf가 비어있지 않은 경우에만 실행 + 기본이미지일경우를 제외함.)
        if (mf != null && !mf.isEmpty()) {
            try {
                String originalName = mf.getOriginalFilename();
                String uniqueFileName = System.currentTimeMillis() + "_" + originalName;

                // 상대 경로로 변경 (resources/static/img/)
                File saveFile = new File(root_path , uniqueFileName);
                // 저장경로 설정
                // 파일 저장
                mf.transferTo(saveFile);
                // 새로운 이미지 정보를 MemberImageDto에 설정
                MemberImageDto memberWithImage = memberService.getMemberWithImage(member.getUser_id());

                if(memberWithImage.getId() != 1 && memberWithImage.getId() != 2){
                    //기존 이미지 삭제.
                    MemberImageDto memberImage = memberService.getMemberImage(member.getFk_image_id());
                    Path deleteFilePath = Paths.get(root_path,memberImage.getImg_url().split("image/")[1]);

                    if(Files.exists(deleteFilePath)){
                        try{
                            Files.delete(deleteFilePath);
                            log.info("기존 프로필 이미지 삭제완료");
                        }catch (IOException e){
                            log.info("기존 프로필 이미지 삭제실패");
                        }
                    }else{
                        log.info("기존 프로필 이미지 경로가 존재하지않음???");
                    }
                }

                MemberImageDto memberImageDto = new MemberImageDto();
                memberImageDto.setName(memberWithImage.getName());
                memberImageDto.setImg_url("/image/" + uniqueFileName);  // 웹 경로로 설정
                memberImageDto.setOrder_number(1);
                memberImageDto.setId(memberWithImage.getId());

                int i = memberService.updateMemberImage(memberImageDto);
                memberDto.setFk_image_id(memberWithImage.getId());

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/join?msg=파일 업로드 실패";
            }
        }

        // DB에 저장
        if (memberService.updateMember(memberDto) == 1) {
            MemberImageDto memberImage = memberService.getMemberImage(member.getFk_image_id());
            session.setAttribute("img",memberImage.getImg_url());
            return "redirect:/mypage2";
        } else {
            String msg = null;
            try {
                msg = URLEncoder.encode("문제가 발생했습니다. 잠시 후에 다시 시도하세요.", "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "redirect:/changeMemInfo?msg=" + msg;
        }
    }

    /*
    3. 마이페이지 컨트롤러 - get 방식
     */
    @GetMapping("/mypage2")
    public String myPage2(HttpSession session, Model model){
        // 세션에 있는 id 값 가져오기
        String userId = (String) session.getAttribute("id");
        // 로그인하지 않은 사용자는 로그인페이지로 리다이렉트
        if(userId == null){
            return "redirect:/login";
        }
        // 로그인된 사용자의 마이페이지 정보 처리
        MemberDto member = memberService.getMember(userId);
        model.addAttribute("member", member);

        // 회원이 작성한 리뷰 개수 조회
        int reviewCount = memberService.selectMemberReviewCount(userId);
        model.addAttribute("reviewCount", reviewCount);

        // 회원 리뷰 조회(제목, 내용, 레스토랑 이름)
        List<ReviewDto> reviews = memberService.getMemberReviews(userId);
        model.addAttribute("reviews", reviews);

        // 회원의 즐겨찾기 레스토랑 정보 조회(이름, c_address, d_address, number, reservation, total_score_count, search_tag)
        List<FavoriteWithRestaurantDto> favorites = memberService.getMemberFavorites(userId);
        model.addAttribute("favorites", favorites);

        // 회원 이미지 조회
        MemberImageDto memberImage = memberService.getMemberImage(member.getFk_image_id());
        model.addAttribute("memberImage", memberImage);
//        log.info(memberImage.getImg_url());

//        ////////////////////////////////////////////////////
        // 2. userId 이용해서 List<FavoriteWithRestaurantDto> favorites 생성

        // 3. 리스트인 favorites의 각 요소들의 'fk_restaurant_id' 구하기
        List<Integer> restaurantIds = new ArrayList<>();
        for (FavoriteWithRestaurantDto favorite : favorites) {
            restaurantIds.add(favorite.getFk_restaurant_id());
        }

        // 4. fk_restaurant_id를 이용해서 restaurant 테이블의 id 구하기
        List<RestaurantDetail> restaurantDetails = new ArrayList<>();
        List<String> restaurantImageUrls = new ArrayList<>();


        for (Integer restaurantId : restaurantIds) {
            // 5. 구한 restaurant 테이블의 id로 RestaurantDetail getRestaurantDetail(int restaurantId) 서비스 접근
            RestaurantDetail restaurantDetail = restaurantService.getRestaurantDetail(restaurantId);
            if (restaurantDetail != null) {
                restaurantDetails.add(restaurantDetail); // 상세 정보 리스트에 추가

                // 이미지 URL 추가
                RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
                if (restaurantDto != null) {
                    String imgUrl = restaurantService.getImgUrl(restaurantDto);
                    restaurantImageUrls.add(imgUrl); // 이미지 URL 리스트에 추가
                }



            }
        }

        // 6. 모델에 보내기
        model.addAttribute("restaurantDetails", restaurantDetails);
        model.addAttribute("restaurantImageUrls", restaurantImageUrls);

        /////////////////////////////////////


        // 4. 리뷰와 매칭되는 레스토랑 정보 조회 및 저장
        Map<Integer, RestaurantDetail> restaurantMap = new HashMap<>();
        for(ReviewDto review: reviews){
            int restaurantId = review.getfk_restaurant_id();
            RestaurantDetail restaurantDetail = restaurantService.getRestaurantDetail(restaurantId);
            if(restaurantDetail != null){
                restaurantMap.put(restaurantId, restaurantDetail);
            }
        }
        model.addAttribute("restaurantMap", restaurantMap);


        return "myPage2";

    }


}


