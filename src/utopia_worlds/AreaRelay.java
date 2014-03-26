package utopia_worlds;

import java.util.HashMap;

/**
 * AreaRelay keeps track of multiple areas and offers them for other objects.
 * 
 * @author Mikko Hilpinen
 * @since 9.3.2014
 */
public class AreaRelay
{
	// ATTRIBUTES	------------------------------------------------------
	
	private HashMap<String, Area> areas;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates an empty areaRelay. The areas need to be added separately
	 * @see #addArea(String, Area)
	 */
	public AreaRelay()
	{
		// Initializes attributes
		this.areas = new HashMap<String, Area>();
	}

	
	// GETTERS & SETTERS	----------------------------------------------
	
	/**
	 * Returns an area with the given name
	 * @param areaName The name of the area to be returned
	 * @return An area with the given name or null if no such area exists.
	 */
	public Area getArea(String areaName)
	{
		if (!this.areas.containsKey(areaName))
		{
			System.err.println("There is no area named " + areaName);
			return null;
		}
		
		return this.areas.get(areaName);
	}
	
	
	// OTHER METHODS	--------------------------------------------------
	
	/**
	 * Adds a new area to the collection
	 * 
	 * @param areaName The name of the new area
	 * @param area The new area to be added
	 */
	public void addArea(String areaName, Area area)
	{
		if (area == null)
			return;
		
		this.areas.put(areaName, area);
	}
	
	/**
	 * Ends functions in all the areas. This might be useful to do between 
	 * area transitions
	 */
	public void endAllAreas()
	{
		for (Area area : this.areas.values())
		{
			area.end();
		}
	}
}
