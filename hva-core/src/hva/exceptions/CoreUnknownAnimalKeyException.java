package hva.exceptions;

import java.io.Serial;

public class CoreUnknownAnimalKeyException extends Exception {
	@Serial
	private static final long serialVersionUID = 202407081733L;
	private String _Id;
	public CoreUnknownAnimalKeyException(String key) {
		_Id = key;

	}
	public String getId() {
		return _Id;
	}
}
