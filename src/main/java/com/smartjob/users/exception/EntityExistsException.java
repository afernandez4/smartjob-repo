package com.smartjob.users.exception;

public class EntityExistsException extends RuntimeException {

  public EntityExistsException(String email) {
    super("The user with email " + email + " already exist.");
  }

}
