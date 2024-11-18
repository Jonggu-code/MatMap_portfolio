package com.matjongchan.app.domain;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter //모든 필드에 getter 메서드추가.
@Setter //모든 필드에 setter 메서드추가.
@AllArgsConstructor //모든 필드를 포함하는 생성자를 추가.
@NoArgsConstructor // 매개변수가 없는 기본 생성자를 추가.
@Slf4j  //log 편하게 사용
public class lombok예시 {
    private String name;
    private int age;

    public static void main(String[] args) {
        log.info("이렇게 쓰면 이거 실행할떄 로그가나옴!");
        //@Slf4j 어노테이션을 클래스단에 넣어줘야함.
    }
}
