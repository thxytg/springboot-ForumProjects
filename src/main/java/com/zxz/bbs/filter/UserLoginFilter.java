package com.zxz.bbs.filter;

import com.alibaba.fastjson.JSONObject;
import com.zxz.bbs.pojo.User;
import org.apache.catalina.Session;
import org.apache.catalina.connector.Request;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

/*
过滤未登录的用户，重定向到登录界面
 */

@WebFilter("/u/*")

public class UserLoginFilter  implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("uu");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        if(true)
        {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user==null)
            {
                //用户尚未登录
                if(uri.endsWith(".do"))
                {
                    //REST请求：返回-100错误
                    JSONObject json= new JSONObject(true);
                    json.put("error",-100);
                    json.put("reason","尚未登录");
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    response.getWriter().write(json.toString());
                    return;

                }
                else {
                    // ?号后面的部分也得带上
                    String query = request.getQueryString();
                    if(query!=null && query.length()>0)
                        uri += "?" + query;

                    //MVC请求： 返回302重定向
                    response.sendRedirect(request.getContextPath()
                            + "/login"
                            + "?returnUrl=" + uri
                    );
                    return;

                }
            }
        }
        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {

    }
}
