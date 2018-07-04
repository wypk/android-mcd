package es.shwebill.component.retrofit;

public class RestException
        extends Exception {

    private static final long serialVersionUID = 7230685161631046748L;

    protected String errorType;
    protected String errorCode;
    protected String errorMessage;

    public RestException(String errorType, String errorCode, String errorMessage) {

        this.errorType = errorType;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {

        return this.errorType + " : " + this.errorCode + " : " + this.errorMessage;
    }

    @Override
    public String toString() {

        return this.errorType + " : " + this.errorCode + " : " + this.errorMessage;
    }
}
