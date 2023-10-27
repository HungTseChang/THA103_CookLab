package com.cooklab.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器（可选）
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 设置请求字符编码
    	req.setCharacterEncoding("UTF-8");

        // 设置响应Content-Type头
        res.setContentType("text/html; charset=UTF-8");

        // 继续处理请求
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // 销毁过滤器（可选）
    }
}

