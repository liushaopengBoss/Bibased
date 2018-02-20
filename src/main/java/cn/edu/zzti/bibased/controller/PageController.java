package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.aspect.ActionLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于页面跳转
 * <p>
 * Created by huaidou on  2018/1/18
 */
@Controller
public class PageController {
    private static final String PAGE_HOME = "page/";
    private static final String INDEX_SHOPSEARCH = PAGE_HOME + "charts";
    private static final String INDEX= PAGE_HOME + "index";
    private static final String INDEHOMEPAGE= PAGE_HOME + "index_homepage";
    private static final String CHARTS= PAGE_HOME + "charts";
    private static final String PROJECT= PAGE_HOME + "projects";
    private static final String PROJECT_DETAIL= PAGE_HOME + "project_detail";
    private static final String COMPANY_SEARCH = PAGE_HOME + "company_search";
    /**
     * 首页查询{主页}
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "forward:/index.html";
    }

    /**
     * 首页查询{主页}
     *
     * @return
     */
    @RequestMapping(value = "/index")
    @ActionLog("首页数据Index")
    public String homepageIndex() {
        return INDEX;
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index_homepage")
    @ActionLog("首页数据HomePage")
    public String homepage() {
        return INDEHOMEPAGE;
    }
    /**
     *图表分析
     *
     * @return
     */
    @RequestMapping(value = "/charts")
    public String charts() {
        return CHARTS;
    }
    /**
     *项目采集信息
     *
     * @return
     */
    @RequestMapping(value = "/projects")
    public String project() {
        return PROJECT;
    }
    /**
     *项目采集详情
     *
     * @return
     */
    @RequestMapping(value = "/project_detail")
    public String projectDetail() {
        return PROJECT_DETAIL;
    }


    /**
     *公司搜索
     *
     * @return
     */
    @RequestMapping(value = "/company_search")
    public String companySearch() {
        return COMPANY_SEARCH;
    }

}
