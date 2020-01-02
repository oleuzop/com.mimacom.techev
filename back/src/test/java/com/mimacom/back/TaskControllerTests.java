package com.mimacom.back;

import com.mimacom.back.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TaskControllerTests {

	@MockBean
	TaskRepository taskRepository;

	@Test
	void getAll() {
	}

	@Test
	void getById() {
	}

/*
    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Test");
        employee.setSalary(1000);

        Mockito.when(repository.save(employee)).thenReturn(Mono.just(employee));

        webClient.post()
            .uri("/create")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(employee))
            .exchange()
            .expectStatus().isCreated();

        Mockito.verify(repository, times(1)).save(employee);
    }
*/


}
