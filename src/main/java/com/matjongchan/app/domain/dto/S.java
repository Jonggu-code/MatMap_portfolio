package com.matjongchan.app.domain.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class S {

    private Integer id;
    private Integer page_size;
    private Integer offset;

}
