package cn.edu.zzti.bibased;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
@ComponentScan("cn.edu.zzti.bibased")
@ImportResource("classpath:applicationContext-httpclient.xml")
@RunWith(SpringRunner.class)
@SpringBootTest
public  class BaseApplicationTests {
}
