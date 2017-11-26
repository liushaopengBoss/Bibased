package cn.edu.zzti.bibased.ServiceTest;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.dao.*;
import cn.edu.zzti.bibased.pojo.PojoTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class pojoServiceTest  extends BaseApplicationTests{

    @Resource
    TestMapper testMapper;

    @Test
    public void insertTest(){
        List<PojoTest> pojoTests = testMapper.get();
        testMapper.insert(new PojoTest("344jjh345","456456456"));

    }
}
