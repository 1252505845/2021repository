package com.atguigu.aspect;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/15 0015 - 9:02
 * @Description: com.atguigu.aspect
 * @version: 1.0
 */


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 控制器切面
 *
 * @author lichuang
 */


@Component
@Aspect
public class ControllerAspect {

    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        map.put("name",12);
        map.put("age",12);
        System.out.println(map.toString());
    }

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

   

    /**
     * 调用controller包下的任意类的任意方法时均会调用此方法
     */
    @Around("execution(* com.atguigu.controller.*.*(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        Method[] methods = joinPoint.getClass().getMethods();
        Class<?> clazz = joinPoint.getTarget().getClass();

        logger.info("calss-----"+clazz.getName());
        logger.info("method----"+joinPoint.getSignature().getName());


        logger.info("methods--"+methods);
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        //得到其方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
        Class[] paramTypeArray = methodSignature.getParameterTypes();

        logger.info("请求参数为{}", Arrays.toString(args));
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = joinPoint.proceed(args);
        logger.info("响应结果为{}",result);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }

//    @Pointcut("execution(* com.atguigu.controller.*.*(..))")
//    public void pointCut2() {}
//
//    @Around("pointCut2()")
//    public Object run2(ProceedingJoinPoint joinPoint) throws Throwable {
//        //获取方法参数值数组
//        Object[] args = joinPoint.getArgs();
//        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        //获取方法参数类型数组
//        Class[] paramTypeArray = methodSignature.getParameterTypes();
//
//        logger.info("请求参数为{}",args);
//        //动态修改其参数
//        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
//        Object result = joinPoint.proceed(args);
//        logger.info("响应结果为{}",result);
//        //如果这里不返回result，则目标对象实际返回值会被置为null
//        return result;
//    }
}
