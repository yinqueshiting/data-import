package com.example.dataimport.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

@Slf4j
@WebFilter(urlPatterns = "/*",filterName = "urlFilter")
public class UrlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        log.info("【url】:{}",url);
        log.info("服务端地址IP：{}",InetAddress.getLocalHost().getHostAddress());
        log.info("服务端端口号：{}",request.getLocalPort());
        //暂不打印body里的数据，需要requestWrapper
        // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
