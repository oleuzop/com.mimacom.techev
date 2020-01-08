package com.mimacom.back;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mimacom.back.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTests {

	@Autowired
	private MockMvc mockMvc;

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void test_get_all_ok() throws Exception {
		this.mockMvc.perform(get("/task")).andExpect(status().isOk());
	}

	@Test
	void test_get_id_200() throws Exception {
		this.mockMvc.perform(get("/task/100")).andExpect(status().isOk());
	}

	@Test
	void test_get_id_401() throws Exception {
		this.mockMvc.perform(get("/task/1234567890")).andExpect(status().is4xxClientError());
	}

	@Test
	void test_get_paginated_ok() throws Exception {
		this.mockMvc.perform(get("/task?page=0&size=1"))
				.andExpect(status().isOk())
				.andExpect(content().string("[{\"id\":100,\"finished\":false,\"title\":" +
						"\"Test\",\"description\":\"Test task\"}]"));
	}

	@Test
	void test_create_200() throws Exception {

		Task task = new Task();
		task.setTitle("Test");
		task.setDescription("Test task");
		task.setFinished(false);

		mockMvc.perform(MockMvcRequestBuilders.post("/task")
				.content(asJsonString(task))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void test_update_200() throws Exception {

		Task task = new Task();
		task.setTitle("Test");
		task.setDescription("Test task");
		task.setFinished(false);

		mockMvc.perform(MockMvcRequestBuilders.put("/task/100")
				.content(asJsonString(task))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void test_update_404() throws Exception {

		Task task = new Task();
		task.setTitle("Test");
		task.setDescription("Test task");
		task.setFinished(false);

		mockMvc.perform(MockMvcRequestBuilders.put("/task/1234567890")
				.content(asJsonString(task))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void test_delete_by_id_200() throws Exception {
		this.mockMvc.perform(delete("/task/101")).andExpect(status().isOk());
	}

	@Test
	void test_delete_by_id_404() throws Exception {
		this.mockMvc.perform(delete("/task/1234567890")).andExpect(status().is4xxClientError());
	}

	@Test
	void test_delete_200() throws Exception {

		Task task = new Task();
		task.setId(102L);
		task.setTitle("Test");
		task.setDescription("Test task");
		task.setFinished(false);

		mockMvc.perform(MockMvcRequestBuilders.delete("/task")
				.content(asJsonString(task))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
