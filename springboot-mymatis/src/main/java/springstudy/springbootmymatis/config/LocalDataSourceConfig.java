package springstudy.springbootmymatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;


/** 
* @author shuwei 
* @version 创建时间：2018年8月10日 下午2:19:28 
* 第一个数据源，本地mysql
*/
@Configuration
@MapperScan(basePackages= {"springstudy.springbootmymatis.mapper", "springstudy.springbootmymatis.easyui.mapper"}, sqlSessionTemplateRef = "localMysqlSqlSessionTemplate")
@ConfigurationProperties(prefix = "spring.datasource")
public class LocalDataSourceConfig {
  private String url;
  private String username;
  private String password;
  private String driverClassName;
  
  @Bean(name = "localMysqlDataSource")
  public DataSource localDataSource() {
    DruidDataSource datasource = new DruidDataSource();
    datasource.setDriverClassName(driverClassName);
    datasource.setUrl(url);
    datasource.setUsername(username);
    datasource.setPassword(password);
    // configuration
    datasource.setInitialSize(5);
    datasource.setMinIdle(5);
    datasource.setMaxActive(10);
    datasource.setMaxWait(5 * 1000);
    datasource.setTimeBetweenEvictionRunsMillis(1000 * 60);
    // datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    datasource.setValidationQuery("SELECT 'x'");
    datasource.setTestWhileIdle(true);
    datasource.setTestOnBorrow(true);
    datasource.setTestOnReturn(false);
    datasource.setPoolPreparedStatements(true);
    datasource.setMaxPoolPreparedStatementPerConnectionSize(10);
    // datasource
    // .setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
    return datasource;
  }
  
  @Bean(name = "localMysqlJdbcTemplate")
  public JdbcTemplate aaaJdbcTemplate(@Qualifier("localMysqlDataSource") DataSource localDataSource) {
    return new JdbcTemplate(localDataSource);
  }
  
  @Bean(name = "localMysqlSqlSessionFactory")
  public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("localMysqlDataSource") DataSource dataSource)
      throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
    bean.setTypeAliasesPackage("springstudy.springbootmymatis.entity");
    bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
    bean.getObject().getConfiguration().setLazyLoadingEnabled(true);
    return bean.getObject();
  }
  
  @Bean(name = "localMysqlSqlSessionTemplate")
  public SqlSessionTemplate mysqlSqlSessionTemplate(
      @Qualifier("localMysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDriverClassName() {
    return driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }
}
