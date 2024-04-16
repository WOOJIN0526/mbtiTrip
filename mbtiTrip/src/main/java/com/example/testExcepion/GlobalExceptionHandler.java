package com.example.testExcepion;


import java.nio.charset.Charset;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.testExcepion.Utile.NotSupportMediaTypeException;
import com.example.testExcepion.Utile.UserNotFoundExcepiton;
import com.example.testExcepion.Cart.CartException;
import com.example.testExcepion.GCSS.GCSSException;
import com.example.testExcepion.Insert.InsertException;
import com.example.testExcepion.Item.ItemException;
import com.example.testExcepion.Post.PostException;
import com.example.testExcepion.SignUP.SignUpException;
import com.example.testExcepion.login.LoginException;
import com.example.testExcepion.updated.UpdateException;
import com.example.testExcepionQnA.QnAException;

import groovy.util.logging.Slf4j;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

   
    HttpHeaders headers;
    
    @ExceptionHandler(SignUpException.class)
    protected ResponseEntity<ExceptionResponse> SignUPExceptionHandler(SignUpException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("[signUpExHandle] ex", ex);
    	ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getSignupExceptionEnum());
    	return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }
    
    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<ExceptionResponse> LoginExceptionHandler(LoginException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("[loginExHandle] ex", ex);
    	ExceptionResponse er = new ExceptionResponse(ex.getLoginExceptionEnum());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(InsertException.class)
    protected ResponseEntity<ExceptionResponse> insertHandler(InsertException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("[insertHandler] ex", ex);
    	ExceptionResponse er = new ExceptionResponse(ex.getInsertExceptionEnum());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(CartException.class)
    protected ResponseEntity<ExceptionResponse> CartExceptionHandler(CartException ex){
	    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("[CartHandler] ex", ex);
    	ExceptionResponse er = new ExceptionResponse(ex.getCartExceptionEnum());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    
    @ExceptionHandler(UpdateException.class)
    protected ResponseEntity<ExceptionResponse> UpdateExceptionHandler(UpdateException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("[updateHandelr] ex", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getUpdateExceptionCode());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(QnAException.class)
    ResponseEntity<ExceptionResponse> QnAExceptionHandler(QnAException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("[updateHandelr] ex", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getQnaExceptionCode());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(NotSupportMediaTypeException.class)
    ResponseEntity<ExceptionResponse> NotSupportMediaTypeExceptionHandler(NotSupportMediaTypeException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("[updateHandelr] ex", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getUtileExceptionCode());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    
    @ExceptionHandler(UserNotFoundExcepiton.class)
    protected ResponseEntity<ExceptionResponse> UserNotFoundHandler(UserNotFoundExcepiton ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("handlerException", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getUtileExceptionCode());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
	
    @ExceptionHandler(PostException.class)
    protected ResponseEntity<ExceptionResponse> PostExceptionHandler(PostException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("handlerException", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getPostExceptionEnum());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(ItemException.class)
    protected ResponseEntity<ExceptionResponse> ItemExceptionHandler(ItemException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("handlerException", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getItemExceptionEnum());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(GCSSException.class)
    protected ResponseEntity<ExceptionResponse> GCSSExceptionHandler(GCSSException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("handlerException", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getGcssExceptionCode());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(NotSupportMediaTypeException.class)
    protected ResponseEntity<ExceptionResponse> NotSupportMediaTypeExceptionHandelr(NotSupportMediaTypeException ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("handlerException", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getUtileExceptionCode());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    
    @ExceptionHandler(UserNotFoundExcepiton.class)
    protected ResponseEntity<ExceptionResponse> UserNotFoundExceptionHandelr(UserNotFoundExcepiton ex){
    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    	log.error("handlerException", ex.getMessage());
    	ExceptionResponse er = new ExceptionResponse(ex.getUtileExceptionCode());
    	return ResponseEntity.status(er.getStatus()).body(er);
    }
    

    
//    @ExceptionHandler(NullPointerException.class)
//    protected ResponseEntity<ExceptionResponse> NullhandlerException(NullPointerException nEX){
//    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//    	log.error("nullPoingException", nEX.getMessage());
//    	ExceptionResponse response = new ExceptionResponse(nEX.toString(), "null" );
//    	return new ResponseEntity(response, HttpStatus.NOT_FOUND);
//    }
//    	
//    @ExceptionHandler(NoResourceFoundException.class)
//    protected ResponseEntity<ExceptionResponse> NoResourceFoundException(NoResourceFoundException noresource){
//    	headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//    	log.error("NoResourceFoundException", noresource.getMessage());
//    	ExceptionResponse response = new ExceptionResponse(noresource.toString(), "Noresource" );
//    	return new ResponseEntity(response, HttpStatus.CONTINUE);	
//    }
//    
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ExceptionResponse> illegalExHandle(IllegalArgumentException e) {
//        log.error("[exceptionHandle] ex", e);
//        ExceptionResponse er = new ExceptionResponse(e.getCause());
//        return  ResponseEntity.status(er.getStatus()).body(er);
//    }
    
}






