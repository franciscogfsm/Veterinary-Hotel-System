package hva.exceptions;

import java.io.Serial;

public class CoreUnknownVeterinarianKeyException extends Exception {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    public CoreUnknownVeterinarianKeyException(String id) {
    }
}
