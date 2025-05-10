package mb.minecraft.dao;

/**
 *
 * @author mikebro
 */
public class DaoConstraintException extends RuntimeException {

	public static final String UNIQUE_CONSTRAINT_ERROR = "Dataset of type \"%s\" does not allow duplicate values for field \"%s\"";

	private Object model;

	public DaoConstraintException( String constraintMessage, Object model ) {
		super( constraintMessage );
		this.model = model;
	}

	public Object getOffendingObject() {
		return this.model;
	}
}
