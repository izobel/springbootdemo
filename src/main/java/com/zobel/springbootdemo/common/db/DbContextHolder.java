package com.zobel.springbootdemo.common.db;

/**
 * @author zhaoZhongbo
 * @date 2019/3/3 20:47
 */
public class DbContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dbType) {
        if (dbType == null) throw new NullPointerException();
        contextHolder.set(dbType);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

}
