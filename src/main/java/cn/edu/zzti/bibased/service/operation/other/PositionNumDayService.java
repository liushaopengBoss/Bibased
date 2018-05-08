package cn.edu.zzti.bibased.service.operation.other;

import cn.edu.zzti.bibased.dao.position.PositionNumDayDao;
import cn.edu.zzti.bibased.dto.PositionNumDay;
import cn.edu.zzti.bibased.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PositionNumDayService {

    @Resource
    private PositionNumDayDao positionNumDayDao;

    public List<String> queryPositionTypes(){
        return positionNumDayDao.queryPositionTypes();
    }


    public List<PositionNumDay> queryDateRangPositionNumDay(String positionType){
        List<PositionNumDay> result = new ArrayList<>();
        String startDate = DateUtils.format(DateUtils.getAfterDate(new Date(), -60), "yyyyMMdd");
        String endDate = DateUtils.format(new Date(), "yyyyMMdd");
        List<PositionNumDay> positionNumDays = positionNumDayDao.queryDateRangPositionNumDay(startDate, endDate, positionType);
        Map<String,PositionNumDay> positionNumDaysMap = handlePositionNumDaysToMap(positionNumDays);
        try {
            List<String> dateRange = DateUtils.getDateRange(startDate, endDate, "yyyyMMdd");
            for(String strDate:dateRange){
                PositionNumDay positionNumDay = positionNumDaysMap.get(strDate);
                if(positionNumDay == null){
                    positionNumDay = new PositionNumDay();
                    positionNumDay.setPositionNum(0);
                    positionNumDay.setCurrDate(strDate);
                    positionNumDay.setPositionType(positionType);
                }
                result.add(positionNumDay);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Map<String,PositionNumDay> handlePositionNumDaysToMap(List<PositionNumDay> positionNumDays) {
        Map<String,PositionNumDay> map = new HashMap<>();
        if(!CollectionUtils.isEmpty(positionNumDays)){
            for(PositionNumDay po:positionNumDays){
                map.put(po.getCurrDate(),po);
            }
        }
        return map;
    }

    public List<PositionNumDay> queryDateRangPositionNumDaySum(String positionType){
        String afterMonth = DateUtils.format(DateUtils.getAfterMonth(new Date(), -11),"yyyyMM");;
        String nowMonth = DateUtils.format(DateUtils.getAfterMonth(new Date(), 0),"yyyyMM");
        List<PositionNumDay> list = new ArrayList<>();
        try {
            List<String> dataRange = DateUtils.getDateRange(afterMonth, nowMonth, "yyyyMM");
           for(int i=0;i<dataRange.size();i++){
               String beginDate=  DateUtils.getMonthFirstday(dataRange.get(i));
               String endDate;
               if(dataRange.get(i).equals(nowMonth)){
                   endDate = DateUtils.format(new Date(),"yyyyMMdd");
               }else{
                   endDate = DateUtils.getMonthLastday(dataRange.get(i));
               }
               PositionNumDay positionNumDays = positionNumDayDao.queryDateRangPositionNumDaySum(beginDate, endDate, positionType);
               if(positionNumDays == null){
                   positionNumDays = new PositionNumDay();
                   positionNumDays.setPositionNum(0);
               }
               positionNumDays.setCurrDate(dataRange.get(i));
               list.add(positionNumDays);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



}
