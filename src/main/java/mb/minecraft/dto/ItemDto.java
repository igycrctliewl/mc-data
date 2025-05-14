package mb.minecraft.dto;

import static mb.minecraft.library.ObjectStringHelper.COMMA_SEPARATOR;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ItemDto is the application-internal representation of the Item entity.
 * This should be a complete version of the object assembled from the data
 * stored in the persistent entity.
 * Part of entity redesign.
 * @author mikebro
 */
public class ItemDto {

	private static final Logger logger = LogManager.getLogger( ItemDto.class );

	private Long id;
	private String name;
	private String imageSource;
	private Image image;


	public ItemDto() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( super.toString() ).append( "( " );
		sb.append( "id=" ).append( this.getId() ).append( COMMA_SEPARATOR );
		sb.append( "name=" ).append( this.getName() );
		sb.append( " )");
		return sb.toString();
	}


	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public String getImageSource() {
		return imageSource;
	}
	public void setImageSource( String imageSource ) {
		this.imageSource = imageSource;
		loadImage();
	}
	public Image getImage() {
		return this.image;
	}
	private void loadImage() {
		try {
			if( this.imageSource != null ) {
				this.image = ImageIO.read( new URL( this.imageSource ) );
			}
		} catch( IOException e ) {
			this.image = null;
			logger.error( "Failed to load image from >{}< for item >{}<", this.getImageSource(), this.getName() );
			//logger.error( "Trace follows...", e );
		}
	}


	public static ItemDto.Builder builder() {
		return new ItemDto.Builder();
	}

	public static class Builder {
		private Long id;
		private String name;
		private String imageSource;
		public Builder id( Long id ) {
			this.id = id;
			return this;
		}
		public Builder name( String name ) {
			this.name = name;
			return this;
		}
		public Builder imageSource( String imageSource ) {
			this.imageSource = imageSource;
			return this;
		}

		public ItemDto build() {
			ItemDto item = new ItemDto();
			item.setId( this.id );
			item.setName( this.name );
			item.setImageSource( this.imageSource );
			return item;
		}
	}

}
