package com.matjongchan.app.dao;

import com.matjongchan.app.domain.RestaurantDto;
import com.matjongchan.app.domain.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    private final SqlSession session;

    private static String namespace = "com.matjongchan.app.dao.RestaurantMapper.";

    @Override
    public RestaurantDto getRestaurantById(int id) {
        return null;
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        return null;
    }

    @Override
    public List<RestaurantDto> searchRestaurantsBySearchCondition(SearchCondition searchCondition) {
        return List.of();
    }
}
