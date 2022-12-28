package com.divergentsl.exception;

import com.divergentsl.entity.JwtResponse;
import com.divergentsl.entity.ResponseRender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<JwtResponse> authenticationException(AuthenticationException authenticationException)
    {
        log.error("Error While Authentication: " + ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(), authenticationException);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setMessage("Invalid Credentials ");
        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseRender> requestDataValidationException(CustomRequestDataValidationException customRequestDataValidationException)
    {
        log.error("Error While Validating Request Data: " + ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(), customRequestDataValidationException);
        ResponseRender responseRender=new ResponseRender();
        responseRender.setMessage(customRequestDataValidationException.getMessage());
        return new ResponseEntity<ResponseRender>(responseRender, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        log.error("Error While Sending Wrong Http Method Type: "+ ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString()+" Type:"+exception.getMethod(),exception);
        return new ResponseEntity<Object>(exception.getMethod()+" Method Type Is Not Supported For This Request",HttpStatus.METHOD_NOT_ALLOWED);
    }


}
