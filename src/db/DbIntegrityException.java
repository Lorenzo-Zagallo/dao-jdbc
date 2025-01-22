package db;

// exceção personalizada de integridade referencial
public class DbIntegrityException extends RuntimeException {

    public DbIntegrityException(String msg) {
        super(msg);
    }
}
