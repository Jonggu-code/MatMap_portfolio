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
public class MemberImageDto {
    private Integer id;                 // 이미지 ID
    private String name;            // 이미지 이름
    private String img_url;          // 이미지 URL
    private int order;              // 이미지 순서

    @Override
    public String toString() {
        return "MemberImageDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img_url='" + img_url + '\'' +
                ", order=" + order +
                '}';
    }
}

