package com.fatmadelenn.recommendation.ecommerce.exception;

import com.fatmadelenn.recommendation.ecommerce.dto.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    private static final String ERROR_TEXT_PREFIX = "Encountered with";

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException ex, WebRequest request) {
        logger.debug(ERROR_TEXT_PREFIX, ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
