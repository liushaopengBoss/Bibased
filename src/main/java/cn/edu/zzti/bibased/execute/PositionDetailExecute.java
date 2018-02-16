package cn.edu.zzti.bibased.execute;

import cn.edu.zzti.bibased.dto.lagou.PositionDetailResultJsonVo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope(value = "prototype")
public class PositionDetailExecute extends BaseExecuter {
    private Logger logger = LoggerFactory.getLogger(PositionDetailExecute.class);
    @Override
    protected PositionDetailResultJsonVo builderResult() {
        String data = httpClientService.doPost(apiUrl, this.params, this.headers);
        return handlePositions(data);
    }

    private PositionDetailResultJsonVo handlePositions(String sourceJson) {

        PositionDetailResultJsonVo positionDetailResultJsonVo = null;
        if (StringUtils.isNotBlank(sourceJson)) {
            String targetJson = null;
            try {
                logger.info("职位信息json" + sourceJson);
                JsonElement jsonElement = new JsonParser().parse(sourceJson);
                targetJson = jsonElement.getAsJsonObject().get("content").getAsJsonObject().get("positionResult").toString();
            } catch (Exception e) {
                logger.error("职位json获取值失败", e);
            }
            Gson gson = new Gson();
            positionDetailResultJsonVo = gson.fromJson(targetJson == null ? "{}" : targetJson, PositionDetailResultJsonVo.class);
        }
        if (positionDetailResultJsonVo == null) {
            positionDetailResultJsonVo = new PositionDetailResultJsonVo();
            positionDetailResultJsonVo.setResultSize(1);
            positionDetailResultJsonVo.setTotalCount(0);
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
            logger.info("页面数json："+data);
            PositionDetailResultJsonVo positionDetailResultJsonVo= handlePositions(data);
            if(positionDetailResultJsonVo !=null){
                return positionDetailResultJsonVo.getTotalCount()/positionDetailResultJsonVo.getResultSize();
            }
        }catch (Exception e){
            logger.error("获取总页数失败",e);
        }
        return 0;
    }

}
