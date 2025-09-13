package com.nitesh.expense.replica.service.service;

import com.nitesh.expense.replica.service.dto.DbzChangeEvent;
import com.nitesh.expense.replica.service.entity.ExpenseRaw;
import com.nitesh.expense.replica.service.mapper.ExpenseMapper;
import com.nitesh.expense.replica.service.repository.ExpenseRawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseCdcConsumer {

    private final ExpenseRawRepository repository;
    private final ExpenseMapper expenseMapper;


    @KafkaListener(topics = "postgres_test_db.public.et_expense", groupId = "expense-consumers")
    public void consume(DbzChangeEvent message) {
        try {
            log.info("Received Message-->{}", message);
            String op = message.getPayload().getOp();
            log.info("Operation is -->{}", op);
            switch (op) {
                case "c":
                case "u":
                      log.info("Operation received is create or update");
                      ExpenseRaw expenseRaw = expenseMapper.dbzDataToExpenseRaw(message.getPayload().getAfter());
                      expenseRaw.setDeleted(false);
                      repository.save(expenseRaw);
                    break;
                case "d":
                    log.info("Operation received is delete for "+message.getPayload().getBefore().getExpenseId());
                    Optional<ExpenseRaw> existingExpenseRaw = repository.findById(message.getPayload().getBefore().getExpenseId());
                    if(existingExpenseRaw.isPresent()){
                        log.info("Entered to delete");
                        ExpenseRaw eR = existingExpenseRaw.get();
                        eR.setDeleted(true);
                        repository.save(eR);
                    }
                    break;
            }
        } catch (Exception e) {
            //e.printStackTrace(); // Later, consider sending to DLQ
            log.error("Error Encountered as {} and \n {}", e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }

}
