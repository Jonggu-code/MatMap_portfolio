package com.matjongchan.app.dao;

import com.matjongchan.app.domain.MemberDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private SqlSession session;

    private String namespace = "com.matjongchan.app.dao.MemberMapper.";

    @Override
    public int count(){
        return session.selectOne(namespace + "count");
    }

    @Override
    public int deleteAll(){
        System.out.println("여기ddd");
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int insert(MemberDto dto){
        return session.insert(namespace + "insert", dto);
    }

    @Override
    public List<MemberDto> selectAll(){
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public MemberDto select(String user_id){
        return session.selectOne(namespace + "select", user_id);
    }

    @Override
    public int delete(String user_id){
        return session.delete(namespace + "delete", user_id);
    }

    @Override
    public int update(MemberDto dto){
        return session.update(namespace + "updateMember", dto);
    }

}
