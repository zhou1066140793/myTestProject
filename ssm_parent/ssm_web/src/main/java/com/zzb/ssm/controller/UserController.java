package com.zzb.ssm.controller;

import com.zzb.ssm.domain.Role;
import com.zzb.ssm.domain.UserInfo;
import com.zzb.ssm.service.IRoleService;
import com.zzb.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> all = userService.findAll();
        System.out.println(all);
        modelAndView.setViewName("user-list");
        modelAndView.addObject("userList",all);
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public void saveUser(UserInfo user , HttpServletResponse response) throws IOException {
        userService.save(user);
        response.sendRedirect("findAll.do");
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id") String id){
        UserInfo userById = userService.findUserById(id);
        System.out.println(userById);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user" , userById);
        modelAndView.setViewName("user-show1");
        return modelAndView;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam("id")String uid){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userById = userService.findUserById(uid);
        List<Role> roles = roleService.findOther(userById.getId());
        modelAndView.addObject("user" , userById);
        modelAndView.addObject("roleList" , roles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId , String[] ids){
        userService.addRoleToUser(userId , ids);
        return "redirect:findAll.do";
    }

}
