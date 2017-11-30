package cn.edu.zzti.bibased;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;
@ImportResource("classpath:*.xml")
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.edu.zzti.bibased.dao")
public  class BaseApplicationTests {
}
