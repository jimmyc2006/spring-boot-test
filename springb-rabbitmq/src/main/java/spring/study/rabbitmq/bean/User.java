package spring.study.rabbitmq.bean;

import java.io.Serializable;

public class User implements Serializable {
  private static final long serialVersionUID = 2L;
  
  private String name;
  private String pass;
  private int age;

  public User() {
    super();
  }
  
  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", pass=" + pass + ", age=" + age + "]";
  }
}
