package db;

public class DBException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public DBException(String message){
        super(message);
    }

}
