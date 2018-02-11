package cn.edu.zzti.bibased.execute;

import cn.edu.zzti.bibased.dto.lagou.PositionDetailResultJsonVo;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
@Scope(value = "prototype")
public class PositionDetailExecute extends BaseExecuter {
    private Logger logger = LoggerFactory.getLogger(PositionDetailExecute.class);
    @Override
    protected PositionDetailResultJsonVo builderResult() {
        String data = httpClientService.doPost(apiUrl, this.params, this.headers);
        return handlePositions(data);
    }


    private PositionDetailResultJsonVo handlePositions(String sourceJson){
        String targetJson = null;
        try {
            JsonElement jsonElement = new JsonParser().parse(sourceJson);
            targetJson =  jsonElement.getAsJsonObject().get("contet").getAsJsonObject().get("positionResult").toString();
        }catch (Exception e){
            logger.error("职位json获取值失败",e);
        }
        Gson gson = new Gson();
        PositionDetailResultJsonVo positionDetailResultJsonVo = gson.fromJson(targetJson == null ? "{}" : targetJson, PositionDetailResultJsonVo.class);
        if(positionDetailResultJsonVo ==null){
            positionDetailResultJsonVo = new PositionDetailResultJsonVo();
            positionDetailResultJsonVo.setResultSize(0);
            positionDetailResultJsonVo.setTotalCount(0);
        }
        return positionDetailResultJsonVo;

    }
}
