package com.example.clouddisk.common;

import com.example.clouddisk.entity.Op;
import com.example.clouddisk.service.OpService;
import com.example.clouddisk.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author:faryhao
 * @create: 2023-05-07 13:24
 * @Description: 日志
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Autowired
    public OpService opService;

    private static final Op OPLOG=new Op();


    //这个方法会拦截controller项目包所有类的所有方法
    @Pointcut("execution(public * com.example.clouddisk.controller.*.*(..))")
    public void webLog() {
    }

    /**
     *使用AOP的前置通知拦截请求参数信息
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        //  接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
            //log.info(String.valueOf(request)+"aa");
        }
        //  记录下请求内容
        long visitTime=System.currentTimeMillis();
        Date date = new Date(visitTime);

        OPLOG.setOpdate(date);
      //  OPLOG.setOprole(request.getParameter());
        String ipAddress = null;
        if (request != null) {
            ipAddress = IpUtils.getIPAddress(request);
//            log.info("ip:"+ipAddress);
//            log.info("请求时间："+ date);
//            log.info("URL : " + request.getRequestURL().toString());
           // OPLOG.setUrl(request.getRequestURL().toString());
//            log.info("HTTP_METHOD : " + request.getMethod());
            OPLOG.setOpmethod(request.getMethod());
//            log.info("IP : " + request.getRemoteAddr());
           // OPLOG.setoprequest.getRemoteAddr());
            OPLOG.setOpip(ipAddress);

            Enumeration<String> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String name = enu.nextElement();
//                log.info("name:{},value:{}", name, request.getParameter(name));
            }

        }
    }

    /**
     *使用AOP的后置通知
     */

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        //  处理完请求，返回内容
        log.info("RESPONSE : " + ret);
        String res=null;
        if (ret==null){
            return;
        }
        if(ret.toString().length()>50){
            res=ret.toString().substring(0,50)+"...}";
        }
        OPLOG.setOpres(res);
        opService.save(OPLOG);
    }
}
