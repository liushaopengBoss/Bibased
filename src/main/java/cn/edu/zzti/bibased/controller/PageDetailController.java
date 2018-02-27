package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.constant.ProjectItem;
import cn.edu.zzti.bibased.service.operation.other.ActionLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * 页面数据渲染
 *
 * Created by huaidou on  2018/2/27
 */
@Controller
@RequestMapping("/view")
public class PageDetailController {
    @Resource
    private ActionLogService actionLogService;
    private static final String PAGE_HOME = "page/";
    private static final String PROJECT_DETAIL = PAGE_HOME+"project_detail";
    @RequestMapping(value = "/v1/get_project_detail")
    public String getProjectDetail(String webType,Integer typeCode,Model model){
        model.addAttribute("actionLogs",actionLogService.queryActionLog(webType, typeCode));
        model.addAttribute("projectName", ProjectItem.getProjectItemCode(typeCode));
        return PROJECT_DETAIL;
    }


}
