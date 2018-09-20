package springstudy.springbootmymatis.easyui.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springstudy.springbootmymatis.easyui.entity.User;
import springstudy.springbootmymatis.easyui.service.UserService;

/** 
* @author shuwei 
* @version 创建时间：2018年8月13日 下午5:50:34 
* 类说明 
*/
@Controller
@RequestMapping("user")
public class UserController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  
  @Autowired
  private UserService userService;
  
  @RequestMapping("page")
  public ModelAndView page(ModelAndView mv) {
    log.info("tackingPage");
    mv.setViewName("main/crud1/datagrid1");
    return mv;
  }
  
  @RequestMapping("all")
  @ResponseBody
  public Object all(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    List<User> result = this.userService.listAllUser();
    return result;
  }
  
  @RequestMapping("get")
  @ResponseBody
  public Object get(long id) {
    User u = this.userService.getUser(id);
    return u;
  }
  
  @RequestMapping("save")
  @ResponseBody
  public Object save(User user) {
    this.userService.save(user);
    return user;
  }
}
