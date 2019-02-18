package com.koror.app.controller;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import com.koror.app.util.UserIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IGroupService groupService;

    @GetMapping(value = "/")
    public String startWindow(ModelMap model) {
        return "index";
    }

    @PostMapping(value = "login")
    public String loginUser(@RequestParam("userlogin") String userlogin, @RequestParam("password") String password, ModelMap model, HttpServletResponse response) {
        User user = userService.login(userlogin, password);
        sessionService.login(user, UserIp.getIp());
        model.addAttribute("username", user.getName());
        List<Group> listGroup = groupService.getListGroupByUser(user);
        model.addAttribute("groupList", listGroup);
        response.addCookie(new Cookie("TDLUI", user.getId()));
        return "group-list";
    }

    @PostMapping(value = "logout")
    public String logoutUser(ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String userIdCookie, HttpServletResponse response) {
        sessionService.deleteByUserId(userIdCookie);
        response.addCookie(new Cookie("TDLUI", null));
        return "index";
    }

}
