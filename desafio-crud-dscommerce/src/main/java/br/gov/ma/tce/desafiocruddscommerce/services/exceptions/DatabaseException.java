package br.gov.ma.tce.desafiocruddscommerce.services.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
