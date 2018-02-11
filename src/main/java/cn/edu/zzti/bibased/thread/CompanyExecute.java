package cn.edu.zzti.bibased.thread;


import cn.edu.zzti.bibased.dto.lagou.CompanyResultJsonVO;
import com.google.gson.Gson;

/**
 * 拉钩公司信息
 *
 * Created by huaidou on  2018/1/31
 */
public class CompanyExecute extends BaseTask {


    @Override
    protected CompanyResultJsonVO builderResult() {
        Gson gson = new Gson();
        String data = httpClientService.doPost(this.apiUrl, this.params, this.headers);
        CompanyResultJsonVO companyResultJsonVO = gson.fromJson(data, CompanyResultJsonVO.class);
        return companyResultJsonVO;
    }
}
