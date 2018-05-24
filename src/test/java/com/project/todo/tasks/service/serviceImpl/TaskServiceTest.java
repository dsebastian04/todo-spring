package com.project.todo.tasks.service.serviceImpl;

import com.project.todo.tasks.document.Task;
import com.project.todo.tasks.document.User;
import com.project.todo.tasks.params.TaskState;
import com.project.todo.tasks.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTask() {
        final Task task = new Task("1","run the test",TaskState.Active,LocalDate.now(),null,new User("testU","test"));

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.createTask(task);

        assertNotNull(t);
        assertEquals("100", t.getId());
    }

    @Test
    public void shouldCheckStatusToBeFinish() {
        final Task task = new Task("1","run the test",TaskState.Active,LocalDate.now(),null,new User("testU","test"));
        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.switchStatus("1");

        assertNotNull(t);
        assertEquals(TaskState.Finish, t.getState());

    }
    @Test
    public void shouldCheckStatusToBeActive() {
        final Task task = new Task("2","run the test", TaskState.Finish, LocalDate.now(),null, new User("testU","test"));
        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.switchStatus("2");

        assertEquals(TaskState.Active, t.getState());

    }
}