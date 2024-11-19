package com.matjongchan.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@AllArgsConstructor //모든 필드를 포함하는 생성자를 추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j

public class MemberDto {
    private Integer id; // 회원번호
    private String user_id; // 회원 아이디
    private String password; //비밀번호
    private String name;    // 회원 이름
    private String address; //주소
    private String email;   // 이메일 주소
    private String introduce;   // 자기소개
    private String phone_number;    //회원 전화번호
    private String create_at;   //회원가입날짜
    private Integer review_id;

    // create_at, review_id 제외한 생성자
    public MemberDto(Integer id, String user_id, String password, String name, String address, String email, String introduce, String phone_number) {
        this.id = id;
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.introduce = introduce;
        this.phone_number = phone_number;
    }


    // toString
    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", introduce='" + introduce + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", create_at='" + create_at + '\'' +
                ", review_id=" + review_id +
                '}';
    }
}
