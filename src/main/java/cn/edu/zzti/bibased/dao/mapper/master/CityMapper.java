package cn.edu.zzti.bibased.dao.mapper.master;


import cn.edu.zzti.bibased.dto.City;

import java.util.List;
/**
 * 城市信息
 *
 * Created by huaidou on  2018/1/15
 */
public interface CityMapper {

    void insert(City record);

    void batchInsert(List<City> citys);

    List<City> queryCity(String include);

}