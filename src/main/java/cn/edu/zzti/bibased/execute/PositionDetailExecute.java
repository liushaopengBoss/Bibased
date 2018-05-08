package cn.edu.zzti.bibased.execute;

import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.lagou.PositionDetailResultJsonVo;
import cn.edu.zzti.bibased.service.handler.BossHandler;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Scope(value = "prototype")
public class PositionDetailExecute extends BaseExecuter {
    private Logger logger = LoggerFactory.getLogger(PositionDetailExecute.class);
    @Override
    protected Object builderResult() {
        switch (websiteEnum){
            case BOSS:{
                String html = httpClientService.doGet(apiUrl, this.params, this.headers);
                return handleBossPositionDetails(html);
            }
            case JOB:{
                break;
            }
            case LAGOU:{
                String data = httpClientService.doPost(apiUrl, this.params, this.headers);
                return handleLagouPositions(data);
            }
        }
        return null;
    }

    private List<PositionDetail> handleBossPositionDetails(String html){
    return BossHandler.getBossPositionDetails(html);
    }
    private PositionDetailResultJsonVo handleLagouPositions(String sourceJson) {

        PositionDetailResultJsonVo positionDetailResultJsonVo = null;
        if (StringUtils.isNotBlank(sourceJson)) {
            String targetJson = null;
            try {

                logger.debug("职位信息json" + sourceJson);
                JsonElement jsonElement = new JsonParser().parse(sourceJson);
                targetJson = jsonElement.getAsJsonObject().get("content").getAsJsonObject().get("positionResult").toString();
            } catch (Exception e) {
                logger.error("职位json获取值失败", e);
            }
            Gson gson = new Gson();
            positionDetailResultJsonVo = gson.fromJson(targetJson == null ? "{}" : targetJson, PositionDetailResultJsonVo.class);
        }
        if (positionDetailResultJsonVo == null || positionDetailResultJsonVo.getTotalCount() ==0|| positionDetailResultJsonVo.getResultSize()==0) {
            positionDetailResultJsonVo = new PositionDetailResultJsonVo();
            positionDetailResultJsonVo.setResultSize(1);
            positionDetailResultJsonVo.setTotalCount(1);
        }
        return positionDetailResultJsonVo;
    }

    /**
     * 获取页数
     *
     * @return
     */
    @Override
    public int getPageSize(){
        try {
            String data = httpClientService.doPost(apiUrl, this.params, this.headers);
            logger.debug("页面数json："+data);
            PositionDetailResultJsonVo positionDetailResultJsonVo= handleLagouPositions(data);
            if(positionDetailResultJsonVo !=null){
                return positionDetailResultJsonVo.getTotalCount()/positionDetailResultJsonVo.getResultSize();
            }
        }catch (Exception e){
            logger.error("获取总页数失败",e);
        }
        return 0;
    }

}
