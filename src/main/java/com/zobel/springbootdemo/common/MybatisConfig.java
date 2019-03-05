package com.zobel.springbootdemo.common;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zobel.springbootdemo.common.db.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhaoZhongbo
 * @date 2019/3/3 21:07
 */
@Configuration
@MapperScan("com.zobel.springbootdemo.mapper")
public class MybatisConfig {

    public static final String MASTERDATASOURCE= "master";

    @Autowired
    private Environment env;

    @Bean
    public DataSource masterDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        props.put("url", env.getProperty("spring.datasource.url"));
        props.put("username", env.getProperty("spring.datasource.username"));
        props.put("password", env.getProperty("spring.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(MASTERDATASOURCE, masterDataSource);

        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource DynamicDataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(DynamicDataSource);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
//        fb.setMapperLocations(
//        new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));

        return fb.getObject();
    }
}
