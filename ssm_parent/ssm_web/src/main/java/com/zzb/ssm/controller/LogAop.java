package com.zzb.ssm.controller;

import com.zzb.ssm.domain.SysLog;
import com.zzb.ssm.service.ILogService;
import com.zzb.ssm.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ILogService logService;

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Before("execution(* com.zzb.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();

//        获取无参的方法
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
//            获取有参的方法
            Class[] classes = new Class[args.length];
            for (int i = 0; i < classes.length; i++) {
                classes[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classes);
        }
    }

    @After("execution(* com.zzb.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws ParseException {
        SysLog sysLog = new SysLog();

//        获取时间
        sysLog.setExecutionTime(new Date().getTime() - visitTime.getTime());

//        获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
//        1.获取类的mapping
            RequestMapping classMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
//        2.获取方法的mapping
            if (classMapping != null) {
                RequestMapping methodMappning = method.getAnnotation(RequestMapping.class);
                if (methodMappning != null) {
                    sysLog.setUrl(classMapping.value()[0] + methodMappning.value()[0]);
                }
            }
        }

//        获取用户
//        1.获取context
        SecurityContext context = SecurityContextHolder.getContext();
//        2.从上下文获得信息
        User user = (User) context.getAuthentication().getPrincipal();
        sysLog.setUsername(user.getUsername());

//        获得ip
        sysLog.setIp(request.getLocalAddr());
        String s = DateUtils.date2String(visitTime, "yyyy-mm-dd HH:mm:ss");
        visitTime = DateUtils.string2Date(s, "yyyy-mm-dd HH:mm:ss");
        sysLog.setVisitTime(visitTime);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
//visitTime,username,ip,url,executionTime,method
        logService.save(sysLog);
    }
}
