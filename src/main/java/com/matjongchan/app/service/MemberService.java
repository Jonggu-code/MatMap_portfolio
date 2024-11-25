package com.matjongchan.app.service;

import com.matjongchan.app.domain.entity.MemberDto;

import java.util.List;

public interface MemberService {

    //MemberDaoImpl의 count()와 연결됨.
    int countNumber();

    //MemberDaoImpl의 deleteAll()와 연결됨.
    int removeAll();

    //MemberDaoImpl의 insert(MemberDto dto)와 연결됨.
    int write(MemberDto dto);

    //MemberDaoImpl의 selectAll()와 연결됨.
    List<MemberDto> chooseAll();

    //MemberDaoImpl의 select(String user_id)와 연결됨.
    MemberDto chooseOne(String user_id);

    //MemberDaoImpl의 delete(String user_id)와 연결됨.
    int removeOne(String user_id);

    //MemberDaoImpl의 update(MemberDto dto)와 연결됨.
    int change(MemberDto dto);
}
