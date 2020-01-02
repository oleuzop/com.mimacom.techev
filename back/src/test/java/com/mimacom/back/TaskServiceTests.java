package com.mimacom.back;

import com.mimacom.back.model.Task;
import com.mimacom.back.repository.TaskRepository;
import com.mimacom.back.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TaskServiceTests {

	@MockBean
	TaskRepository taskRepository;

	@Autowired
	TaskService taskService;

	private void mock_repo_several_tasks(){
		List<Task> returnList = new ArrayList<>();

		Task task1 = new Task();
		task1.setId(1L);
		task1.setTitle("Test 1");
		task1.setDescription("Test task 1");
		task1.setFinished(false);
		returnList.add(task1);

		Task task2 = new Task();
		task2.setId(2L);
		task2.setTitle("Test 2");
		task2.setDescription("Test task 2");
		task2.setFinished(false);
		returnList.add(task2);

		Mockito.when(taskRepository.findAll()).thenReturn(returnList);
	}

	private void mock_repo_one_task(){
		Task task = new Task();
		task.setId(1L);
		task.setTitle("Test");
		task.setDescription("Test task");
		task.setFinished(false);

		Mockito.when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(task));
		Mockito.when(taskRepository.save(task)).thenReturn(task);
	}

	@Test
	void test_findAll_ok() {
		mock_repo_several_tasks();

		List<Task> tasks = taskService.findAll();

		assertEquals(2, tasks.size());
		Mockito.verify(taskRepository, times(1)).findAll();
	}

	@Test
	void test_findAll_ko() {
		List<Task> tasks = taskService.findAll();

		assertEquals(0, tasks.size());
		Mockito.verify(taskRepository, times(1)).findAll();
	}

	@Test
	void test_findById_ok() {
		mock_repo_one_task();

		taskService.findById(1L);

		Mockito.verify(taskRepository, times(1)).findById(1L);
	}

	@Test
	void test_findById_ko() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> taskService.findById(1234567890L));
	}

	@Test
	void test_find_update_ok() {
		mock_repo_one_task();

		Task task = taskService.findById(1L);
		task.setFinished(true);

		taskService.update(1L, task );

		Task updatedTask = taskService.findById(1L);

		assertEquals(updatedTask.getFinished(), true);

		Mockito.verify(taskRepository, times(1)).save(task);
		Mockito.verify(taskRepository, times(3)).findById(1L);
	}

	@Test
	void test_delete_ok() {
		mock_repo_one_task();
		Task task = taskService.delete(1L);
		Mockito.verify(taskRepository, times(1)).delete(task);
	}

}
