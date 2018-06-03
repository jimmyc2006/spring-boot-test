package cn.springboot.sundry.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.springboot.sundry.security.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
  SysUser findByUsername(String username);
}
