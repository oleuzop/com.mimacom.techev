package com.mimacom.back.controller;

import com.mimacom.back.dto.TaskDto;
import com.mimacom.back.dto.mappers.TaskMapper;
import com.mimacom.back.model.Task;
import com.mimacom.back.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private @Autowired
    TaskService taskService;

    /**
     * findAll
     * GET
     * http://localhost:8080/task
     */
    @GetMapping
    public List<TaskDto> findAll() {
        return TaskMapper.INSTANCE.taskToDtoList(taskService.findAll());
    }

    /**
     * findById
     * GET
     * http://localhost:8080/task/100
     */
    @GetMapping(path = "/{id}")
    public TaskDto findById(@PathVariable Long id) {
        return TaskMapper.INSTANCE.taskToDto(taskService.findById(id));
    }

    /**
     * update
     * POST
     * http://localhost:8080/task
     */
    @PostMapping
    public TaskDto create(@RequestBody Task task) {
        return TaskMapper.INSTANCE.taskToDto(taskService.create(task));
    }

    /**
     * update
     * PUT
     * http://localhost:8080/task/100
     */
    @PutMapping("/{id}")
    public TaskDto update(@PathVariable Long id, @RequestBody Task task) {
        return TaskMapper.INSTANCE.taskToDto(taskService.update(id, task));
    }

    /**
     * deleteById
     * DELETE
     * http://localhost:8080/task/100
     */
    @DeleteMapping(path = "/{id}")
    public TaskDto deleteById(@PathVariable Long id) {
        return TaskMapper.INSTANCE.taskToDto(taskService.delete(id));
    }

    @DeleteMapping
    public TaskDto delete(@RequestBody Task task) {
        return TaskMapper.INSTANCE.taskToDto(taskService.delete(task));
    }
}
