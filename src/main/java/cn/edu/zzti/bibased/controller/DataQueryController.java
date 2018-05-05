package cn.edu.zzti.bibased.controller;



import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.ResultMap;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouQueryService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据查询
 * Created by huaidou on  2018/4/13
 */
@RestController
@RequestMapping("/rest/query")
public class DataQueryController {

    @Resource
    private LagouQueryService queryService;
    @Resource
    private AcquisitionService acquisitionService;

    /**
     * 查询公司
     *
     * @return
     */
    @RequestMapping("/v1/queryCompany")
    @ResponseBody
    public Object queryCompany(){

        return null;
    }

    /**
     * 职位信息查询
     *
     * @return
     */
    @RequestMapping("/v1/queryPostionDetail")
    @ResponseBody
    public Object queryPostionDetail(String[] province,String websine,String workYear,String salary ,String companySize,String positionType,String finance,Integer pageNum,Integer pageSize){
        pageNum = 0;
        pageSize = 20;

        return  queryService.queryPositionDetailWithBaseQuery(province,websine,workYear,salary,companySize,positionType,finance,pageNum,pageSize);
    }

    @RequestMapping("/v1/queryCity")
    @ResponseBody
    public  List<String> queryCity(String code){
        List<String> cities = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                List<City> citys = acquisitionService.queryCitys(websiteEnum.getWebCode());
                if(!CollectionUtils.isEmpty(citys)){
                    cities = citys.stream().map(City::getCityName).collect(Collectors.toList());
                }
            }
        }
        return cities;
    }



}
