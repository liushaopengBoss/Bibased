package cn.edu.zzti.bibased;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ImportResource("classpath:applicationContext-config.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplicationTests.class)
@WebAppConfiguration
@MapperScan("cn.edu.zzti.bibased.dao.read")//mapper扫描
public  class BaseApplicationTests {
}
