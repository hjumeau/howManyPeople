package howmanypeople.exception

import org.springframework.validation.Errors

import java.text.MessageFormat

class DomainConstraintException extends Exception {

    DomainConstraintException() {
    }

    DomainConstraintException(String message) {
        super(message)
    }

    DomainConstraintException(String message, Throwable cause) {
        super(message, cause)
    }

    DomainConstraintException(Throwable cause) {
        super(cause)
    }

    DomainConstraintException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace)
    }
}
