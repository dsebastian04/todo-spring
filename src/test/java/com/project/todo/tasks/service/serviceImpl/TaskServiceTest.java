package com.project.todo.tasks.service.serviceImpl;

import com.project.todo.tasks.Exception.TaskNotFoundException;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.when(taskRepository.insert(task)).thenReturn(task);

        Task t = taskService.createTask(task);

        assertNotNull(t);
        assertEquals(task, t);
    }

    @Test
    public void shouldCheckStatusToBeFinish() {
        final Task task = new Task("1", "run the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.switchStatus("1");

        assertNotNull(t);
        assertEquals(TaskState.Finish, t.getState());

    }

    @Test
    public void shouldCheckStatusToBeActive() {
        final Task task = new Task("2", "run the test", TaskState.Finish, LocalDate.now(), null, new User("testU", "test"));
        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.switchStatus("2");

        assertEquals(TaskState.Active, t.getState());

    }

    @Test
    public void shouldCheckStatusNullToBeActive() {
        final Task task = new Task("3", "run the test", null, LocalDate.now(), null, new User("testU", "test"));
        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.switchStatus("3");

        assertEquals(TaskState.Active, t.getState());

    }

    @Test(expected = TaskNotFoundException.class)
    public void switchStatus_whenTaskIsNull() {
        final Task task = null;
        Mockito.when(taskRepository.findOne("1")).thenReturn(task);

        Task t = taskService.switchStatus("1");


    }

    @Test
    public void modifyToDO_WhenIsSetTheTodo() {
        final Task task = new Task("4", "modify the test", TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.modifyToDO(task, "4");
        assertNotNull(task);
        assertEquals(LocalDate.now(), t.getDateModify());

    }

    @Test(expected = TaskNotFoundException.class)
    public void modifyToDO_WhenIsNullTodo() {
        final Task task = null;
        Mockito.when(taskRepository.findOne("1")).thenReturn(task);

        Task t = taskService.modifyToDO(task, "5");

    }

    @Test
    public void modifyToDOWhenIsNullTodo() {
        final Task task = new Task("5", null, TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task t = taskService.modifyToDO(task, "5");
        assertNotNull(task);
        assertEquals(LocalDate.now(), t.getDateModify());

    }

    @Test
    public void getAllTasks() {
        final Task task = new Task("6", null, TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final Task task2 = new Task("7", null, TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        final List lista = new ArrayList<Task>();

        lista.add(task);
        lista.add(task2);

        Mockito.when(taskRepository.findAll()).thenReturn(lista);

        List listaRetrieve = taskService.getAllTasks();

        assertNotNull(listaRetrieve);
        assertEquals(lista, listaRetrieve);
    }

    @Test
    public void findByIdTask() {
        final Task task = new Task("8", null, TaskState.Active, LocalDate.now(), null, new User("testU", "test"));

        Mockito.when(taskRepository.findOne(task.getId())).thenReturn(task);

        final Task taskRetrieve = taskService.findByIdTask(task.getId());

        assertNotNull(taskRetrieve);
        assertEquals(task, taskRetrieve);
    }

    @Test(expected = TaskNotFoundException.class)
    public void findByIdTask_whenTaskIsNull() {
        final Task task = null;

        Mockito.when(taskRepository.findOne("8")).thenReturn(task);

        final Task taskRetrieve = taskService.findByIdTask("8");

    }

    @Test
    public void findByUserNickname() {
        final Task task = new Task("9", null, TaskState.Active, LocalDate.now(), null, new User("testU", "test"));
        final List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        Mockito.when(taskRepository.findByUser_Nickname(task.getUser().getNickname())).thenReturn(tasks);

        final List<Task> taskRetrieve = taskService.findByUserNickname(task.getUser().getNickname());

        assertNotNull(taskRetrieve);
        assertEquals(tasks, taskRetrieve);
    }

    @Test(expected = TaskNotFoundException.class)
    public void findByUserNickname_whenNotFoundTasks() {

        final List<Task> tasks = new ArrayList<>();

        Mockito.when(taskRepository.findByUser_Nickname("user")).thenReturn(tasks);

        final List<Task> taskRetrieve = taskService.findByUserNickname("user");


    }

    @Test
    public void deleteTaskById() {
        final Task task = new Task("10", null, TaskState.Active, LocalDate.now(), null, new User("testU", "test"));


        Mockito.doNothing().when(taskRepository).delete(Mockito.isA(String.class));

        taskService.deleteTaskById(task.getId());

        Mockito.verify(taskRepository, Mockito.times(1)).delete(task.getId());
    }

    @Test
    public void updateTask() {
        final Task task = new Task("11", null, TaskState.Active, LocalDate.now(), null, new User("testU", "test"));


        Mockito.when(taskRepository.save(task)).thenReturn(task);

        final Task taskRetrieve = taskService.updateTask(task);

        assertNotNull(taskRetrieve);
        assertEquals(task, taskRetrieve);
    }
}