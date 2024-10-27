package hva.exceptions;

import java.io.Serial;

public class CoreNoResponsibilityException extends Exception {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    public CoreNoResponsibilityException(String employeeKey, String responsibilityKey) {
    }
}
