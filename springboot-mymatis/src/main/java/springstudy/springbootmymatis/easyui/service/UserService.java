package springstudy.springbootmymatis.easyui.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import springstudy.springbootmymatis.easyui.entity.User;
import springstudy.springbootmymatis.easyui.mapper.UserMapper;

/** 
* @author shuwei 
* @version 创建时间：2018年8月13日 下午5:51:13 
* 类说明 
*/
@Service
public class UserService {
  private Logger log = LoggerFactory.getLogger(UserService.class);

  private final Map<Long, User> cacheMap = new ConcurrentHashMap<>();
  @Autowired
  private UserMapper userMapper;
  
  @Cacheable(value = "people")
  public List<User> listAllUser() {
    return this.userMapper.listUsers();
  }
  
  public User getUser(long id) {
	  log.info("缓存中内容:" + this.cacheMap.get(id));
	  this.cacheMap.computeIfAbsent(id, this::gUser);
	  return this.cacheMap.get(id);
  }
  
  private User gUser(long id) {
	  log.info("查询用户:" + id);
	  return this.userMapper.findById(id);
  }
  
  public void refreshUserCache(long id) {
	  this.cacheMap.put(id, gUser(id));
  }
  
  public void save(User u) {
    if (u.getId() > 0) {
      this.userMapper.update(u);
    } else {
      this.userMapper.insert(u);
    }
  }
}
