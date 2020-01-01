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

    /*
    @Mappings({
            // @Mapping(target = "id", source = "salary.id"),
            // @Mapping(target = "version", source = "salary.version"),
            // @Mapping(target = "montant", source = "salary.montant"),
            @Mapping(target = "employeeDto", source = "salary.employee")
    })


    public Page<TaskDto> toPageDto(Page<Task> page) {
        return new PageImpl<>(this.salaryToDtoList(page.getContent()),
                page.getPageable(), page.getTotalElements());
    }
    */
}