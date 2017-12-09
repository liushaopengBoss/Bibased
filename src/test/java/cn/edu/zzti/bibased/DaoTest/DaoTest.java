package cn.edu.zzti.bibased.DaoTest;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.dao.write.LaGouWriteDao;
import cn.edu.zzti.bibased.pojo.PojoTest;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class DaoTest extends BaseApplicationTests {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    LaGouWriteDao LaGouWriteDao;
    @Test
    public void t1(){
        jdbcTemplate.execute("INSERT INTO `test1` VALUES ('13545kfgrtsd64', '你好！！')");
    }
    @Test
    public void findAll() {

        List<PojoTest> list = new ArrayList<>(3);
        list.add(new PojoTest("3434545","hahaha1"));
        list.add(new PojoTest("34344345","hahaha2"));
        list.add(new PojoTest("343434556","hahaha3"));
        LaGouWriteDao.batchAdd(list);
    }
}
