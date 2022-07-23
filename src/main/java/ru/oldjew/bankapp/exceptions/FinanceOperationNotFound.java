package ru.oldjew.bankapp.exceptions;

public class FinanceOperationNotFound extends Exception{

    public FinanceOperationNotFound() {
        super("Operation not found");
    }
}
