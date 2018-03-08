package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.service.operation.lagou.LagouOperationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据分析服务
 *
 * Created by huaidou on  2018/3/7
 */
@RestController
@RequestMapping("/rest")
public class DataAnalysisController {

    @Resource
    private LagouOperationService lagouOperationService;

    @RequestMapping("/v1/queryCityCompanNum")
    @ResponseBody
    public List<Company> queryCityCompanNum(){
        return lagouOperationService.queryCityCompanNum();
    }
    @RequestMapping("/v1/queryFinanceStageCompanNum")
    @ResponseBody
    public List<Company> queryFinanceStageCompanNum(){
        return lagouOperationService.queryFinanceStageCompanNum();
    }
    @RequestMapping("/v1/queryIndustryCompanNum")
    @ResponseBody
    public List<Company> queryIndustryCompanNum(){
        return lagouOperationService.queryIndustryCompanNum();
    }
    @RequestMapping("/v1/queryPositionTypeNums")
    @ResponseBody
    public List<Positions> queryPositionTypeNums(){
        return lagouOperationService.queryPositionTypeNums();
    }

}
