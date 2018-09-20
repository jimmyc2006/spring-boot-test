package springstudy.springbootmymatis.mapper2;

import org.apache.ibatis.annotations.Select;

import springstudy.springbootmymatis.entity.SysUser;

public interface SysUserMapper2 {
  @Select("select * from sys_user where user_id=#{userId}")
  public SysUser getById(long userId);
  
  // public long insert(SysUser user);
}
