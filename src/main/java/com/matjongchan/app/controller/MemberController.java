package com.matjongchan.app.controller;



/* 1. 로그인 컨트롤러
 *  2. 회원가입 컨트롤러
 * */


import com.matjongchan.app.domain.entity.MemberDto;
import com.matjongchan.app.domain.dto.MemberLoginDto;
import com.matjongchan.app.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

/*
1. 로그인 컨트롤러 - get, post 방식
 */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/main";
    }
    @GetMapping("/login")
    public String loginForm(){
        System.out.println("sadfasfadfsd");
        return "loginForm"; //loginForm jsp 파일로 연결
    }

    @PostMapping("/login")
    public String login(MemberLoginDto memberlogin, String toUrl, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String user_id = memberlogin.getUser_id();
        String user_pw = memberlogin.getPassword();
        boolean r_id = memberlogin.isRemember_id();

        // 유효성 검사
        if(!loginChk(user_id, user_pw)){
            String msg = URLEncoder.encode("일치하는 회원 정보가 없습니다.", "UTF-8");
            return "redirect:/login?msg=" + msg;
        }
        // 세션 생성
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("id", user_id);

        // 쿠키 생성
        Cookie cookie = new Cookie("id", user_id);
        // 하루동안 쿠키 유지
        cookie.setMaxAge(r_id?60 * 60 * 24:0);
        response.addCookie(cookie);

        toUrl = toUrl == ""?"":toUrl;

        return "redirect:/main" + toUrl;

    }

    private boolean loginChk(String user_id, String user_pw) {
        // 입력 받은 id, pw와 매칭되는 회원이 있는지, DB의 member 테이블에서 select 해오기
        // 정보가 있으면, 회원이기에 true
        // 정보가 없으면, 비회원이기에 false

        // 아이디 확인
        MemberDto member = memberService.getMember(user_id);
        if(member == null) return false;
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
    public String registerForm(){
        return "registerForm";
    }

    @PostMapping("/join")
    public String register(MemberDto memberDto, Model m, @RequestParam(value = "profile_image", required = false) MultipartFile mf){
        log.info("11111111");
        String user_id = memberDto.getUser_id();
        String password = memberDto.getPassword();
        String email = memberDto.getEmail();
        String phone_number = memberDto.getPhone_number();
        String gender = memberDto.getGender();
    log.info(memberDto.toString());
        // 유효성 검사
        /*
        유효성 검사 조건 (introduce 뺴고 not null)
        1. userId: 특수문자 불가
        2. email: @ 포함인지 확인
        3. phoneNumber: 000-0000-0000 형식
        4. gender: 남/ 여

        */
//        if(user_id == null || user_id.isEmpty() || !user_id.matches("[a-zA-Z0-9]+")) {
//            String msg = URLEncoder.encode("아이디는 필수 입력 항목이고, 특수문자를 포함할 수 없습니다.", "UTF-8");
//            return "redirect:/join?msg=" + msg;
//        }
//
//
//        if(password == null || password.isEmpty()){
//            String msg = URLEncoder.encode("비밀번호는 필수 입력 항목입니다.", "UTF-8");
//            return "redirect:/join?msg=" +msg;
//        }
//        if(email == null || email.isEmpty() || !email.contains("@")){
//            String msg = URLEncoder.encode("유효하지 않은 이메일 형식입니다.", "UTF-8");
//            return "redirect:/join?msg=" +msg;
//        }
//        if(phone_number == null || phone_number.isEmpty() || !phone_number.matches("\\d{3}-\\d{4}-\\d{4}")){
//            String msg = URLEncoder.encode("전화번호는 000-0000-0000 형식이어야 합니다.", "UTF-8");
//            return "redirect:/join?msg=" +msg;
//        }
//
//        if(gender == null || gender.isEmpty() || (!gender.equals("M") && !gender.equals("F"))){
//            String msg = URLEncoder.encode("성별은 'M' 또는 'F'만 가능합니다.", "UTF-8");
//            return "redirect:/join?msg=" + msg;
//        }



        // 아이디 중복 검사 통과한 경우,
        if(isValid(user_id)){
            // DB에 저장
            if(memberService.addMember(memberDto) == 1){
                return "redirect:/main";
            }
            else{
                String msg = null;
                try {
                    msg = URLEncoder.encode("문제가 발생했습니다. 잠시 후에 다시 시도하세요.", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return "redirect:/join?msg" + msg;
            }
        }
        // 아이디 중복 검사 통과 못한 경우
        else{
            String msg = null;
            try {
                msg = URLEncoder.encode("사용 중인 아이디입니다.", "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // 사용자가 적어둔 정보 유지, 뷰 파일에서 아래 이름들(u_seqno, ...)로 value 값 넣어놓아야함.
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
    }


    private boolean isValid(String id){
        MemberDto member = memberService.getMember(id);

        if(member != null) return false;
        return true;
    }
}
