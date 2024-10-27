package hva.exceptions;

import java.io.Serial;

public class CoreDuplicateVaccineKeyException extends Exception {
	@Serial
	private static final long serialVersionUID = 202407081733L;

	public CoreDuplicateVaccineKeyException(String id) {
	}
}
