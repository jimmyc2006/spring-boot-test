package springstudy.springbootmymatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springstudy.springbootmymatis.dao.HelloDao;
import springstudy.springbootmymatis.mapper.SysUserMapper;
import springstudy.springbootmymatis.mapper2.SysUserMapper2;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMymatisApplicationTests {
  @Autowired
  private HelloDao helloDao;
  @Autowired
  private SysUserMapper sysUserMapper;
  @Autowired
  private SysUserMapper2 SysUserMapper2;
  
  @Test
  public void contextLoads() {
    // System.out.println(this.helloDao.queryForList());
    System.out.println(this.sysUserMapper.getById(1L));
    System.out.println("------------------------");
    System.out.println(this.SysUserMapper2.getById(1L));
//    SysUser su = new SysUser();
//    su.setName("testName");
//    su.setPassword("pppppp");
//    this.sysUserMapper.insert(su);
//    System.out.println(su);
  }
}
