package cn.edu.zzti.bibased.execute;


import cn.edu.zzti.bibased.dto.PositionDesc;
import cn.edu.zzti.bibased.dto.lagou.CompanyResultJsonVO;
import cn.edu.zzti.bibased.service.handler.LagouHandler;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * 职位描述信息
 *
 * Created by huaidou on  2018/1/31
 */
@Service
@Scope(value = "prototype")
public class PositionDescExecute extends BaseExecuter {

    @Override
    protected PositionDesc builderResult() {
        switch (websiteEnum){
            case BOSS:{
                return null;
            }
            case JOB:{
                break;
            }
            case LAGOU:{
                String html = httpClientService.doGet(apiUrl, this.params, this.headers);
                if(StringUtils.isNotEmpty(html)){
                    PositionDesc positionDesc = LagouHandler.getPositionDesc(html);
                    positionDesc.setPositionId(this.getPositionId());
                    return positionDesc;
                }
            }
        }
        return null;
    }


}
