package cn.edu.zzti.bibased.service.handler;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.Positions;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

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


}
