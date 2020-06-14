package net.marco27.api.filesystemapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/** https://dzone.com/articles/spring-rest-service-exception-handling-1 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ DocumentNotFoundException.class })
    public void handle(DocumentNotFoundException e) {
    }

}
