package cn.edu.zzti.bibased.dao.mapper;


import cn.edu.zzti.bibased.dto.City;

import java.util.List;

public interface CityMapper {

    void insert(City record);

    void batchInsert(List<City> citys);

    List<City> queryCity(String include);

}