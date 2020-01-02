package com.mimacom.back;

import com.mimacom.back.controller.TaskController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootApplicationTests {

	@Autowired
	private TaskController taskController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(taskController);
	}

}
