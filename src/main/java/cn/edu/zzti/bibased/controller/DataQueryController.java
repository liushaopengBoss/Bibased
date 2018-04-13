package cn.edu.zzti.bibased.controller;



import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.ResultMap;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据查询
 * Created by huaidou on  2018/4/13
 */
@RestController
@RequestMapping("/rest/query")
public class DataQueryController {



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
    public Object queryPostionDetail(String[] province,String websine,String workYear,String salary ,String companySize,String positionType,String finance){

        return  null;
    }


}
