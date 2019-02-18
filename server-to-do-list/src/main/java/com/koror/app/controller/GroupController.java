package com.koror.app.controller;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.ITaskService;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private ITaskService taskService;

    @PostMapping(value = "add-group")
    public String addGroup(@ModelAttribute("group") Group group, ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie) {
        User user = userService.getById(cookie);
        group.setUser(user);
        group.setCreator(user.getName());
        groupService.add(group);
        model.addAttribute("username", user.getName());
        List<Group> listGroup = groupService.getListGroupByUser(user);
        model.addAttribute("groupList", listGroup);
        return "group-list";
    }

    @PostMapping(value = "delete-group")
    public String deleteGroup(@RequestParam("groupid") String idGroup, ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie) {
        final Group group = groupService.getById(idGroup);
        groupService.delete(group);
        User user = userService.getById(cookie);
        model.addAttribute("username", user.getName());
        List<Group> listGroup = groupService.getListGroupByUser(user);
        model.addAttribute("groupList", listGroup);
        return "group-list";
    }

    @PostMapping("edit-group")
    public String editGroup( @ModelAttribute("group") Group group,ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie) {
        User user = userService.getById(cookie);
        Group proxyGroup = groupService.getById(group.getId());
        proxyGroup.setName(group.getName());
        groupService.update(proxyGroup);
        model.addAttribute("username", user.getName());
        List<Group> listGroup = groupService.getListGroupByUser(user);
        model.addAttribute("groupList", listGroup);
        return "group-list";
    }

    @PostMapping("link-add-group")
    public ModelAndView linkAddGroup(){
        return new ModelAndView( "group-add","group",new Group());
    }

    @PostMapping("link-edit-group")
    public ModelAndView linkEditGroup(@RequestParam("groupId") String idGroup, ModelMap model){
        Group group = groupService.getById(idGroup);
        return new ModelAndView( "group-edit","group",group);
    }

    @PostMapping("link-to-task")
    public String linkToTask(@RequestParam("groupId") String idGroup,ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie){
        User user = userService.getById(cookie);
        model.addAttribute("username", user.getName());
        model.addAttribute("taskList", taskService.getListTaskByGroupId(idGroup));
        model.addAttribute("groupId", idGroup);
        model.addAttribute("groupName", groupService.getById(idGroup).getName());
        return "task-list";
    }

}
