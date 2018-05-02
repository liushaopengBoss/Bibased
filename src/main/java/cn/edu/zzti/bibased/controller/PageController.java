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
    private static final String LAGOU_PROJECT= PAGE_HOME + "lagou_projects";
    private static final String BOSS_PROJECT= PAGE_HOME + "boss_projects";
    private static final String PROJECT_DETAIL= PAGE_HOME + "project_detail";
    private static final String COMPANY_SEARCH = PAGE_HOME + "company_search";
    private static final String LAGOU_ANALYSIS = PAGE_HOME + "lagou_analysis";
    private static final String BOSS_ANALYSIS = PAGE_HOME + "boss_analysis";
    private static  final String POSITIONdETAIL_SEARCH = PAGE_HOME+"positionDetail_search";
    private static  final String INDEX_HOME = PAGE_HOME+"index_home";
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
    @RequestMapping(value = "/lagou_projects")
    public String lagouProject() {
        return LAGOU_PROJECT;
    }
    /**
     *项目采集信息
     *
     * @return
     */
    @RequestMapping(value = "/boss_projects")
    public String bossPproject() {
        return BOSS_PROJECT;
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
     *公司搜索
     *
     * @return
     */
    @RequestMapping(value = "/positionDetail_search")
    public String positionDetailSearch() {
        return POSITIONdETAIL_SEARCH;
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
    /**
     *lagou 数据分析
     *
     * @return
     */
    @RequestMapping(value = "/boss_analysis")
    public String bossAnalysis() {
        return BOSS_ANALYSIS;
    }
    /**
     *index
     *
     * @return
     */
    @RequestMapping(value = "/index_home")
    public String indexHome() {
        return INDEX_HOME;
    }

}
