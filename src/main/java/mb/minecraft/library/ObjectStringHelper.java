package mb.minecraft.library;

/**
 * This class contains constants useful for building detailed toString() implementations
 * for the objects in this application.
 * @author mikebro
 */
public class ObjectStringHelper {
	private ObjectStringHelper() throws InstantiationException {
		throw new InstantiationException( "cannot create an instance of " + ObjectStringHelper.class.getName() );
	}

	public static final String COLON_SEPARATOR = ":";
	public static final String COMMA_SEPARATOR = ", ";
	public static final String QUOTE_MARK = "\"";

}
