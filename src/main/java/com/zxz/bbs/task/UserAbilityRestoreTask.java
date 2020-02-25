package com.zxz.bbs.task;

//对用户恢复权限进程
public class UserAbilityRestoreTask implements Task {
    @Override
    public void execute() throws Exception {

        TokenUtil.tokenUtil.messageMapper.userAbilityRestore();

    }
}
