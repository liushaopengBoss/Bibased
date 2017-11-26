package cn.edu.zzti.bibased;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * spring-boot启动类
 */
//@ImportResource("classpath:*.xml")
@MapperScan("cn.edu.zzti.bibased.dao")
@SpringBootApplication()
public class Starter {
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Starter.class, args);
    }
    public static void exit() {
        SpringApplication.exit(applicationContext);
    }
}