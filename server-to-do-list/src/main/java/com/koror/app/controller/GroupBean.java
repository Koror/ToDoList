package com.koror.app.controller;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.IUserService;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.Date;

@ManagedBean(name = "groupBean")
@SessionScoped
public class GroupBean {

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
    private String inputText;

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Group group = new Group();

    @Getter
    @Setter
    private Date dateBegin;

    @Getter
    @Setter
    private Date dateEnd;

    public void init() {
        group = groupService.getById(id);
    }

    public String testText() {
        return groupService.getById("169543b5-eda2-44fc-91dd-6d575e8203b5").toString();
    }

    public void addGroup() {
        final Group group = new Group("Test");
        final User user = userService.getById("e8bfcb40-666c-4004-9a33-fdf6da364067");
        group.setUser(user);
        group.setCreator("Lina");
        groupService.add(group);
    }

    public void deleteGroup(String id) {
        final Group group = groupService.getById(id);
        groupService.delete(group);
    }

    public String save() {
        group.setDateBegin(dateBegin);
        group.setDateEnd(dateEnd);
        dateBegin = null;
        dateEnd = null;
        groupService.update(group);
        return "group-list";
    }

}
