package spring.restful_api.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentException extends RuntimeException {

    public DepartmentException(Long id) {
        super("Could not find department ID : " + id);
    }

    public DepartmentException(String message) {
        super(message);
    }
}
