package cn.edu.zzti.bibased;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/!\
//		 .' @          /!!!\
//		 :         ,    !!!!
//		  `-..__.-' _.-\!!!/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //  保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)


//
//=======================================================
/**
 * spring-boot启动类
 */
@EnableAsync // 启动异步调用
@ImportResource("classpath:application-config.xml")//导入配置文件
@SpringBootApplication()//springboot
@EnableTransactionManagement//事务
public class Starter {
    private static ConfigurableApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Starter.class, args);
    }
    public static void exit() {
        SpringApplication.exit(applicationContext);
    }
}