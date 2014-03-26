package utopia_gameobjects;


import utopia_handleds.Handled;
import utopia_worlds.Area;

/**
 * GameObject represents any game entity. All of the gameobjects can be created, 
 * handled and killed. Pretty much any visible or invisible object in a game 
 * that can become an 'object' of an action should inherit this class.
 *
 * @author Mikko Hilpinen.
 * @since 11.7.2013.
 */
public abstract class GameObject implements Handled
{
	// ATTRIBUTES	-----------------------------------------------------
	
	private boolean dead;
	
	
	// CONSTRUCTOR	-----------------------------------------------------
	
	/**
	 * Creates a new gameobject that is alive until it is killed
	 * @param area The area where the GameObject will reside
	 */
	public GameObject(Area area)
	{
		// Initializes attributes
		this.dead = false;
		
		// Adds the object to the handler(s)
		if (area != null)
			area.addObject(this);
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------
	
	@Override
	public boolean isDead()
	{
		return this.dead;
	}

	@Override
	public void kill()
	{
		this.dead = true;
	}
	
	@Override
	public String toString()
	{
		String status = "alive ";
		if (isDead())
			status = "dead ";
		return status + getClass().getName();
	}
}
