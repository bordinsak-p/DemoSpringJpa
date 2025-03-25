package spring.restful_api.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeException extends RuntimeException {

    public EmployeeException(UUID id) {
        super("Could not find employee ID : " + id);
    }

    public EmployeeException(String message) {
        super(message);
    }

}
