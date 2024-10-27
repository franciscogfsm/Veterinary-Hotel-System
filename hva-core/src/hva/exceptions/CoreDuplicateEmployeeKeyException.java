package hva.exceptions;

import java.io.Serial;

public class CoreDuplicateEmployeeKeyException extends Exception {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    public CoreDuplicateEmployeeKeyException(String id) {

    }
}
