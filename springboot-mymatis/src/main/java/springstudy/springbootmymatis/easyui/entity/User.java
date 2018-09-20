package springstudy.springbootmymatis.easyui.entity;
/** 
* @author shuwei 
* @version 创建时间：2018年8月13日 下午5:50:54 
* 类说明 
*/
public class User {
  private long id;
  private String firstname;
  private String lastname;
  private String phone;
  private String email;
  
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getFirstname() {
    return firstname;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  @Override
  public String toString() {
    return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", phone="
        + phone + ", email=" + email + "]";
  }
}
