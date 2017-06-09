package org.formacio.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="id no hi trobat")
public class OperationException extends RuntimeException {
	// http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	private static final long serialVersionUID = 01L;
}
