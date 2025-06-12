package com.harshal.beniwal.fairshare.exception;

import org.springframework.http.HttpStatus;

public class DomainException {
    public static class UserException extends AppException {
        public UserException(String message , HttpStatus status) {
            super(message, status);
        }


    }

    public static class GroupException extends AppException {
        public GroupException(String message, HttpStatus status) {
            super(message, status );
        }
    }

    public static class ResourceNotFoundException extends AppException {
        public ResourceNotFoundException(String message) {
            super(message, HttpStatus.NOT_FOUND);
        }
    }

}
