package mb.minecraft.model;

import mb.minecraft.dao.InvalidValueException;

public enum OfferRequire {

	OFFER( "O" ),
	REQUIRE( "R" );

	private String offerRequire;

	OfferRequire( String setting ) {
		offerRequire = setting;
	}

	public String getCode() {
		return offerRequire;
	}

	public static OfferRequire getFromCode( String code ) {
		if( OFFER.getCode().equals( code ) )
			return OFFER;
		if( REQUIRE.getCode().equals( code ) )
			return REQUIRE;
		throw new InvalidValueException( String.format( InvalidValueException.INVALID_VALUE_ERROR, code, OfferRequire.class.getSimpleName() ) );
	}
}
