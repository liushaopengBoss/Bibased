package cn.edu.zzti.bibased;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/3/14
 */
public class OtherTests extends BaseApplicationTests {

    @Test
    public void BigDecimalTest(){
        double s = 10.9030;
        BigDecimal big = new BigDecimal("10.9030");
        big.setScale(1,BigDecimal.ROUND_HALF_UP);


        DecimalFormat df4 = new DecimalFormat();
        // #：位置上无数字不显示
        df4.applyPattern("#.##");
        System.out.printf(""+df4.format(big));
    }
}
