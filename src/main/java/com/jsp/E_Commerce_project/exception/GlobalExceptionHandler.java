package com.jsp.E_Commerce_project.exception;


import com.jsp.E_Commerce_project.util.ResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpRequest request;

    @ExceptionHandler({UserNotFound.class})
    public ResponseEntity<ResponseStructure<String>> handle(NotFound ex){
        ex.printStackTrace();
        ResponseStructure<String> responseStructure=new ResponseStructure<>();
        responseStructure.setPath(request.getURI().getPath());
        responseStructure.setMessage(ex.getMessage());
        responseStructure.setMessage("error");
        responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);

    }
}
