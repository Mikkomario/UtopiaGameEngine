package utopia_resourcebanks;


import java.io.FileNotFoundException;

import utopia_graphic.Sprite;



/**
 * This class creates a group of sprites used in a project and gives them to the 
 * objects using them as needed.
 *
 * @author Gandalf.
 *         Created 7.12.2012.
 */
public abstract class SpriteBank extends AbstractBank
{    
    // ABSTRACT METHODS	-----------------------------------------------------
    
    /**
     * Here, sprites are created using the createsprite method
     * @throws FileNotFoundException If all of the sprites could not be loaded
     */
    public abstract void createSprites() throws FileNotFoundException;


    // IMPLEMENTED METHODS	-------------------------------------------------
    
    @Override
	protected Class<?> getSupportedClass()
    {
    	return Sprite.class;
    }
	
	@Override
	protected void initialize()
	{
		// Initializes the sprites
		try
        {
            createSprites();
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("All of the sprites could not be loaded!");
            fnfe.printStackTrace();
        }
	}
    
    
    // OTHER METHODS	-----------------------------------------------------

    /**
     * Returns a sprite from the spritebank
     *
     * @param spritename The name of the sprite looked
     * @return The sprite with the given name
     */
    public Sprite getSprite(String spritename)
    {
    	return (Sprite) getObject(spritename);
    }
    
    /**
     * Scales all the spites held in the bank. Note that this scaling is 
     * permanent and can only be undone with another scale.
     * 
     * @param xScale How much the width of the sprites is scaled
     * @param yScale How much the height of the sprites is scaled
     */
    public void scaleSprites(double xScale, double yScale)
    {
    	for (String spriteName : getContentNames())
    	{
    		getSprite(spriteName).scale(xScale, yScale);
    	}
    }
    
    /**
     * Loads and creates a sprite and adds it to the bank
     *
     * @param filename The filename of the sprite
     * @param imgnumber How many images does the sprite contain
     * @param originx What is the position of the sprite's origin on the x-axis (Pxl)
     * @param originy What is the position of the sprite's origin on the y-axis (Pxl)
     * @param name What is the name of the new sprite
     * @param forcedWidth How wide the sprite will be (optional, 0 if default)
     * @param forcedHeight How high the sprite will be (optional, 0 if default)
     * @throws FileNotFoundException If the image file could not be loaded
     */
    protected void createSprite(String filename, int imgnumber, int originx, 
    		int originy, String name, int forcedWidth, int forcedHeight)
    {
    	Sprite newsprite = new Sprite(filename, imgnumber, originx, originy);
        addObject(newsprite, name);
        
        if (forcedHeight != 0 && forcedWidth != 0)
        	newsprite.forceDimensions(forcedWidth, forcedHeight);
    }
}
