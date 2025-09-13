package com.nitesh.expense.replica.service.repository;

import com.nitesh.expense.replica.service.entity.ExpenseRaw;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRawRepository extends MongoRepository<ExpenseRaw, String> {
}
