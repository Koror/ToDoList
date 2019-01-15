package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;

public class UserDeleteCommand extends AbstractCommand {
    @Override
    public void execute() {
//        List<User> userList = bootstrap.getUserService().getList();
//        System.out.println(userList);
//        System.out.println("Input index");
//        final int index = bootstrap.nextInt();
//        final User user = userList.get(index);
//        bootstrap.getUserService().delete(user.getId());
//        System.out.println("User delete");
    }

    @Override
    public String command() {
        return "DeleteUser";
    }

    @Override
    public String description() {
        return "Delete user";
    }
}
