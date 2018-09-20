package springstudy.springbootmymatis.config.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import springstudy.springbootmymatis.easyui.service.UserService;

/**
 * @author shuwei
 * @version 创建时间：2018年8月17日 下午2:35:55 定时刷新缓存
 */
@Configuration
@Component
@EnableScheduling
public class LocalCache implements InitializingBean, ApplicationListener<ApplicationEvent> {
	private static final Logger log = LoggerFactory.getLogger(LocalCache.class);
	@Autowired
	private UserService userService;
	
	// 如果到时间，上一个批次没有执行完，则不会开启下一个批次
	@Scheduled(cron = "0 * *  * * *")    // 每个小时执行一次
	public void monitor() {
		try {
			log.info("start sleep");
			Thread.sleep(1000 * 100);
			log.info("end sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.userService.refreshUserCache(1);
	}

	private int testValue = 1;
	@Override
	public void afterPropertiesSet() throws Exception {
		// 设置值为2
		log.info("SSSSS afterPropertiesSet:" + testValue);
		testValue = 100;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.info("SSSSS onApplicationEvent:" + testValue);
		testValue = 200;
	}
	
}
