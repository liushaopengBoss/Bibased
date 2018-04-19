package cn.edu.zzti.bibased.dao.mapper;

import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.query.PositionDetailQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 职位详细信息mapper
 *
 * Created by huaidou on  2018/1/15
 */
public interface PositionDetailMapper {
    /**
     *单个写入数据
     *
     * @return
     */
    void insert(PositionDetail positionDetail);
    /**
     *批量写入数据
     *
     * @return
     */
    void batchInsert(List<PositionDetail> positionDetails);

    PositionDetail selectByPrimaryKey(Integer id);
    /**
     *获取不同职位最后信息采集的时间
     *
     * @param include
     * @return
     */
    Long selectLastPostionCreateTime(@Param("include") String include, @Param("city") String city, @Param("thirdType") String thirdType);

    /**
     *不同工龄的职位数量
     *
     * @param include
     * @return
     */
    @Select(" SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 0 and work_max_year = 0 and include = #{include} UNION\n" +
            " SELECT work_min_year as  workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 1 and work_max_year = 3 and include = #{include} UNION\n" +
            "SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 3 and work_max_year = 5 and include = #{include} UNION\n" +
            "SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where work_min_year = 5 and work_max_year = 10 and include = #{include}  UNION\n" +
            "SELECT work_min_year as workMinYear,work_max_year as workMaxYear,count(id) as num from position_detail where  work_min_year = 10 and work_max_year = 10 and include = #{include}")
    List<PositionDetail> queryWorkYearNums(@Param("include")String include);
    /**
     *不同学历类型下的职位数量
     *
     * @param include
     * @return
     */
    @Select("SELECT education ,count(id) as num\n" +
            " from position_detail where include = #{include} GROUP BY education")
    List<PositionDetail> queryEducationNums(@Param("include")String include);
    /**
     *不同职位类型下的职位数量
     * eg：全职->
     *
     * @param include
     * @return
     */
    @Select("SELECT job_nature as jobNature ,count(id) as num\n" +
            " from position_detail where include = #{include} GROUP BY job_nature")
    List<PositionDetail> queryJobNatureNums(@Param("include")String include);
    /**
     *不同职能类型下的职位数量
     * eg:技术-->
     *
     * @param include
     * @return
     */
    @Select("SELECT third_type as thirdType ,count(id) as num from position_detail where include = #{include} and first_type =#{firstType}" +
            " GROUP BY third_type ORDER BY num desc limit 60")
    List<PositionDetail> queryPositionDetailsByFirstTye(@Param("include")String include,@Param("firstType")String firstType);

    /**
     * 不同公司规模的职位数量
     *
     * @param include
     * @return
     */
  @Select("SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where  include = #{include} and  company_min_size = 0 and company_max_size = 0  UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where  include = #{include} and  company_min_size >= 10 and company_max_size <= 50  UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where  include = #{include} and company_min_size = 50 and company_max_size = 150  UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where  include = #{include} and company_min_size = 150 and company_max_size = 500  UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where  include = #{include} and company_min_size = 500 and company_max_size = 2000  UNION\n" +
          "SELECT company_min_size as companyMinSize ,company_max_size as companyMaxSize ,COUNT(id) as num from position_detail where  include = #{include} and company_min_size = 2000 and company_max_size = 2000  \n")
   List<PositionDetail>   queryCompanySize(@Param("include")String include );

    /**
     * 获取昨日信息采集的数量
     *
     * @param include
     * @return
     */
  @Select("SELECT count(*) from position_detail where include =  #{include} cr_time>UNIX_TIMESTAMP(date_sub(curdate(),interval 1 day)) AND\n" +
          "   cr_time < UNIX_TIMESTAMP(DATE_SUB(curdate(),INTERVAL 0 DAY))")
    int queryBeforeDateGetInfoNums(@Param("include")String include);

    /**
     * 获取每个招聘网站在各个城市的职位数量
     * @return
     */
  @Select("SELECT include,city,count(id) as num from position_detail GROUP BY include,city")
  List<PositionDetail> queryWebCityNums();

    /**
     * 不同行业的职位数量
     * @return
     */
  @Select("SELECT industry_field as industryField ,count(id) as num  from position_detail where include = #{include}  and industry_field IS not null  GROUP BY industry_field")
  List<PositionDetail> queryIndustryFieldNums(@Param("include")String include);

    /**
     * 不同融资下的职位数量
     * @param include
     * @return
     */
  @Select("SELECT finance_stage as finance_stage ,count(id) as num  from position_detail where include = #{include}  and finance_stage IS not null  GROUP BY finance_stage" +
          "ORDER BY num DESC")
  List<PositionDetail> queryFinanceStage(@Param("include")String include);
    /**
     * 通用查询
     * @param query
     * @return
     */
//    @Select("<script>" +
//            "    SELECT * from position_detail where 1=1\n" +
//            "    <if test=\"workMinYear != null \" >\n" +
//            "      and work_min_year = #{workMinYear,jdbcType=INTEGER}  and  work_max_year = #{workMaxYear,jdbcType=INTEGER}\n" +
//            "    </if>\n" +
//            "\n" +
//            "    <if test=\"minSalary != null\" >\n" +
//            "      and min_salary = #{minSalary,jdbcType=DECIMAL}  and max_salary = #{maxSalary,jdbcType=DECIMAL}\n" +
//            "    </if>\n" +
//            "    <if test=\"include != null\">\n" +
//            "      and include = #{include,jdbcType=VARCHAR}\n" +
//            "    </if>\n" +
//            "    <if test=\"companyMinSize != null\" >\n" +
//            "      and company_min_size = #{companyMinSize,jdbcType=INTEGER}  and company_max_size = #{companyMaxSize,jdbcType=INTEGER}\n" +
//            "    </if>\n" +
//            "    <if test=\"financeStage != null\" >\n" +
//            "      and  finance_stage = #{financeStage,jdbcType=VARCHAR}\n" +
//            "    </if>\n" +
//            "    <if test=\"jobNature != null\" >\n" +
//            "      and job_nature = #{jobNature,jdbcType=VARCHAR}\n" +
//            "    </if>\n" +
//            "     limit 10" +
//            "  </script>")
    List<PositionDetail> queryPositionDetailWithBaseQuery(PositionDetailQuery query);



}