package cn.edu.zzti.bibased.queue;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者 spring-kafka 2.0 + 依赖JDK8
 */
@Component
public class KafkaConsumer {

    /**
     * 监听seckill主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {"seckill"})
	public void receiveMessage(String message){
		//收到通道的消息之后执行秒杀操作
		String[] array = message.split(";");
		System.out.printf(message+"\n");
	}
}