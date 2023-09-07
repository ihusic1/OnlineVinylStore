package ba.unsa.etf.rpr.exceptions;

public class RecordException extends Exception{

    public RecordException(String msg, Exception reason){
        super(msg, reason);
    }

    public RecordException(String msg){

        super(msg);
    }
}