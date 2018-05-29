package com.project.todo.tasks.rest.restImpl;

import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.document.User;
import com.project.todo.tasks.params.TaskState;
import com.project.todo.tasks.service.serviceImpl.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TaskRestTest {

    @InjectMocks
    private TaskRest taskRest;

    @Mock
    private TaskService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTask() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.when(taskRest.createTask(task)).thenReturn(task);
        Task taskRetrieve = taskRest.createTask(task);

        assertNotNull(taskRetrieve);
        assertEquals(task, taskRetrieve);
    }

    @Test
    public void switchStatus() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.when(taskRest.switchStatus(task.getId())).thenReturn(task);
        Task taskRetrieve = taskRest.switchStatus(task.getId());

        assertNotNull(taskRetrieve);
        assertEquals(task, taskRetrieve);
    }

    @Test
    public void modifyToDO() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.when(taskRest.modifyToDO(task, task.getId())).thenReturn(task);
        Task taskRetrieve = taskRest.modifyToDO(task, task.getId());

        assertNotNull(taskRetrieve);
        assertEquals(task, taskRetrieve);
    }

    @Test
    public void getAllTasks() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final Task taskTwo = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        List tasks = new ArrayList<Task>();

        tasks.add(task);
        tasks.add(taskTwo);

        Mockito.when(taskRest.getAllTasks()).thenReturn(tasks);
        final List tasksRetrieve = taskRest.getAllTasks();

        assertNotNull(tasks);
        assertEquals(tasks, tasksRetrieve);
    }

    @Test
    public void findByIdTask() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.when(taskRest.findByIdTask(task.getId())).thenReturn(task);
        Task taskRetrieve = taskRest.findByIdTask(task.getId());

        assertNotNull(taskRetrieve);
        assertEquals(task.getId(), taskRetrieve.getId());
    }

    @Test
    public void findByUserNickname() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        Mockito.when(taskRest.findByUserNickname(task.getUser().getNickname())).thenReturn(tasks);
        List<Task> taskRetrieve = taskRest.findByUserNickname(task.getUser().getNickname());

        assertNotNull(taskRetrieve);
        assertEquals(tasks, taskRetrieve);
    }

    @Test
    public void deleteTaskById() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.doNothing().when(service).deleteTaskById(Mockito.isA(String.class));

        taskRest.deleteTaskById(task.getId());

        Mockito.verify(service, Mockito.times(1)).deleteTaskById(task.getId());
    }

    @Test
    public void updateTask() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.when(taskRest.updateTask(task, task.getId())).thenReturn(task);
        Task taskRetrieve = taskRest.updateTask(task, task.getId());

        assertNotNull(taskRetrieve);
        assertEquals(task, taskRetrieve);
    }
}