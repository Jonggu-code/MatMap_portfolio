package com.matjongchan.app.dao;

import com.matjongchan.app.domain.MemberDto;

import java.util.List;

public interface MemberDao {
    int count();
    int deleteAll();
    int insert(MemberDto dto);
    List<MemberDto> selectAll();
    MemberDto select(Integer id);
    int delete(Integer id);
    int update(MemberDto dto);
}
