package spring.study.threadPool.spring.study.threadPool.config.spring.study.service;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

	private static final Logger log = LoggerFactory.getLogger(CalculateService.class);

	private Random rand = new Random(System.currentTimeMillis());

	@Async
	public void execute(CountDownLatch countDownLatch) {
		int sleepMillions = rand.nextInt(3000);
		try {
			log.info("开始休息" + sleepMillions);
			Thread.sleep(sleepMillions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("休眠结束");
		countDownLatch.countDown();
	}
}
