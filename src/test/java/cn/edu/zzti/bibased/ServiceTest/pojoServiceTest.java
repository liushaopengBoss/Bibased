package cn.edu.zzti.bibased.ServiceTest;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.dao.*;
import cn.edu.zzti.bibased.pojo.PojoTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;

public class pojoServiceTest  extends BaseApplicationTests{

    @Resource
    TestMapper testMapper;
    ExecutorCompletionService executorCompletionService;
    @Test
    public void insertTest(){
        List<PojoTest> pojoTests = testMapper.get();
        testMapper.insert(new PojoTest("344jjh345","456456456"));

    }
}
