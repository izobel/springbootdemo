package com.zobel.springbootdemo.common.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.zobel.springbootdemo.common.MybatisConfig;
import com.zobel.springbootdemo.common.db.DbContextHolder;
import com.zobel.springbootdemo.common.db.DynamicDataSource;
import com.zobel.springbootdemo.common.db.annotation.SwitchDataSource;
import com.zobel.springbootdemo.domain.DataSourceDomain;
import com.zobel.springbootdemo.service.DataSourceService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * 多数据源切换
 * @author zhaoZhongbo
 * @date 2019/3/3 22:58
 */
@Aspect
@Component
public class DataSourceAOP {

    private static final Logger log = LoggerFactory.getLogger(DataSourceAOP.class);

    @Autowired
    private DataSourceService dataSourceService;

    public static long startTime;
    public static long endTime;

    /*@PointCut注解表示表示横切点，哪些方法需要被横切*/
    /*切点表达式*/
    @Pointcut("execution( * com.zobel.springbootdemo.service.*.*(..))")
    /*切点签名*/
    public void print() {

    }

    /*@Before注解表示在具体的方法之前执行*/
    @Before("print()")
    public void before(JoinPoint joinPoint) {
        //先判断当前方法是否有主数据源的标签，若有则将数据源切换为主库
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);
        if(annotation != null){
            DbContextHolder.setDataSource(annotation.value()[0]);
            return;
        }
        //根据请求传递的dataSource标识，来判断是否切换数据源
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String dataSourcekey = (String)request.getAttribute("dataSourceKey");
        if (!MybatisConfig.MASTERDATASOURCE.equals(dataSourcekey)) {
            //判断当前库中是否有该数据源
            DynamicDataSource dataSource = DynamicDataSource.getInstance();
            Map<Object, Object> dataSourceMap = dataSource.getDataSourceMap();
            if (dataSourceMap.get(dataSourcekey) == null) {
                //加载数据源
                appendDataSource(dataSourceMap,dataSourcekey);
            }
            DbContextHolder.setDataSource(dataSourcekey);
        } else {
            DbContextHolder.setDataSource(MybatisConfig.MASTERDATASOURCE);
        }
        log.info("前置切面before……");
        log.info("选择数据源：" + dataSourcekey);
    }

    /*@After注解表示在方法执行之后执行*/
    @After("print()")
    public void after() {
        endTime = System.currentTimeMillis() - startTime;
        log.info("后置切面after……");
    }

    /*@AfterReturning注解用于获取方法的返回值*/
    @AfterReturning(pointcut = "print()", returning = "object")
    public void getAfterReturn(Object object) {
        log.info("本次接口耗时={}ms", endTime);
        log.info("afterReturning={}", object.toString());
    }

    public void appendDataSource(Map dataSourceMap,String dataSourcekey){
        DataSourceDomain dataSource = dataSourceService.query((long) 1);
        DruidDataSource dynamicDataSource = new DruidDataSource();
        dynamicDataSource.setDriverClassName(dataSource.getDriver());
        dynamicDataSource.setUrl(dataSource.getUrl());
        dynamicDataSource.setUsername(dataSource.getUsername());
        dynamicDataSource.setPassword(dataSource.getPassword());
        /**
         * 创建动态数据源
         */
        dataSourceMap.put(dataSourcekey, dynamicDataSource);
        DynamicDataSource.getInstance().setTargetDataSources(dataSourceMap);
    }
}
