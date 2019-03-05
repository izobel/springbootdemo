package com.zobel.springbootdemo.interceptor;

import com.zobel.springbootdemo.common.MybatisConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String dataSourceKey = request.getParameter("dataSource");
        if(StringUtils.isEmpty(dataSourceKey)){
            request.setAttribute("dataSourceKey", MybatisConfig.MASTERDATASOURCE);
        }else{
            request.setAttribute("dataSourceKey", dataSourceKey);
        }



        String token = request.getHeader("token");
        if(token == null){
            token = request.getParameter("token");
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("自定义登陆拦截器----->postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("自定义登陆拦截器----->afterHandle");

    }
}
