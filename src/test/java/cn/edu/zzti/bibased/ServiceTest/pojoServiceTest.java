package cn.edu.zzti.bibased.ServiceTest;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.dao.*;
import cn.edu.zzti.bibased.pojo.PojoTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class pojoServiceTest  extends BaseApplicationTests{

    @Resource
    TestMapper TestMapper;
    @Test
    public void t1(){

        TestMapper.insert(new PojoTest("34534345","456456456"));

    }
}
