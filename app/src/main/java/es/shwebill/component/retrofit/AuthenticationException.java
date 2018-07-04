package es.shwebill.component.retrofit;

public class AuthenticationException
        extends RestException {

    private static final long serialVersionUID = 4062679624836917363L;

    public AuthenticationException() {

        super("ACCESS_DENIED", "AUTHENTICATION_EXCEPTION", "AUTHENTICATION_EXCEPTION");
    }

    @Override
    public String toString() {

        return "AuthenticationException{}";
    }
}
