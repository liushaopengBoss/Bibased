package cn.edu.zzti.bibased.dao.mapper.master;

import cn.edu.zzti.bibased.dto.Company;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * 1.融资情况公司数量
     */
    List<Company> queryFinanceStageCompanNum(@Param("include")String include);
    /**
     * 1.分析各个城市行业领域公司数量 去重
     */
    List<Company> queryIndustryCompanNum(@Param("include")String include);

    /**
     *总的公司数量
     *
     * @param
     * @return
     */
    @Select("SELECT count(id) from company"  )
    Integer queryCompanyDetailsByCount();


}