package com.nitesh.gateway.service.controller;

import com.nitesh.gateway.service.dto.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RestController
public class FallbackController {

    @GetMapping(value = "/fallback/expense", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ResponseWrapper<String>>> userServiceFallback() {
        //return Mono.just("{\"message\":\"Expense Service is temporarily unavailable. Please try again later.\"}");

        return Mono.just("Expense Service is temporarily unavailable. Please try again later.")
                .map(message -> {
                    // Create the custom wrapper for the body
                    ResponseWrapper<String> wrapper =
                            new ResponseWrapper<>(HttpStatus.NO_CONTENT.value(),
                                    message,
                                    null,
                                    new ArrayList<>()
                                    );
                    // Return a 200 OK response with the wrapped body
                    return ResponseEntity.ok(wrapper);
                })
                .defaultIfEmpty(
                        // Handle a case where the Mono is empty
                        ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                );
    }

}

