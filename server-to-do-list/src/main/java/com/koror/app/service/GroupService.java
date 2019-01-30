package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.service.IGroupService;
import com.koror.app.entity.*;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GroupService extends AbstractService<IGroupRepository, Group> implements IGroupService {

    @Inject
    private IAssigneeGroupRepository assigneeGroupRepository;

    @Inject
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Inject
    private ITaskRepository taskRepository;

    @Override
    public void add(@Nullable Group entity,@Nullable User user) {
        if (entity == null || user == null) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        repository.add(entity);
        final AssigneeGroup assigneeGroup = new AssigneeGroup(user, entity);
        assigneeGroupRepository.add(assigneeGroup);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void delete(@Nullable Group group,@Nullable User user) {
        if (group == null || user == null) throw new WrongInputException("Wrong Input");
        entityManager.getTransaction().begin();
        //delete all task and assignee in project
        for (AssigneeTask assigneeTask : assigneeTaskRepository.getList()) {
            Task taskTemp = taskRepository.getById(assigneeTask.getTask().getId());
            if (group.equals(taskTemp.getGroup())) {
                assigneeTaskRepository.delete(assigneeTask.getId());
                taskRepository.delete(taskTemp.getId());
            }
        }
        //delete project and assignee
        assigneeGroupRepository.deleteAssigneeByParam(user.getId(), group.getId());
        repository.delete(group.getId());
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public Group getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        Group group = repository.getById(id);
        entityManager.clear();
        return group;
    }

    @Nullable
    @Override
    public List<Group> getList() {
        List<Group> list = repository.getList();
        entityManager.clear();
        return list;
    }

    @Override
    public List<Group> getListGroupByUserId(@Nullable User user) {
        if (user == null) throw new WrongInputException("Wrong Input");
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        List<Group> groupList = repository.getListGroupByUserId(user.getId());
        entityManager.clear();
        return groupList;
    }

}
