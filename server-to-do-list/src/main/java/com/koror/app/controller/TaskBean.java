package com.koror.app.controller;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ITaskService;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Priority;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Date;

@ManagedBean(name = "taskBean")
@SessionScoped
public class TaskBean {

    @Setter
    @Getter
    private String groupId;

    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private Task task;

    @Getter
    @Setter
    @ManagedProperty("#{taskService}")
    private ITaskService taskService;

    @Getter
    @Setter
    @ManagedProperty("#{groupService}")
    private IGroupService groupService;

    @Getter
    @Setter
    @ManagedProperty("#{userService}")
    private IUserService userService;

    @Getter
    @Setter
    private Date dateBegin;

    @Getter
    @Setter
    private Date dateEnd;

    public void init() {
        task = taskService.getById(id);
    }

    public void deleteTask(String id) {
        final Task task = taskService.getById(id);
        taskService.delete(task);
    }

    public void addTask(){
        final Group group = groupService.getById(groupId);
        final Task task = new Task("Test", Priority.MEDIUM_PRIORITY, group);
        final User user = userService.getById("e8bfcb40-666c-4004-9a33-fdf6da364067");
        task.setUser(user);
        task.setCreator("Lina");
        taskService.add(task);
    }

    public String save() {
        task.setDateBegin(dateBegin);
        task.setDateEnd(dateEnd);
        dateBegin = null;
        dateEnd = null;
        taskService.update(task);
        return "task-list";
    }

    public String back(){
        return "group-list";
    }

}
