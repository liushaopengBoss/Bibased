package cn.edu.zzti.bibased.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    /**
     * 首页查询{主页}
     * @return
     */
    @RequestMapping(value = "/index")
    public String homepageIndex(Model model) {
        model.addAttribute("index","2322");
        return INDEX_SHOPSEARCH;
    }
}
