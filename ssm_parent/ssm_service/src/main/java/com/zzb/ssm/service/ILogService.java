package com.zzb.ssm.service;

import com.zzb.ssm.domain.SysLog;

import java.util.List;

public interface ILogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();
}
