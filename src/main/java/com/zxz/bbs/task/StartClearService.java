package com.zxz.bbs.task;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/*
这个类可以在项目启动的时候附加启动某些的方法，暂时未使用
 */
@Component
public class StartClearService implements ApplicationRunner {
//    DailyTaskManager daily = new DailyTaskManager();

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("开始清理任务");
//        daily.addTask(new MsgDelTask(), "清理message表");
//        daily.addTask(new UserAbilityRestoreTask(),"恢复用户权限");
//
//
//        daily.doStart();
    }
}
