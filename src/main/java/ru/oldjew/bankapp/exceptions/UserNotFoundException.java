package ru.oldjew.bankapp.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User not found");
    }
}