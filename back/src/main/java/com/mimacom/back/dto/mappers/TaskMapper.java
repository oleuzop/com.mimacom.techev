package com.mimacom.back.dto.mappers;

import com.mimacom.back.dto.TaskDto;
import com.mimacom.back.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto taskToDto(Task salary);

    Task taskFromDto(TaskDto salaryDto);

    List<TaskDto> taskToDtoList(List<Task> taskList);

    List<Task> taskFromDtoList(List<TaskDto> taskDtoList);
}