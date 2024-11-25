package com.jgerardo.fromzeroapi.shared.infrastructure.exceptionHandler;

import com.jgerardo.fromzeroapi.projects.domain.exceptions.DeliverableFilesProcessingException;
import com.jgerardo.fromzeroapi.shared.domain.exceptions.CompanyNotFoundException;
import com.jgerardo.fromzeroapi.shared.domain.exceptions.DeveloperNotFoundException;
import com.jgerardo.fromzeroapi.shared.interfaces.rest.resources.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler({CompanyNotFoundException.class, DeveloperNotFoundException.class})
    public ResponseEntity<MessageResource> handle(RuntimeException ex){
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new MessageResource(ex.getMessage()));
    }

    @ExceptionHandler(DeliverableFilesProcessingException.class)
    public ResponseEntity<MessageResource> handle(DeliverableFilesProcessingException ex){
        LOGGER.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource(ex.getMessage()));
    }
}
