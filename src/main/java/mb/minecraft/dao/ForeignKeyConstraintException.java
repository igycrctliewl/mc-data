package mb.minecraft.dao;

/**
 * ForeignKeyConstraintException is thrown when a database action (update or delete)
 * is attempted on an object and the action cannot be allowed because the object has
 * a relationship to another object.
 * @author mikebro
 */
public class ForeignKeyConstraintException extends RuntimeException {

	public static final String FOREIGN_KEY_CONSTRAINT_ERROR = "Cannot delete or update %s object with id %s: Object is assigned to %s with id %s";

	public ForeignKeyConstraintException( String constraintMessage ) {
		super( constraintMessage );
	}

}
