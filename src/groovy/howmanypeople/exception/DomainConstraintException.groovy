package howmanypeople.exception

import org.springframework.validation.Errors

import java.text.MessageFormat

class DomainConstraintException extends Exception {

	Errors errors
	
    DomainConstraintException() {
    }

	DomainConstraintException(String message, Errors errors) {
		super(message)
		this.errors = errors
	}
	
    DomainConstraintException(String message) {
        super(message)
    }

	DomainConstraintException(Throwable cause) {
		super(cause)
	}
	
    DomainConstraintException(String message, Throwable cause) {
        super(message, cause)
    }
}
