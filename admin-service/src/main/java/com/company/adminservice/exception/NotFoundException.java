package com.company.adminservice.exception;


// This class handles the NotFoundException when trying to retrieve objects not in the database

public class NotFoundException extends RuntimeException{


        public NotFoundException() { }

        public NotFoundException(String message) {
            super(message);
        }

}
