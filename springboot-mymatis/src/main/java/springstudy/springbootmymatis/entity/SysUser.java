package springstudy.springbootmymatis.entity;
/** 
* @author shuwei 
* @version 创建时间：2018年8月3日 下午6:38:43 
* 类说明 
*/
public class SysUser {
  private int userId;
  private String name;
  private String realName;
  private String password;
  
  public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getRealName() {
    return realName;
  }
  public void setRealName(String realName) {
    this.realName = realName;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String toString() {
    return "SysUser [userId=" + userId + ", name=" + name + ", realName=" + realName + ", password="
        + password + "]";
  }
}
