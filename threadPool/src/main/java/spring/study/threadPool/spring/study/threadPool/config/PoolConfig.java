package spring.study.threadPool.spring.study.threadPool.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class PoolConfig {

	/**
	 * 线程池的策略是，先使用corePoolSize来执行任务
	 * 如果corePoolSize不够用，接下来会进入缓存队列排队queueCapacity
	 * 如果queueCapacity的队列满了以后，会将执行任务的线程数扩大到maxPoolsize的数量
	 * 如果此时还有新任务进来，就会采用RejactedExcutionHandler配置的策略还出来新任务
	 * 这里配置的是使用主线程来执行，因为我在执行任务的时候使用了CountDownLatch，所以不能直接拒绝，那样会导致祝线程在await的时候挂起
	 */
	private int corePoolSize = 10;  // 线程池维护线程的最少数量
	private int maxPoolSize = 20;  // 最大数量
	private int queueCapacity = 10; // 缓存队列
	private int keepAlive = 60;  // 允许的空闲时间

	@Bean
	public Executor myExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAlive);
		executor.setThreadNamePrefix("mqExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());	// 这种配置，当线程池的线程数不够的时候，会使用main线程参与任务的执行
		executor.initialize();
		return executor;
	}
}
