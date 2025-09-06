package com.nitesh.expense.service.repository;

import com.nitesh.expense.service.entity.ExpenseEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseEventRepository extends JpaRepository<ExpenseEvent, Long> {

    List<ExpenseEvent> findAllByUserId(Long userId);

}
