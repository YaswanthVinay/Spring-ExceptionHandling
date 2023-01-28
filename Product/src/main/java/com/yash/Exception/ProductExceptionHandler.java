package com.yash.Exception;

import java.util.Date;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException productNotFoundException){

		ProductPojo productPojo=new ProductPojo();
		productPojo.setMessage("product not found with the Id you entered, so please check again!");
		productPojo.setTimestamp(new Date());
		
		productPojo.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity(productPojo, HttpStatus.BAD_REQUEST);
	}
	


	@ExceptionHandler(IdAlreaadyExists.class)
	public ResponseEntity<?> handleIdAlreaadyExists(IdAlreaadyExists idAlreaadyExists){

		ProductPojo productPojo=new ProductPojo();
		productPojo.setMessage("id already exists in the database , so please try another with ID");
		productPojo.setTimestamp(new Date());
		
		productPojo.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity(productPojo, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProductPojo productPojo=new ProductPojo();
		productPojo.setMessage("Request method  not supported , so please Check With the correct Request method ");
		productPojo.setTimestamp(new Date());
		
		productPojo.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity(productPojo, HttpStatus.BAD_REQUEST);
	}

}
