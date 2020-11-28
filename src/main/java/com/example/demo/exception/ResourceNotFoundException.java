package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResourceNotFoundException extends RuntimeException  {
   private String resourceName;
   private String fieldName;
   private Object fieldValue;

   public ResourceNotFoundException(String message){
       super(message);
   }

   public ResourceNotFoundException(String resourceName,String fieldName,Object fieldValue){
       String.format("recource not found",resourceName,fieldName,fieldValue);
       this.resourceName = resourceName;
       this.fieldName = fieldName;
       this.fieldValue = fieldValue;
   }


}
