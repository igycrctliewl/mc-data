package mb.minecraft.dao;

/**
 *
 * @author mikebro
 */
public class InvalidValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final String INVALID_VALUE_ERROR = "\"%s\" is an invalid value for type \"%s\"";

	public InvalidValueException( String valueMessage ) {
		super( valueMessage );
	}
}
