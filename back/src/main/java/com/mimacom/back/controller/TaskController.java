package com.mimacom.back.controller;

import com.mimacom.back.dto.TaskDto;
import com.mimacom.back.dto.mappers.TaskMapper;
import com.mimacom.back.model.Task;
import com.mimacom.back.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    @Inject
    public TaskController(TaskService taskService) {
        Assert.notNull(taskService, "TaskService must not be null!");
        this.taskService = taskService;
    }

    /**
     * findAll
     * GET
     * http://localhost:8080/task
     */
    @GetMapping
    public List<TaskDto> findAll() {
        return TaskMapper.INSTANCE.taskToDtoList(taskService.findAll());
    }

    @GetMapping(params = { "page", "size" })
    public List<TaskDto> findAllPaged(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {

        Page<Task> resultPage = taskService.findAllPaged(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new EntityNotFoundException();
        }

        return TaskMapper.INSTANCE.taskToDtoList(resultPage.getContent());
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
