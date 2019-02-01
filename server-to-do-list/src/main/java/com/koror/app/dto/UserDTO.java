package com.koror.app.dto;

import com.koror.app.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String name;

    private String email;

    private String login;

    public UserDTO(@Nullable User user){
        if (user == null) return;
        name = user.getName();
        email = user.getEmail();
        login = user.getLogin();
    }

}
