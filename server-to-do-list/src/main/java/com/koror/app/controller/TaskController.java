package com.koror.app.controller;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.ITaskService;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Priority;
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
public class TaskController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private ITaskService taskService;

    @PostMapping("link-add-task")
    public String linkAddTask(@RequestParam("groupId") String idGroup, ModelMap modelMap){
        modelMap.addAttribute("groupId",idGroup);
        modelMap.addAttribute("task", new Task());
        return "task-add";
    }

    @PostMapping("link-to-group")
    public String groupView(ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie){
        User user = userService.getById(cookie);
        model.addAttribute("username", user.getName());
        List<Group> listGroup = groupService.getListGroupByUser(user);
        model.addAttribute("groupList", listGroup);
        return "group-list";
    }

    @PostMapping(value = "add-task")
    public String addTask(@RequestParam("groupId") String groupId,@ModelAttribute("task") Task task, ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie) {
        User user = userService.getById(cookie);
        task.setUser(user);
        task.setCreator(user.getName());
        task.setPriority(Priority.MEDIUM_PRIORITY);
        Group group = groupService.getById(groupId);
        task.setGroup(group);
        taskService.add(task);
        model.addAttribute("username", user.getName());
        model.addAttribute("taskList", taskService.getListTaskByGroupId(groupId));
        model.addAttribute("groupId", groupId);
        model.addAttribute("groupName", group.getName());
        return "task-list";
    }

    @PostMapping(value = "delete-task")
    public String deleteTask(@RequestParam("groupId") String groupId, @RequestParam("taskId") String taskId, ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie) {
        final Task task = taskService.getById(taskId);
        taskService.delete(task);
        User user = userService.getById(cookie);
        Group group = groupService.getById(groupId);
        model.addAttribute("groupId", groupId);
        model.addAttribute("username", user.getName());
        model.addAttribute("taskList", taskService.getListTaskByGroupId(groupId));
        model.addAttribute("groupName", group.getName());
        return "task-list";
    }

    @PostMapping("link-edit-task")
    public String linkEditGroup(@RequestParam("groupId") String groupId,@RequestParam("taskId") String taskId, ModelMap model){
        Task task = taskService.getById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("groupId", groupId);
        return "task-edit";
    }

    @PostMapping("edit-task")
    public String editGroup(@RequestParam("groupId") String groupId, @ModelAttribute("task") Task task,ModelMap model, @CookieValue(value = "TDLUI", defaultValue = "123") String cookie) {
        User user = userService.getById(cookie);
        Task proxyTask = taskService.getById(task.getId());
        proxyTask.setName(task.getName());
        taskService.update(proxyTask);
        model.addAttribute("groupId", groupId);
        model.addAttribute("username", user.getName());
        model.addAttribute("taskList", taskService.getListTaskByGroupId(groupId));
        model.addAttribute("groupName", groupService.getById(groupId).getName());
        return "task-list";
    }

}
