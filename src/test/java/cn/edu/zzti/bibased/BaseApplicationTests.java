package cn.edu.zzti.bibased;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplicationTests.class)
@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@ComponentScan(basePackages={"cn.edu.zzti","cn.edu.zzti.bibased.execute"})
@MapperScan("cn.edu.zzti.bibased.dao.mapper")//mapper扫描
@ContextConfiguration(locations = {"classpath:applicationContext-config.xml"})//使用配置文件
@EnableAsync // 启动异步调用
public  class BaseApplicationTests {

}
