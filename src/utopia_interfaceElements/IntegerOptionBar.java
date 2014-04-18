package utopia_interfaceElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import utopia_gameobjects.DrawnObject;
import utopia_graphic.Sprite;
import utopia_helpAndEnums.DepthConstants;
import utopia_listeners.RoomListener;
import utopia_worlds.Area;
import utopia_worlds.Room;

/**
 * Creates an OptionBar for one of the game's options.
 * 
 * @author Unto Solala & Mikko Hilpinen
 * @since 8.9.2013
 */
public class IntegerOptionBar extends DrawnObject implements RoomListener
{
	// ATTRIBUTES-------------------------------------------------------
	
	private int value;
	private int minValue;
	private int maxValue;
	private String description;
	private OptionBarButton leftbutton, rightbutton;
	private Font font;
	private Color textColor;

	
	//CONSTRUCTOR-------------------------------------------------------
	
	/**
	 * Constructs an OptionBar based on the given parameters.
	 * 
	 * @param x The x-coordinate of the bar's left side (in pixels)
	 * @param y The y-coordinate of the bar's top (in pixels)
	 * @param defaultValue The value the bar will have as default
	 * @param minValue The minimum value the bar can have
	 * @param maxValue The maximum value the bar can have
	 * @param description The description shown in the bar
	 * @param textFont What font the text will use
	 * @param textColor What color the text will have
	 * @param buttonSprite What sprite is used to draw the value-adjustment buttons
	 * @param buttonMask The mask used for the buttons' collision checking
	 * @param area The area where the object is placed to
	 */
	public IntegerOptionBar(int x, int y, int defaultValue,
			int minValue, int maxValue, String description, Font textFont, 
			Color textColor, Sprite buttonSprite, Sprite buttonMask, Area area)
	{
		super(x, y, DepthConstants.NORMAL, area);

		// Initializes attributes
		this.value = defaultValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.description = description;
		this.textColor = textColor;
		this.font = textFont;
		
		this.leftbutton = new OptionBarButton((int)this.getX(),
				(int)this.getY(), OptionBarButton.LEFT, buttonSprite, 
				buttonMask, area);
		this.rightbutton = new OptionBarButton((int)this.getX()+100,
				(int)this.getY(), OptionBarButton.RIGHT, buttonSprite, 
				buttonMask, area);
	}
	
	
	//GETTERS & SETTERS ------------------------------------------------
	
	/**
	 * @return The value the user has chosen
	 */
	public int getValue()
	{
		return this.value;
	}
	
	
	//IMPLEMENTED METHODS ----------------------------------------------
	
	@Override
	public int getOriginX()
	{
		return 0;
	}

	@Override
	public int getOriginY()
	{
		return 0;
	}

	@Override
	public void drawSelfBasic(Graphics2D g2d)
	{
		g2d.setFont(this.font);
		g2d.setColor(this.textColor);
		g2d.drawString(getValuePrint(this.value), 30, 15);
		g2d.drawString(this.description, 150, 15);
	}

	@Override
	public void onRoomStart(Room room)
	{
		// Does nothing
	}

	@Override
	public void onRoomEnd(Room room)
	{
		// Death approaches
		kill();
	}
	
	@Override
	public void kill()
	{
		super.kill();
		this.leftbutton.kill();
		this.rightbutton.kill();
	}
	
	
	// OTHER METHODS	--------------------------------------------------
	
	/**
	 * This method defines how the value is drawn. Some subclasses may wish 
	 * to override this method.
	 * 
	 * @param value The value that should be drawn in some manner
	 * @return How the value will be drawn
	 */
	protected String getValuePrint(int value)
	{
		return "" + value;
	}

	/**
	 * OptionBarButtons are buttons used to change the OptionBar's
	 * values.
	 * 
	 * @author Unto Solala & Mikko Hilpinen
	 * @since 8.9.2013
	 */
	private class OptionBarButton extends AbstractMaskButton
	{
		private static final int RIGHT = 0;
		private static final int LEFT = 1;
		
		
		// ATTRIBUTES-------------------------------------------------------
		
		private int direction;
		
		
		//CONSTRUCTOR-------------------------------------------------------
		
		/**
		 * Creates the OptionBarButtons, which are used to change the values
		 * of various options in the game.
		 * 
		 * @param x	The x-coordinate of the button
		 * @param y The y-coordinate of the button
		 * button about mouse events
		 * @param direction	The direction the button is pointing, if it points
		 * to the LEFT, the button will lower the value. If it points to the 
		 * RIGHT, the button will increase the value.
		 * @param buttonSprite The sprite with which the button will be drawn
		 * @param buttonMask The sprite which is used for the button's collision checking
		 * @param area The area where the object is placed to
		 */
		public OptionBarButton(int x, int y, int direction, Sprite buttonSprite, 
				Sprite buttonMask, Area area)
		{
			super(x, y, DepthConstants.NORMAL, buttonSprite, buttonMask, area);
			
			this.direction = direction;
			
			if (this.direction == LEFT)
				this.setXScale(-1);
		}

		
		// IMPLEMENTENTED METHODS ------------------------------------------

		@Override
		public boolean listensMouseEnterExit()
		{
			return true;
		}

		@Override
		public void onMousePositionEvent(MousePositionEventType eventType, 
				Point2D.Double mousePosition, double eventStepTime)
		{
			// Changes sprite index when mouse enters or exits the button
			if (eventType == MousePositionEventType.ENTER)
				getSpriteDrawer().setImageIndex(1);
			else if (eventType == MousePositionEventType.EXIT)
				getSpriteDrawer().setImageIndex(0);
		}
		
		@Override
		public boolean isVisible()
		{
			if (!super.isVisible())
				return false;
			
			// If the value is already at maximum / minimum, doesn't even 
			// show the button
			if ((this.direction == RIGHT && IntegerOptionBar.this.value == 
					IntegerOptionBar.this.maxValue) || (this.direction == LEFT && 
					IntegerOptionBar.this.value == IntegerOptionBar.this.minValue))
				return false;
			
			return true;
		}


		@Override
		public void onMouseButtonEvent(MouseButton button, 
				MouseButtonEventType eventType, Point2D.Double mousePosition, 
				double eventStepTime)
		{
			if (button == MouseButton.LEFT && 
					eventType == MouseButtonEventType.PRESSED)
			{
				if(this.direction == LEFT)
				{
					//The arrow points to the left
					if(IntegerOptionBar.this.value>IntegerOptionBar.this.minValue)
						IntegerOptionBar.this.value = IntegerOptionBar.this.value -1;
				}
				else
				{
					//The arrow points to the right
					if(IntegerOptionBar.this.value<IntegerOptionBar.this.maxValue)
						IntegerOptionBar.this.value = IntegerOptionBar.this.value +1;
				}
			}
		}
	}
}
