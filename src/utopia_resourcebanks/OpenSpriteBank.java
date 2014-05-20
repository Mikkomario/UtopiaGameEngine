package utopia_resourcebanks;

import java.io.FileNotFoundException;
import java.util.ArrayList;



/**
 * This SpriteBank, unlike other SpriteBanks can be put into a bank. Also, 
 * the content of the SpriteBank can be defined by providing a number of 
 * commands upon the creation of the bank.
 *
 * @author Mikko Hilpinen.
 *         Created 26.8.2013.
 */
public class OpenSpriteBank extends SpriteBank implements OpenBank
{
	// ATTRIBUTES	-----------------------------------------------------
	
	private ArrayList<String> commands;
	
	
	// CONSTRUCTOR	-----------------------------------------------------
	
	/**
	 * Creates a new OpenSpriteBank that will initialize itself using the 
	 * given information
	 *
	 * @param creationcommands Creation commands should follow the following 
	 * style:<br>
	 * spritename#filename <i>(data/ is automatically included)</i>#image 
	 * number(optional)#originx(optional, -1 means center)#originy(optional, -1 means center)
	 * #forcedWidth(optional)#forcedHeight(optional)
	 */
	public OpenSpriteBank(ArrayList<String> creationcommands)
	{
		// Initializes attributes
		this.commands = creationcommands;
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------
	
	@Override
	public void createSprites() throws FileNotFoundException
	{
		// Creates the sprites by going through the commands
		for (int i = 0; i < this.commands.size(); i++)
		{
			String command = this.commands.get(i);
			String[] parts = command.split("#");
			
			// Checks that there are enough arguments
			if (parts.length < 2)
			{
				System.err.println("Couldn't load a sprite. Line " + command + 
						"doensn't have enough arguments");
				continue;
			}
			
			int imgnumber = 1;
			int originx = -1;
			int originy = -1;
			int width = 0;
			int height = 0;
			
			try
			{
				if (parts.length > 2)
					imgnumber = Integer.parseInt(parts[2]);
				if (parts.length > 3)
					originx = Integer.parseInt(parts[3]);
				if (parts.length > 4)
					originy = Integer.parseInt(parts[4]);
				if (parts.length > 6)
				{
					width = Integer.parseInt(parts[5]);
					height = Integer.parseInt(parts[6]);
				}
			}
			catch(NumberFormatException nfe)
			{
				System.err.println("Couldn't load a sprite. Line " + command 
						+ " contained invalid information.");
				continue;
			}
			
			createSprite(parts[1], imgnumber, originx, originy, parts[0], width, 
					height);
		}
	}
}
