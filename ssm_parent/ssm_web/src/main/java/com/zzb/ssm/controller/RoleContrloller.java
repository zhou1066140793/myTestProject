package com.zzb.ssm.controller;

import com.zzb.ssm.domain.Permission;
import com.zzb.ssm.domain.Role;
import com.zzb.ssm.service.IPermissionService;
import com.zzb.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleContrloller {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Role> roleList = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }
    @Secured("ROLE_ADMIN")
    @RolesAllowed("ADMIN")
    @PreAuthorize("authentication.principal.username == 'jack'")
    @RequestMapping("save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",
            required = true) String roleId) {
        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.findRoleById(roleId);
        List<Permission> permissionList = permissionService.findOtherByRoleId(roleId);
        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam("roleId") String roleId,
                                      @RequestParam("ids") String[] permissionIds) {
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";

    }
}
