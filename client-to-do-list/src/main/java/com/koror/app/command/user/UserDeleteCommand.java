package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.UserDTO;

import java.util.List;

public class UserDeleteCommand extends AbstractCommand {
    @Override
    public void execute() {
        final List<UserDTO> userList = bootstrap.getUserEndpoint().getUserList(bootstrap.getSession());
        int index = 0;
        for (UserDTO user : userList) {
            System.out.println(index + " " + user.getLogin());
            index++;
        }
        System.out.println("Input index");
        final int indexUser = bootstrap.nextInt();
        final UserDTO user = userList.get(indexUser);
        Result result = bootstrap.getUserEndpoint().deleteUser(user, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("User delete");
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
