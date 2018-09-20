package springstudy.springbootmymatis.mapper;

import org.apache.ibatis.annotations.Select;

import springstudy.springbootmymatis.entity.SysUser;

public interface SysUserMapper {
  // @Select("select * from sys_user where user_id=#{userId}")
  public SysUser getById(long userId);
  
  public long insert(SysUser user);
}
