package springstudy.springbootmymatis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {
  @Autowired
  @Qualifier("localMysqlJdbcTemplate")
  JdbcTemplate jdbcTemplate;

  public int update() {
    String sql = "update sys_user set username = ? where id = ?";
    Object[] params = new Object[] {"张三", 1};
    return jdbcTemplate.update(sql, params);
  }

  public List queryForList() {
    String sql = "select id ,username from sys_user where id = ?";
    Object[] params = new Object[] {1};
    return jdbcTemplate.queryForList(sql, params);
  }
}
