package com.koror.app.service;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.api.service.IGroupService;
import com.koror.app.entity.*;
import com.koror.app.enumerated.Access;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.AssigneeGroupRepository;
import com.koror.app.repository.AssigneeTaskRepository;
import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class GroupService extends AbstractService<IGroupRepository, Group> implements IGroupService {

    private IAssigneeGroupRepository assigneeGroupRepository;

    private IAssigneeTaskRepository assigneeTaskRepository;

    private ITaskRepository taskRepository;

    public GroupService(IGroupRepository repository, IAssigneeGroupRepository assigneeGroupRepository, ITaskRepository taskRepository, IAssigneeTaskRepository assigneeTaskRepository, EntityManagerFactory entityManagerFactory) {
        this.repository = repository;
        this.assigneeGroupRepository = assigneeGroupRepository;
        this.taskRepository = taskRepository;
        this.assigneeTaskRepository = assigneeTaskRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(Group entity, User user) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        repository.add(entity, entityManager);
        final AssigneeGroup assigneeGroup = new AssigneeGroup(user, entity);
        assigneeGroupRepository.add(assigneeGroup, entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Group group, User user) {
        if (group == null) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //delete all task and assignee in project
        for (AssigneeTask assigneeTask : assigneeTaskRepository.getList(entityManager)) {
            Task taskTemp = taskRepository.getById(assigneeTask.getTask().getId(), entityManager);
            if (group.equals(taskTemp.getGroup())) {
                assigneeTaskRepository.delete(assigneeTask.getId(), entityManager);
                taskRepository.delete(taskTemp.getId(), entityManager);
            }
        }
        //delete project and assignee
        assigneeGroupRepository.deleteAssigneeByParam(user.getId(), group.getId(), entityManager);
        repository.delete(group.getId(), entityManager);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Group getById(String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Group group = repository.getById(id, entityManager);
        entityManager.close();
        return group;
    }

    @Override
    public List<Group> getList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Group> list = repository.getList(entityManager);
        entityManager.close();
        return list;
    }

    @Override
    public List<Group> getListGroupByUserId(User user) {
        if (user.getAccess() == Access.ADMIN_ACCESS) return getList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Group> groupList = repository.getListGroupByUserId(user.getId(), entityManager);
        entityManager.close();
        return groupList;
    }

}
