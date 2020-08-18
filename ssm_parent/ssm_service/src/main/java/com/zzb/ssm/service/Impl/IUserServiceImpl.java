package com.zzb.ssm.service.Impl;

import com.zzb.ssm.dao.IUserDao;
import com.zzb.ssm.domain.Role;
import com.zzb.ssm.domain.UserInfo;
import com.zzb.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public UserInfo findUserById(String id) {
        System.out.println(userDao.findById(id));
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        for (String roleId : ids) {
            userDao.addRoleToUser(userId , roleId);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        User user = null;
        try {
            userInfo = userDao.findByName(username);
        } catch (Throwable e) {
            throw e;
        }
        user = new User(userInfo.getUsername(), userInfo.getPassword(),
                getAuthority(userInfo.getRoles()));
        return user;
    }

    public static List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        ArrayList<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            grantedAuthorities.add(
                    new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return grantedAuthorities;
    }
}
