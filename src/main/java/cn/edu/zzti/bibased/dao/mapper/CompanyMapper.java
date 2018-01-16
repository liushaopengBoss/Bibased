package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.Company;

import java.util.List;

public interface CompanyMapper {

    void insert(Company company);

    void batchInsert(List<Company> companies);

    Company selectByPrimaryKey(Integer id);


}