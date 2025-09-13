package com.nitesh.expense.replica.service.mapper;

import com.nitesh.expense.replica.service.dto.DbzData;
import com.nitesh.expense.replica.service.entity.ExpenseRaw;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ExpenseMapper {


    @Mapping(source = "expenseId", target = "expenseId", qualifiedByName = "longToString")
    @Mapping(source = "expenseTitle", target = "expenseTitle")
    @Mapping(source = "expenseDescription", target = "expenseDescription")
    @Mapping(source = "expenseAmount", target = "expenseAmount")
    @Mapping(source = "expenseDate", target = "expenseDate", qualifiedByName = "stringToLocalDate")
    @Mapping(source = "expenseLocation", target = "expenseLocation")
    @Mapping(source = "tags", target = "tags", qualifiedByName = "stringToList")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "attachmentId", target = "attachmentId")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "exchangeRate", target = "exchangeRate")
    @Mapping(source = "expenseInputTime", target = "expenseInputTime", qualifiedByName = "stringToLocalDateTime") // Updated for String -> LocalDateTime conversion
    @Mapping(source = "eventId", target = "eventId")
    @Mapping(target = "deleted", constant = "false") // default to false for a new insert
    ExpenseRaw dbzDataToExpenseRaw(DbzData dbzData);

    @Mapping(source = "expenseId", target = "expenseId", qualifiedByName = "stringToLong")
    @Mapping(source = "expenseTitle", target = "expenseTitle")
    @Mapping(source = "expenseDescription", target = "expenseDescription")
    @Mapping(source = "expenseAmount", target = "expenseAmount")
    @Mapping(source = "expenseDate", target = "expenseDate", qualifiedByName = "localDateToString")
    @Mapping(source = "expenseLocation", target = "expenseLocation")
    @Mapping(source = "tags", target = "tags", qualifiedByName = "listToString")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "notes", target = "notes")
    @Mapping(source = "attachmentId", target = "attachmentId")
    @Mapping(source = "currency", target = "currency")
    @Mapping(source = "exchangeRate", target = "exchangeRate")
    @Mapping(source = "expenseInputTime", target = "expenseInputTime", qualifiedByName = "localDateTimeToString") // Updated for LocalDateTime -> String conversion
    @Mapping(source = "eventId", target = "eventId")
    DbzData sexpenseRawToDbzData(ExpenseRaw expenseRaw);

    // Convert Long to String (for expenseId)
    @Named("longToString")
    default String longToString(Long value) {
        return value != null ? value.toString() : null;
    }

    // Convert String to Long (for expenseId)
    @Named("stringToLong")
    default Long stringToLong(String value) {
        return value != null ? Long.valueOf(value) : null;
    }

    // Convert String to LocalDate (for expenseDate)
    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            long days = Long.parseLong(value);   // e.g., 20454
            return LocalDate.ofEpochDay(days);   // 2026-01-01
        } catch (NumberFormatException e) {
            // fallback: ISO date string
            return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    // Convert LocalDate to String (for expenseDate)
    @Named("localDateToString")
    default String localDateToString(LocalDate value) {
        return value != null
                ? String.valueOf(value.toEpochDay())
                : null;
    }

    // Convert String to LocalDateTime (for expenseInputTime)
    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String value) {
        if (value == null || value.isBlank()) return null;

        // first: try to parse as long (epoch)
        try {
            long epoch = Long.parseLong(value);
            // choose seconds vs millis
            Instant instant = epoch > 9_999_999_999L      // ~Sat Nov 20 2286 (sec)
                    ? Instant.ofEpochMilli(epoch)         // looks like millis
                    : Instant.ofEpochSecond(epoch);
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        } catch (NumberFormatException e) {
            // fallback: ISO-8601 string ("2026-01-01T10:15:30")
            return LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
        }
    }


    // Convert LocalDateTime to String (for expenseInputTime)
    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime value) {
        return value != null
                ? String.valueOf(value.atZone(ZoneId.systemDefault())
                                      .toInstant()
                                      .toEpochMilli())   // or .toEpochSecond()
                : null;
    }

    // Convert String to List<String> (for tags)
    @Named("stringToList")
    default List<String> stringToList(String value) {
        return value != null ? List.of(value.split(",")) : null; // Assuming tags are comma-separated
    }

    // Convert List<String> to String (for tags)
    @Named("listToString")
    default String listToString(List<String> value) {
        return value != null ? String.join(",", value) : null;
    }
}

