package cn.edu.zzti.bibased.utilsTests;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import org.junit.Test;

/**
 * 用途
 * <p>
 * Created by huaidou on  2018/12/14
 */
public class FelTest {

    @Test
    public void t1(){
        FelEngine fel = new FelEngineImpl();
        FelContext ctx = fel.getContext();
        ctx.set("单价", 5);
        ctx.set("数量", 2);
        ctx.set("运费", 5);
        Object result = fel.eval("单价*数量+运费");
        System.out.println(result);
        Expression exp = fel.compile("单价*数量+运费",ctx);
        Object result2 = exp.eval(ctx);
        System.out.println(result2);

    }
}
