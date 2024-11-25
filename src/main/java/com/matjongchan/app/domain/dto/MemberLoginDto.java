package com.matjongchan.app.domain.dto;

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
public class MemberLoginDto {
    private String user_id;
    private String password;
    private boolean remember_id;

    @Override
    public String toString() {
        return "MemberLoginDto{" +
                "user_id='" + user_id + '\'' +
                ", password='" + password + '\'' +
                ", remember_id=" + remember_id +
                '}';
    }
}
