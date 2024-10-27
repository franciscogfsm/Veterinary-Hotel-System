package hva.exceptions;

import java.io.Serial;

public class CoreVeterinarianNotAuthorizedException extends Exception {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    public CoreVeterinarianNotAuthorizedException(String idVet, String idSpecies) {
    }
}
