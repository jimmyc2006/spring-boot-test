package spring.study.threadPool;

import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.study.threadPool.spring.study.threadPool.config.spring.study.service.CalculateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolApplicationTests {

	@Autowired
	private CalculateService cSerlvice;

	@Test
	public void contextLoads() {
		int count = 40;
		CountDownLatch countDownLatch = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			this.cSerlvice.execute(countDownLatch);
		}
		try {
			System.out.println("开始await");
			countDownLatch.await();
			System.out.println("await结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
