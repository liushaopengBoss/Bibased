package cn.edu.zzti.bibased.service.handler;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.PositionDesc;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhilianHandler {

    public static List<Positions>  handlerPositionType(String html){
        List<Positions> positions = new ArrayList<>();
        if(StringUtils.isNotEmpty(html)) {
            Document zhilianPositions = Jsoup.parse(html);
            Element searchJobtypeTag = zhilianPositions.getElementById("search_jobtype_tag");
            if(searchJobtypeTag != null){
                Elements aTags = searchJobtypeTag.getElementsByTag("a");
                for(Element ele:aTags){
                    String positionUrl = ele.attr("href");
                    String positionName = ele.text();
                    Positions position = new Positions();
                    positions.add(position);
                    position.setPositionUrl(positionUrl);
                    position.setPositionName(positionName);
                    position.setInclude(WebsiteEnum.ZHILIAN.getWebCode());
                }
            }
        }
        return positions;
    }

    public static Map<String ,Object> handlePositionDetail(String html){
        Map<String ,Object> map = new HashMap<>();
        List<PositionDetail> positionDetails = new ArrayList<>();
        List<PositionDesc> positionDesc = new ArrayList<>();
        map.put("positionDetail",positionDetails);
        map.put("positionDesc",positionDesc);
        if(StringUtils.isNotEmpty(html)) {
            Document zhilianPositionDetails = Jsoup.parse(html);
            Elements  posiotnDetails = zhilianPositionDetails.getElementsByClass("newlist");
            if(posiotnDetails != null){
                for(Element element:posiotnDetails){
                    PositionDetail positionDetail = new PositionDetail();
                    positionDetails.add(positionDetail);
                    PositionDesc positionDesc1 = new PositionDesc();
                    positionDesc.add(positionDesc1);
                    String  positionName = element.getElementsByClass("zwmc").text();
                    positionDetail.setPositionName(positionName);
                    String  city = element.getElementsByClass("gzdd").text();
                    positionDetail.setCity(city);
                    String  salary = element.getElementsByClass("zwyx").text();
                    positionDetail.setSalary(salary);
                    String  postionDesc = element.getElementsByClass("newlist_deatil_two").text();
                    positionDesc1.setInclude(WebsiteEnum.ZHILIAN.getWebCode());
                    positionDesc1.setPostDuties(postionDesc);
                    positionDesc1.setTenureRequirements(postionDesc);
                    Elements spans = element.getElementsByClass("span");
                    for(Element element1 :spans){
                        positionDetail.setCompanySize(element1.data());
                    }
                }
            }
        }
        return map;
    }


}
