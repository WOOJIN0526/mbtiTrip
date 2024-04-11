//package com.example.testExcepion;
//
//
//import java.nio.charset.Charset;
//
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.resource.NoResourceFoundException;
//
//import com.example.testExcepion.Cart.CartException;
//import com.example.testExcepion.Insert.InsertException;
//import com.example.testExcepion.SignUP.SignUpException;
//import com.example.testExcepion.login.LoginException;
//
//import groovy.util.logging.Slf4j;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//   
//    HttpHeaders headers;
//    
//    @ExceptionHandler(SignUpException.class)
//    protected ResponseEntity<ExceptionResponse> signUPException(SignUpException ex){
//    	log.error("[signUpExHandle] ex", ex);
//    	ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getSignupExceptionEnum());
//    	return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
//    }
//    
//    @ExceptionHandler(LoginException.class)
//    protected ResponseEntity<ExceptionResponse> LoginException(LoginException ex){
//    	log.error("[loginExHandle] ex", ex);
//    	ExceptionResponse er = new ExceptionResponse(ex.getLoginExceptionEnum());
//    	return ResponseEntity.status(er.getStatus()).body(er);
//    }
//    
//    @ExceptionHandler(InsertException.class)
//    protected ResponseEntity<ExceptionResponse> LoginException(InsertException ex){
//    	log.error("[insertHandler] ex", ex);
//    	ExceptionResponse er = new ExceptionResponse(ex.getInsertExceptionEnum());
//    	return ResponseEntity.status(er.getStatus()).body(er);
//    }
//    
//    @ExceptionHandler(CartException.class)
//    protected ResponseEntity<ExceptionResponse> LoginException(CartException ex){
//    	log.error("[insertHandler] ex", ex);
//    	ExceptionResponse er = new ExceptionResponse(ex.getCartExceptionEnum());
//    	return ResponseEntity.status(er.getStatus()).body(er);
//    }
//    
//    
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<String> handleUserException(Exception ex){
//    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//    }
//	
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<ErrorResponse> handleBusinessException(Exception ex) {
//    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//    	log.error("handlerException", ex.getMessage());
//    	ErrorRespone response = new ErrorRespone(ErrorCode.INTER_SERVER_ERROR);
//    	return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    
//    @ExceptionHandler(NullPointerException.class)
//    protected ResponseEntity<ErrorResponse> NullhandlerException(NullPointerException nEX){
//    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//    	log.error("nullPoingException", nEX.getMessage());
//    	ErrorRespone response = new ErrorRespone(nEX.toString(), "null" );
//    	return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//    }
//    	
//    @ExceptionHandler(NoResourceFoundException.class)
//    protected ResponseEntity<ErrorResponse> NoResourceFoundException(NoResourceFoundException noresource){
//    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//    	log.error("NoResourceFoundException", noresource.getMessage());
//    	ErrorRespone response = new ErrorRespone(noresource.toString(), "Noresource" );
//    	return new ResponseEntity(response, HttpStatus.CONTINUE);	
//    }
//    
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponse> illegalExHandle(IllegalArgumentException e) {
//        log.error("[exceptionHandle] ex", e);
//        ErrorRespone response = new ErrorRespone(e.toString(), "IllegalArgumentException" );
//        return new ResponseEntity("BAD",HttpStatus.BAD_REQUEST);
//    }
//    
//}
//
//
//
//
//
//
