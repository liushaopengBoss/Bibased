package cn.edu.zzti.bibased.dao.cache;

import cn.edu.zzti.bibased.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *缓存 解决加载缓慢的问题
 *
 * Created by huaidou on  2018/3/22
 */
@Repository
public class Cache {
    Logger logger = LoggerFactory.getLogger(Cache.class);
    private static Map<String,Object>  cache = new ConcurrentHashMap<>();

    public void add(String key,Object value){
        cache.put(key,value);
    }

    public void clear(String key){
        cache.remove(key);
    }

    public Object getValue(String key){
        return cache.get(key);
    }

    public boolean containsKey(String key){
        return cache.containsKey(key);
    }

    public void clearInvalideData(){
        String yestoday = DateUtils.formatStr(DateUtils.getAfterDate(new Date(), 0), DateUtils.YYMMDD);
        logger.info(""+yestoday);
        List<String> list = new ArrayList<>();
        for(String key:cache.keySet()){
            logger.info(""+key);
            if(key.contains(yestoday)){
                list.add(key);
            }
        }
        for(String key:list){
            cache.remove(key);
        }
    }
}
