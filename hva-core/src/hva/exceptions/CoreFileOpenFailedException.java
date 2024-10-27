package hva.exceptions;

import java.io.Serial;

public class CoreFileOpenFailedException extends Exception {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    public CoreFileOpenFailedException(Exception e) {
    }
}
