package my.project.exception;

import my.project.exception.customException.ResourceNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //400 Incorrect data...
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String message = "INCORRECT DATA";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
        List<ApiSubError> list = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> {
                    return new ApiSubError(err.getObjectName(),
                            err.getField(),
                            err.getRejectedValue().toString(),
                            err.getDefaultMessage());
                }).collect(Collectors.toList());

        apiError.setSubErrorsList(list);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //400 Incorrect JSON body...
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String message = "INCORRECT JSON";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //400 Type mismatch...
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = "TYPE MISMATCH";
        ApiSubError apiSubError = new ApiSubError("", "", ex.getValue().toString(), ex.getMessage());
        List<ApiSubError> apiSubErrorList = new ArrayList<>();
        apiSubErrorList.add(apiSubError);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
        apiError.setSubErrorsList(apiSubErrorList);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //404 Resource not found...
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //405 Method not supported for this resource...
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "Method not allowed for this resource";

        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, message);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
