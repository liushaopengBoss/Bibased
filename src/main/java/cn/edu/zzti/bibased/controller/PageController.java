package cn.edu.zzti.bibased.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于页面跳转
 *
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
    private static final String LAGOU_ANALYSIS = PAGE_HOME + "lagou_analysis";
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
    public String homepageIndex() {
        return INDEX;
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index_homepage")
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
    /**
     *lagou 数据分析
     *
     * @return
     */
    @RequestMapping(value = "/lagou_analysis")
    public String lagouAnalysis() {
        return LAGOU_ANALYSIS;
    }
}
