package cn.edu.zzti.bibased.DaoTest;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.pojo.PojoTest;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

public class DaoTest extends BaseApplicationTests {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Test
    public void t1(){
        jdbcTemplate.execute("INSERT INTO `test1` VALUES ('13545kfgrtsd64', '你好！！')");
    }
    @Test
    public void findAll() {

        System.out.printf("");
    }
}
