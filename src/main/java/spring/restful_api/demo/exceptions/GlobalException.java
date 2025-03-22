package spring.restful_api.demo.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.restful_api.demo.models.ErrorResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeException(EmployeeException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getServletPath());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
