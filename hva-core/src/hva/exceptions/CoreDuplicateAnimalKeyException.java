package hva.exceptions;

import java.io.Serial;

public class CoreDuplicateAnimalKeyException extends Exception {
	@Serial
	private static final long serialVersionUID = 202407081733L;

	public CoreDuplicateAnimalKeyException(String id) {

	}
}
