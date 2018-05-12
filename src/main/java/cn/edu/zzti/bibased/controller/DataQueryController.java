package cn.edu.zzti.bibased.controller;



import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.*;
import cn.edu.zzti.bibased.dto.page.PageResult;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouQueryService;
import cn.edu.zzti.bibased.service.operation.other.PositionKeyWordSevice;
import cn.edu.zzti.bibased.service.operation.other.PositionNumDayService;
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
    @Resource
    private PositionKeyWordSevice positionKeyWordSevice;
    @Resource
    private PositionNumDayService positionNumDayService;

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
    public Object queryPostionDetail(String city,String websine,String workYear,String salary ,String companySize,String positionType,String finance,Integer pageNum,Integer pageSize){
        PageResult pageResult = new PageResult();
        if(pageNum == null || pageNum <1){
            pageNum = 1;
        }
        pageSize = 20;
        List<PositionDetail> positionDetails = queryService.queryPositionDetailWithBaseQuery(city, websine, workYear, salary, companySize, positionType, finance, pageNum, pageSize);
        pageResult.setPageNum(pageNum);
        pageResult.setPositionDetailList(positionDetails);
        return pageResult;
    }

    @RequestMapping("/v1/queryCity")
    @ResponseBody
    public  List<String> queryCity(String code){
        List<String> citys = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                citys = acquisitionService.queryCityList(websiteEnum.getWebCode());
            }
        }
        return citys;
    }


     @RequestMapping("/v1/queryKeyWordPositionTypes")
    @ResponseBody
    public List<String> queryKeyWordPositionTypes(){
        return positionKeyWordSevice.queryPositionTypes();
    }


    @RequestMapping("/v1/queryNumDayPositionTypes")
    @ResponseBody
    public List<String> queryNumDayPositionTypes(){
        return positionNumDayService.queryPositionTypes();
    }
    @RequestMapping("/v1/queryYestodayPositionKeyWord")
    @ResponseBody
    public List<PositionKeyword> queryYestodayPositionKeyWord(String positionType){
        if(positionType.trim().equals("C")){
            positionType = "C++";
        }
        return positionKeyWordSevice.queryPositionKeyWordByCurrrDate(positionType.trim());
    }
    @RequestMapping("/v1/queryDataRangePositionKeyWordNums")
    @ResponseBody
    public List<PositionKeyword>  queryDataRangePositionKeyWordNums(String positionType){
        if(positionType.trim().equals("C")){
            positionType = "C++";
        }
        return positionKeyWordSevice.queryPositionKeyWordNumsByDateRangeAndPosition(positionType.trim(),null);
    }

    @RequestMapping("/v1/queryDateRangPositionNumDay")
    @ResponseBody
    public List<PositionNumDay> queryDateRangPositionNumDay(String positionType){
        return positionNumDayService.queryDateRangPositionNumDay(positionType.trim());
    }
    @RequestMapping("/v1/queryDateRangPositionNumDaySum")
    @ResponseBody
    public List<PositionNumDay> queryDateRangPositionNumDaySum(String positionType){
        if(positionType.trim().equals("C")){
            positionType = "C++";
        }
        return positionNumDayService.queryDateRangPositionNumDaySum(positionType.trim());
    }

    @RequestMapping("/v1/queryLastMonthPositionKeyWord")
    @ResponseBody
    public List<PositionKeyword> queryLastMonthPositionKeyWordByPositionType(String positionType){
        if(positionType.trim().equals("C")){
            positionType = "C++";
        }
        return positionKeyWordSevice.queryLastMonthPositionKeyWordByPositionType(positionType.trim());
    }
}
