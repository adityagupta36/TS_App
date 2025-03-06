package task.TS_App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import org.springframework.security.access.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleAllExceptions {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleHandlerMethodValidationException(HandlerMethodValidationException exception) {
        Map<String, Object> response = new HashMap<>();

        Map<String, String> errors = new HashMap<>();
        exception.getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        });

        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, Object> response = new HashMap<>();

//        Map<String, String> errors = new HashMap<>();
        Map<String, String> errors =
                exception.getBindingResult().
                        getFieldErrors().
                        stream().collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage()));
    /*    for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }*/

        response.put("status", HttpStatus.BAD_REQUEST.value());  // 400
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> noSuchElement(NoSuchElementException exception){
        Map<String,Object > response  = new HashMap<>();

        Map<String,String > stringStringMap  = new HashMap<>();
        stringStringMap.put("Error", exception.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());  // 400
        response.put("message", "Element Existing Failing");
        response.put("errors", stringStringMap);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> noSuchElement(AccessDeniedException exception){
        Map<String,Object > response  = new HashMap<>();

        Map<String,String > stringStringMap  = new HashMap<>();
        stringStringMap.put("Error", exception.getMessage());
        response.put("status", HttpStatus.NOT_ACCEPTABLE.value());  // 400
        response.put("message", "Access Failing");
        response.put("errors", stringStringMap);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoReportsFoundException.class)
    public ResponseEntity<Map<String, Object>> noReportsException(NoReportsFoundException exception){
        Map<String, Object> response  = new HashMap<>();

        Map<String,String> error  = new HashMap<>();
        error.put("Report Error Faced!", exception.getMessage());

        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("Error", error);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



}
