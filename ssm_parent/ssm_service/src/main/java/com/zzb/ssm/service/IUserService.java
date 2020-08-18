package com.zzb.ssm.service;

import com.zzb.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll();

    void save(UserInfo user);

    UserInfo findUserById(String id);

    void addRoleToUser(String userId, String[] ids);
}
