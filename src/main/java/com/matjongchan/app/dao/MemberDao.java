package com.matjongchan.app.dao;

import com.matjongchan.app.domain.entity.MemberDto;

import java.util.List;

public interface MemberDao {
    int count();
    int deleteAll();
    int insert(MemberDto dto);
    List<MemberDto> selectAll();
    MemberDto select(String user_id);
    int delete(String user_id);
    int update(MemberDto dto);
}
