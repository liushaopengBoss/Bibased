package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.PositionDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 职位详细信息mapper
 *
 * Created by huaidou on  2018/1/15
 */
public interface PositionDetailMapper {

    void insert(PositionDetail positionDetail);

    void batchInsert(List<PositionDetail> positionDetails);

    PositionDetail selectByPrimaryKey(Integer id);

    Long selectLastPostionCreateTime(@Param("include") String include, @Param("city") String city, @Param("thirdType") String thirdType);

    @Select(" SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 0 and work_max_year = 0 and include = #{include} UNION\n" +
            " SELECT work_min_year as  workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 1 and work_max_year = 3 and include = #{include} UNION\n" +
            "SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 3 and work_max_year = 5 and include = #{include} UNION\n" +
            "SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 5 and work_max_year = 10 and include = #{include}  UNION\n" +
            "SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where  work_min_year = 10 and work_max_year = 10 and include = #{include}")
    List<PositionDetail> queryWorkYearNums(@Param("include")String include);

    @Select("SELECT education ,count(id) as num\n" +
            " from position_detail where include = #{include} GROUP BY education")
    List<PositionDetail> queryEducationNums(@Param("include")String include);

    @Select("SELECT job_nature as jobNature ,count(id) as num\n" +
            " from position_detail where include = #{include} GROUP BY job_nature")
    List<PositionDetail> queryJobNatureNums(@Param("include")String include);

    @Select("SELECT third_type as thirdType ,count(id) as num from position_detail where include = #{include} and first_type =#{firstType}" +
            " GROUP BY third_type ORDER BY num desc limit 60")
    List<PositionDetail> queryPositionDetailsByFirstTye(@Param("include")String include,@Param("firstType")String firstType);
  @Select("SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where company_min_size = 0 and company_max_size = 0 and include = #{include} UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where company_min_size >= 10 and company_max_size <= 50 and include = #{include} UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where company_min_size = 50 and company_max_size = 150 and include = #{include} UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where company_min_size = 150 and company_max_size = 500 and include = #{include} UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where company_min_size = 500 and company_max_size = 2000 and include = #{include} UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where company_min_size = 2000 and company_max_size = 2000 and include = #{include} \n")
   List<PositionDetail> queryCompanySize(@Param("include")String include );

}