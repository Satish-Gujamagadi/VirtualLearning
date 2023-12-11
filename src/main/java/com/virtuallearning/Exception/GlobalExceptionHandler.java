package com.virtuallearning.Exception;

import java.io.IOException;

import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(IOException ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", "IO Exception: " + ex.getMessage());
		return modelAndView;
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", "Data Integrity Violation: " + ex.getMessage());
		return modelAndView;
	}

//	@ExceptionHandler(BadCredentialsException.class)
//	public ModelAndView handleBadCredentialsException(BadCredentialsException ex) {
//		ModelAndView modelAndView = new ModelAndView("error");
//		modelAndView.addObject("errorMessage", "Bad Credentials: " + ex.getMessage());
//		return modelAndView;
//	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMessage", "An unexpected error occurred: " + ex.getMessage());
		return modelAndView;
	}

}
