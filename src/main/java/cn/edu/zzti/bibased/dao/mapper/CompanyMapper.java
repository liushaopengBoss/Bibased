package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {

    void insert(Company company);

    void batchInsert(List<Company> companies);

    Company selectByPrimaryKey(Integer id);

    /**
     * 1.分析各个城市的公司数量 去重
     */
    List<Company> queryCityCompanNum(@Param("include") String include);
    /**
     * 1.各个城市融资情况公司数量
     */
    List<Company> queryFinanceStageCompanNum(@Param("include")String include);
    /**
     * 1.分析各个城市行业领域公司数量 去重
     */
    List<Company> queryIndustryCompanNum(@Param("include")String include);
}