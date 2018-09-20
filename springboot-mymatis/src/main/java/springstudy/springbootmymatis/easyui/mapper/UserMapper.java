package springstudy.springbootmymatis.easyui.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import springstudy.springbootmymatis.easyui.entity.User;

/** 
* @author shuwei 
* @version 创建时间：2018年8月13日 下午5:52:39 
* 类说明 
*/
public interface UserMapper {
  @Select("select * from sys_user")
  public List<User> listUsers();
  
  @Select("select * from sys_user where id=#{id}")
  public User findById(long id);
  
  @Insert("insert into sys_user(firstname, lastname, phone, email) values(#{firstname}, #{lastname}, #{phone}, #{email})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  public void insert(User u);
  
  @Update("update sys_user set firstname=#{firstname}, lastname=#{lastname}, phone=#{phone}, email=#{email} where id=#{id}")
  public int update(User u);
}
