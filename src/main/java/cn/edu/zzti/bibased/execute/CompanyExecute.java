package cn.edu.zzti.bibased.execute;


import cn.edu.zzti.bibased.dto.lagou.CompanyResultJsonVO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * 拉钩公司信息
 *
 * Created by huaidou on  2018/1/31
 */
@Service
@Scope(value = "prototype")
public class CompanyExecute extends BaseExecuter {
    private Logger logger =  LoggerFactory.getLogger(CompanyExecute.class);

    @Override
    protected CompanyResultJsonVO builderResult() {
        String data = httpClientService.doPost(this.apiUrl, this.params, this.headers);
        CompanyResultJsonVO companyResultJsonVO =  new Gson().fromJson(data, CompanyResultJsonVO.class);
        return companyResultJsonVO;
    }
}
