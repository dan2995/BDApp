package mt.edu.um.b_d_app;

public class TransactionFailureException extends Exception {

    private String message;

    public TransactionFailureException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
