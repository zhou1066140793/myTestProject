package com.zzb.ssm.service.Impl;

import com.zzb.ssm.dao.ISysLogDao;
import com.zzb.ssm.domain.SysLog;
import com.zzb.ssm.service.ILogService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ILogServiceImpl implements ILogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
