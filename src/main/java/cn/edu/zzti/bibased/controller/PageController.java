package cn.edu.zzti.bibased.controller;

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

    /**
     * 首页查询{主页}
     * @return
     */
    @RequestMapping(value = "/index")
    public String homepageIndex() {
        return INDEX;
    }

    @RequestMapping(value = "/index_homepage")
    public String homepage() {
        return INDEHOMEPAGE;
    }

    @RequestMapping(value = "/charts")
    public String charts() {
        return CHARTS;
    }
}
