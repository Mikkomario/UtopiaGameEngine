package utopia_interfaceElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import utopia_gameobjects.DrawnObject;
import utopia_graphic.SingleSpriteDrawer;
import utopia_graphic.Sprite;
import utopia_graphic.ParagraphDrawer;
import utopia_worlds.Area;

// TODO: Consider adding a width / height system that allows rescaling 
// of the background sprite without scaling the text size

/**
 * Messageboxes are used to present information to the user in text format. 
 * Messageboxes can be drawn on screen and manipulated like any DrawnObject
 * 
 * @author Mikko Hilpinen
 * @since 8.1.2014
 */
public class MessageBox extends DrawnObject
{
	// ATTRIBUTES	-----------------------------------------------------
	
	/**
	 * Margin is the empty amount of pixels left to the each side of the box
	 */
	protected static final int MARGIN = 15;
	
	private SingleSpriteDrawer spritedrawer;
	private ParagraphDrawer textDrawer;
	
	
	// CONSTRUCTOR	-----------------------------------------------------

	/**
	 * Creates a new abstract message to the given position with the given 
	 * message and background.
	 * 
	 * @param x The x-coordinate of the box's center
	 * @param y The y-coordinate of the box's center
	 * @param depth The drawing depth of the box
	 * @param message The message shown in the box
	 * @param textfont The font used in drawing the message
	 * @param textcolor What color is used when drawing the text
	 * @param backgroundsprite The sprite used to draw the background of the 
	 * messageBox
	 * @param area The area where the object will reside
	 */
	public MessageBox(int x, int y, int depth, String message, 
			Font textfont, Color textcolor, Sprite backgroundsprite, Area area)
	{
		super(x, y, depth, area);

		// Initializes the attributes
		this.spritedrawer = new SingleSpriteDrawer(backgroundsprite, 
				area.getActorHandler(), this);
		this.textDrawer = new ParagraphDrawer(message + "", textfont, textcolor, 
				this.spritedrawer.getSprite().getWidth() - MARGIN, this);
	}
	
	
	// IMPLEMENTED METHODS	--------------------------------------------

	@Override
	public int getOriginX()
	{
		if (this.spritedrawer == null)
			return 0;
		
		return this.spritedrawer.getSprite().getWidth() / 2;
	}

	@Override
	public int getOriginY()
	{
		if (this.spritedrawer == null)
			return 0;
		
		return this.spritedrawer.getSprite().getHeight() / 2;
	}

	@Override
	public void drawSelfBasic(Graphics2D g2d)
	{
		// TODO: Test the positioning that it's right
		
		// Draws the background
		if (this.spritedrawer != null)
			this.spritedrawer.drawSprite(g2d, 0, 0);
		
		// And the text
		if (this.textDrawer != null)
			this.textDrawer.drawText(g2d, MARGIN, MARGIN);
	}
}
