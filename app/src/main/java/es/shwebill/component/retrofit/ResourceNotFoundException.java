package es.shwebill.component.retrofit;

public class ResourceNotFoundException
        extends RestException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {

        super("NOT_FOUND", "RESOURCE_NOT_FOUND_EXCEPTION", "RESOURCE_NOT_FOUND_EXCEPTION");
    }

    @Override
    public String toString() {

        return "ResourceNotFoundException{}";
    }
}
