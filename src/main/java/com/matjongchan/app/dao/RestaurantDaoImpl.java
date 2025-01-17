package com.matjongchan.app.dao;

import com.matjongchan.app.domain.dto.MenuDetail;
import com.matjongchan.app.domain.dto.TotalCount;
import com.matjongchan.app.domain.entity.BusinessHoursDto;
import com.matjongchan.app.domain.entity.FavoriteDto;
import com.matjongchan.app.domain.entity.MenuDto;
import com.matjongchan.app.domain.entity.RestaurantDto;
import com.matjongchan.app.domain.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    private final SqlSession session;

    private final String namespace = "com.matjongchan.app.dao.restaurantMapper.";

    @Override
    public int getRestaurantCount() {
        return session.selectOne(namespace + "count");
    }

    @Override
    public RestaurantDto getRestaurantById(int id) {
        return session.selectOne(namespace + "selectById", id);
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        return session.selectOne(namespace + "selectByName", name);
    }

    @Override
    public List<RestaurantDto> totalSearch(SearchCondition searchCondition) {
        return session.selectList(namespace + "totalSearch", searchCondition);
    }

    @Override
    public List<RestaurantDto> nearSearch(SearchCondition searchCondition) {
        return session.selectList(namespace + "nearSearch", searchCondition);
    }

    @Override
    public List<RestaurantDto> realTotalSearch(SearchCondition searchCondition) {
        return session.selectList(namespace + "realTotalSearch", searchCondition);
    }

    @Override
    public int truncateAll() {
        return session.delete(namespace + "truncateAll");
    }

    @Override
    public int insertRestaurant(RestaurantDto restaurantDto) {
        return session.insert(namespace + "insertRestaurant", restaurantDto);
    }

    @Override
    public int updateRestaurant(RestaurantDto restaurantDto) {
        return session.update(namespace + "updateRestaurant", restaurantDto);
    }

    @Override
    public int deleteRestaurant(int id) {
        return session.delete(namespace + "deleteRestaurant", id);
    }

    @Override
    public TotalCount getTotalCount(TotalCount totalCount) {
        return session.selectOne(namespace + "getTotalCount", totalCount);
    }

    @Override
    public int updateTotalCount(TotalCount totalCount) {
        return session.update(namespace + "updateTotalCount", totalCount);
    }

    @Override
    public List<RestaurantDto> getRestaurantAll() {
        return session.selectList(namespace + "getRestaurantAll");
    }

    @Override
    public BusinessHoursDto getBusinessHours(int restaurant_id) {
        return session.selectOne(namespace + "getBusinessHours", restaurant_id);
    }

    @Override
    public List<MenuDetail> getMenuDetail(int restaurant_id) {
        return session.selectList(namespace+"getRestaurantMenuDetail",restaurant_id);
    }
    @Override
    public List<MenuDto> getMenuDto(int restaurant_id) {
        return session.selectList(namespace+"getRestaurantMenuDto",restaurant_id);
    }


    @Override
    public String getMenuUrl(int image_id) {
        return session.selectOne(namespace+"getRestaurantMenuImage",image_id);
    }

    @Override
    public List<RestaurantDto> getRelationRestaurant3(SearchCondition searchCondition) {
        return session.selectList(namespace+"getRelationRestaurant",searchCondition);
    }

    @Override
    public List<RestaurantDto> getPopularRestaurant(SearchCondition searchCondition) {
        return session.selectList(namespace + "getPopularRestaurant", searchCondition);
    }

    @Override
    public List<RestaurantDto> getAllConsiderRestaurant(SearchCondition searchCondition) {
        return session.selectList(namespace + "allConsiderSearch", searchCondition);
    }

    @Override
    public int allConsiderSearchGetTotalCount(SearchCondition searchCondition) {
        return session.selectOne(namespace + "allConsiderSearchGetTotalCount", searchCondition);
    }

    @Override
    public Integer getIdByName(String restaurantName){
        return session.selectOne(namespace + "getIdByName",restaurantName);
    };

    @Override
    public int saveFavoriteRestaurant(FavoriteDto favoriteDto) {
        return session.insert(namespace + "saveFavoriteRestaurant", favoriteDto);
    }

    @Override
    public int deleteFavoriteRestaurant(FavoriteDto favoriteDto) {
        return session.delete(namespace + "deleteFavoriteRestaurant", favoriteDto);
    }
}



