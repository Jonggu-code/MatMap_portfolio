package com.matjongchan.app.service;

import com.matjongchan.app.dao.MemberDao;
import com.matjongchan.app.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberDao memberdao;

    //MemberDaoImpl의 count()와 연결됨.
    @Override
    public int countNumber(){
        return memberdao.count();
    }

    //MemberDaoImpl의 deleteAll()와 연결됨.
    @Override
    public int removeAll(){
        return memberdao.deleteAll();
    }

    //MemberDaoImpl의 insert(MemberDto dto)와 연결됨.
    @Override
    public int write(MemberDto dto){
        return memberdao.insert(dto);
    }

    //MemberDaoImpl의 selectAll()와 연결됨.
    @Override
    public List<MemberDto> chooseAll(){
        return memberdao.selectAll();
    }

    //MemberDaoImpl의 select(String user_id)와 연결됨.
    @Override
    public MemberDto chooseOne(String user_id){
        return memberdao.select(user_id);
    }

    //MemberDaoImpl의 delete(String user_id)와 연결됨.
    @Override
    public int removeOne(String user_id){
        return memberdao.delete(user_id);
    }

    //MemberDaoImpl의 update(MemberDto dto)와 연결됨.
    @Override
    public int change(MemberDto dto){
        return memberdao.update(dto);
    }
}
