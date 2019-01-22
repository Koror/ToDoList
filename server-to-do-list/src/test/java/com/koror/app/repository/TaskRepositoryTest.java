package com.koror.app.repository;

import com.koror.app.entity.Task;
import com.koror.app.util.DatabaseConnection;
import com.koror.app.util.HibernateFactory;
import org.junit.Test;

public class TaskRepositoryTest {

    @Test
    public void testPositiveAddTask() throws ReflectiveOperationException {
        DatabaseConnection.setConnection();
        HibernateFactory.buildFactory();
        TaskRepository taskRepository = new TaskRepository();
        Task task = new Task();
        task.setName("TestTask");
        //taskRepository.add(task);
        taskRepository.delete("2c9200bb6876172a016876172cab0000");
        DatabaseConnection.closeConnection();
    }
//
//    @Test
//    public void testNegativeAddTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        for(Task task : taskRepository.getList()){
//            if(task.getName().equals("test task"))
//                fail();
//        }
//        DatabaseConnection.closeConnection();
//    }

//    @Test
//    public void testPositiveCompleteTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.add(task);
//        taskRepository.getById(task.getId()).setComplete(true);
//        assertTrue(taskRepository.getById(task.getId()).isComplete());
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testNegativeCompleteTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.add(task);
//        assertFalse(taskRepository.getById(task.getId()).isComplete());
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testPositiveDeleteTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.add(task);
//        taskRepository.delete(task.getId());
//        assertTrue(taskRepository.getList().isEmpty());
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testNegativeDeleteTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.add(task);
//        taskRepository.delete(task.getId());
//        task = taskRepository.getById(task.getId());
//        task.getId();
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testPositiveUpdateTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.add(task);
//        final String id = task.getId();
//        task = new Task("new test task");
//        taskRepository.update(task);
//        assertFalse(id.equals(taskRepository.getById(task.getId()).getId()));
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testPositiveClearTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.add(task);
//        taskRepository.getById(task.getId()).setComplete(true);
//        taskRepository.clearTask(taskRepository.getList());
//        assertTrue(taskRepository.getList().isEmpty());
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testNegativeClearTask() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.add(task);
//        taskRepository.getById(task.getId()).setComplete(true);
//        taskRepository.clearTask(taskRepository.getList());
//        task = taskRepository.getById(task.getId());
//        task.getId();
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testPositiveSetGroupId() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.add(task);
//        final Group group = new Group("test group");
//        final String groupId = group.getId();
//        task.setGroupId(group.getId());
//        taskRepository.add(task);
//        task = taskRepository.getById(task.getId());
//        assertEquals(groupId, task.getGroupId());
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testNegativeSetGroupId() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        Task task = new Task("test task");
//        taskRepository.add(task);
//        taskRepository.add(task);
//        task = taskRepository.getById(task.getId());
//        assertNull(task.getGroupId());
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testPositiveGetTaskList() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        final Task task = new Task("test task");
//        taskRepository.add(task);
//        assertFalse(taskRepository.getList().isEmpty());
//        DatabaseConnection.closeConnection();
//    }
//
//    @Test
//    public void testNegativeGetTaskList() {
//        DatabaseConnection.setConnection();
//        final TaskRepository taskRepository = new TaskRepository();
//        assertTrue(taskRepository.getList().isEmpty());
//        DatabaseConnection.closeConnection();
//    }

}