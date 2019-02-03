package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.service.IGroupService;
import com.koror.app.entity.*;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@ApplicationScoped
public class GroupService extends AbstractService<IGroupRepository, Group> implements IGroupService {

    @Inject
    private IAssigneeGroupRepository assigneeGroupRepository;

    @Inject
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Inject
    private ITaskRepository taskRepository;

    @Override
    public void add(@Nullable Group entity, @Nullable User user) {
        if (entity == null || user == null) throw new WrongInputException("Wrong Input");
        repository.save(entity);
        final AssigneeGroup assigneeGroup = new AssigneeGroup(user, entity);
        assigneeGroupRepository.save(assigneeGroup);
    }

    @Override
    public void delete(@Nullable Group group, @Nullable User user) {
        if (group == null || user == null) throw new WrongInputException("Wrong Input");
        for (AssigneeTask assigneeTask : assigneeTaskRepository.findAll()) {
            if(assigneeTask.getTask() == null) throw new WrongInputException("Wrong Input");
            Task taskTemp = taskRepository.findBy(assigneeTask.getTask().getId());
            if (group.equals(taskTemp.getGroup())) {
                taskRepository.remove(taskTemp);
            }
        }
        repository.remove(group);
    }

    public Group getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.findBy(id);
    }

    public void delete(@Nullable Group entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.remove(entity);
    }

    @Override
    public List<Group> getListGroupByUserId(@Nullable User user) {
        if (user == null) throw new WrongInputException("Wrong Input");
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        try {
            return repository.getListGroupByUserId(user.getId());
        } catch (
                NoResultException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
