package com.mimacom.back.service;

import com.mimacom.back.model.Task;
import com.mimacom.back.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class TaskService {

    private @Autowired
    TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> oTask = taskRepository.findById(id);
        if (!oTask.isPresent()) {
            throw new EntityNotFoundException("Task not found: " + id);
        }
        return oTask.get();
    }

    @Transactional
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Long id, Task task) {
        Task tasksToBeUpdated = this.findById(id);
        //Update needed fields
        tasksToBeUpdated.setTitle(task.getTitle());
        tasksToBeUpdated.setDescription(task.getDescription());
        tasksToBeUpdated.setFinished(task.getFinished());
        return taskRepository.save(tasksToBeUpdated);
    }

    @Transactional
    public Task delete(Task task) {
        taskRepository.delete(task);
        return task;
    }

    public Task delete(Long id) {
        return this.delete(this.findById(id));
    }
}
