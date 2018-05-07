package cn.edu.zzti.bibased.service.operation.other;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dao.position.PositionDescDao;
import cn.edu.zzti.bibased.dao.position.PositionKeywordDao;
import cn.edu.zzti.bibased.dto.PositionDesc;
import cn.edu.zzti.bibased.dto.PositionKeyword;
import cn.edu.zzti.bibased.service.ikanalyzer.IKAnalzyerService;
import cn.edu.zzti.bibased.utils.DateUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PositionKeyWordSevice {

    @Resource
    private PositionDescDao positionDescDao;
    @Resource
    private IKAnalzyerService ikAnalzyerService;
    @Resource
    private PositionKeywordDao positionKeywordDao;

    /**
     * 分词
     */
    public void keyWord(){

        String yestodayDate = DateUtils.format(DateUtils.getAfterDate(new Date(), -1), "yyyyMMdd");
        List<String> positionTypes = positionDescDao.queryPositionTypes(yestodayDate);
        if(!CollectionUtils.isEmpty(positionTypes)){
            for(String positionType:positionTypes){
                Integer totalNum = positionDescDao.queryPositionDescCount(yestodayDate,positionType);

                int pageNum = totalNum/300+(totalNum%300 == 0 ? 0:1);

                List<PositionKeyword> positionKeywords = new ArrayList<>();
                Map<String,Integer>  keyWordNums = new HashMap<>();
                for(int i=0;i<pageNum;i++){

                    List<PositionDesc> positionDesc = positionDescDao.getPositionDesc(yestodayDate, positionType,i * 300, 300);

                    if(!CollectionUtils.isEmpty(positionDesc)){

                        for(int j=0;j<positionDesc.size();j++){

                            try {
                                Set<String> sensitiveWord = ikAnalzyerService.getSensitiveWord(positionDesc.get(j).getTenureRequirements());
                                handleSensitiveWord(sensitiveWord,keyWordNums);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }


                }
                handleToPositionKeyWord(keyWordNums,positionKeywords,positionType);
                if(!CollectionUtils.isEmpty(positionKeywords)){
                    positionKeywordDao.batchAddPositionKeyWord(positionKeywords);
                }
            }

        }

    }
   private void handleToPositionKeyWord(Map<String, Integer> keyWordNums,List<PositionKeyword> positionKeywords,String positionType){

    if(!MapUtils.isEmpty(keyWordNums)){
        Iterator<String> iterator = keyWordNums.keySet().iterator();
        while(iterator.hasNext()){
            PositionKeyword positionKeyword = new PositionKeyword();
            positionKeywords.add(positionKeyword);
            String keyWord = iterator.next();
            String yestodayDate = DateUtils.format(DateUtils.getAfterDate(new Date(), -1), "yyyyMMdd");
            positionKeyword.setCurrDate(yestodayDate);
            positionKeyword.setInclude(WebsiteEnum.LAGOU.getWebCode());
            positionKeyword.setKeywordName(keyWord);
            positionKeyword.setKeywordNum(keyWordNums.get(keyWord));
            positionKeyword.setPositionType(positionType);
        }



    }
   }
    private void handleSensitiveWord(Set<String> sensitiveWord, Map<String, Integer> keyWordNums) {
        Iterator<String> iterator = sensitiveWord.iterator();
        while(iterator.hasNext()){
            String keyWord = iterator.next();
            if(keyWordNums.containsKey(keyWord)){
                keyWordNums.put(keyWord,keyWordNums.get(keyWord)+1);
            }else{
                keyWordNums.put(keyWord,1);
            }
        }
    }


    public List<String> queryPositionTypes(){
        return positionKeywordDao.queryPositionTypes();
    }
}
