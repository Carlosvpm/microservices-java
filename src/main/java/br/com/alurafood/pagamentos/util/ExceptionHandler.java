package br.com.alurafood.pagamentos.util;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationExceptionHandler(ConstraintViolationException exception){
        List<String> errors = exception.getConstraintViolations()
                .parallelStream()
                .map(violation -> violation
                        .getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toList());

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);

    };
}
