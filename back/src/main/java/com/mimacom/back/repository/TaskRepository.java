package com.mimacom.back.repository;

import com.mimacom.back.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //@Query( value = "select s from Task",
    //        countQuery = "select count(s) from Task as t")
    //Page<Task> findPageTask(Pageable pageable);

}
