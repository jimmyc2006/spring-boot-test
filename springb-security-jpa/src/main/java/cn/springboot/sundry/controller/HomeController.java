package cn.springboot.sundry.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.springboot.sundry.security.entity.Msg;

@Controller
public class HomeController {
  @RequestMapping("/")
  public String index(Model model) {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
    System.out.println(userDetails);
    Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
    model.addAttribute("msg", msg);
    return "index";
  }
}
