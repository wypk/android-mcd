package es.shwebill.component.retrofit;

public class BadRequestException
        extends RestException {

    private static final long serialVersionUID = 2895146036460151014L;

    public BadRequestException(String errorType, String errorCode, String errorBody) {

        super(errorType, errorCode, errorBody);
    }

    @Override
    public String toString() {

        return "errorType : " + this.errorType + " , errorCode : " + this.errorCode +
                " , errorMessage : [" + this.errorMessage + "].";
    }
}
