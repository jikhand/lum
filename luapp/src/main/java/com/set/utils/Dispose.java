package com.set.utils;

import static java.lang.annotation.ElementType.FIELD; 
import static java.lang.annotation.ElementType.METHOD; 
import static java.lang.annotation.RetentionPolicy.RUNTIME; 
 
import java.lang.annotation.Retention; 
import java.lang.annotation.Target; 
 
import static java.lang.annotation.ElementType.FIELD; 
import static java.lang.annotation.ElementType.METHOD; 
import static java.lang.annotation.RetentionPolicy.RUNTIME; 
 
import java.lang.annotation.Retention; 
import java.lang.annotation.Target; 
 
/**
 * This annotation marks fields in JPA entities and DTOs, that the field is the 
 * "disposal" indicator. If that value changed to true, the object can be 
 * removed from any internal caches. 
 */ 
@Target({ FIELD, METHOD }) 
@Retention(RUNTIME) 
public @interface Dispose { 
 
}