package com.nitesh.expense.service.mapper;

import com.nitesh.expense.service.dto.ExpenseEventRequestDTO;
import com.nitesh.expense.service.dto.ExpenseEventResponseDTO;
import com.nitesh.expense.service.entity.ExpenseEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExpenseEventMapper {

    @Mapping(target = "eventId", ignore = true)          // DB will generate
    @Mapping(target = "eventStatus", ignore = true)      // set in @PrePersist
    @Mapping(target = "eventInputTime", ignore = true)   // set in @PrePersist
    ExpenseEvent toEntity(ExpenseEventRequestDTO dto);

    // Entity -> ResponseDTO
    ExpenseEventResponseDTO toResponseDto(ExpenseEvent entity);


    // Request -> Update Existing Entity
    @Mapping(target = "eventId", ignore = true)          // don't allow overriding ID
    @Mapping(target = "eventStatus", ignore = true)      // business-controlled
    @Mapping(target = "eventInputTime", ignore = true)   // system-controlled
    void map(ExpenseEventRequestDTO dto, @MappingTarget ExpenseEvent entity);
}
