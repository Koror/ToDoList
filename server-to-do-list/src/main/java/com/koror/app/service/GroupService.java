package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.service.IGroupService;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
public class GroupService implements IGroupService {

    @Autowired
    private IAssigneeGroupRepository assigneeGroupRepository;

    @Autowired
    private IAssigneeTaskRepository assigneeTaskRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IGroupRepository iGroupRepository;

    @Override
    public void add(@Nullable final Group entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iGroupRepository.save(entity);
    }

    @Override
    public void update(@Nullable final Group entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        iGroupRepository.save(entity);
    }

    @Override
    public List<Group> getList() {
        return iGroupRepository.findAll();
    }

    @Override
    public void add(@Nullable Group entity, @Nullable User user) {
        if (entity == null || user == null) throw new WrongInputException("Wrong Input");
        iGroupRepository.save(entity);
        final AssigneeGroup assigneeGroup = new AssigneeGroup(user, entity);
        assigneeGroupRepository.save(assigneeGroup);
    }

    @Override
    public Group getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return iGroupRepository.findById(id).get();
    }

    @Override
    public void delete(@Nullable Group entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iGroupRepository.delete(entity);
    }

    @Override
    public List<Group> getListGroupByUser(@Nullable User user) {
        if (user == null) throw new WrongInputException("Wrong Input");
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        try {
            return iGroupRepository.getListGroupByUserId(user.getId());
        } catch (
                NoResultException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
