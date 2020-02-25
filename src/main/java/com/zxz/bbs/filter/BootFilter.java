package com.zxz.bbs.filter;

import com.zxz.bbs.task.DailyTaskManager;
import com.zxz.bbs.task.MsgDelTask;
import com.zxz.bbs.task.UserAbilityRestoreTask;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import java.io.IOException;
/*
用filter来初始化工作线程；
 */

@WebFilter("")
public class BootFilter implements Filter {
    DailyTaskManager daily = new DailyTaskManager();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("开始清理任务");
        daily.addTask(new MsgDelTask(), "清理message表");
        daily.addTask(new UserAbilityRestoreTask(),"恢复用户权限");
        daily.doStart();

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        daily.doQuit();
    }
}
