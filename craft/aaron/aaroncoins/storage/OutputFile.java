package craft.aaron.aaroncoins.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class OutputFile
{
	
	private Object output;
	private File save;
	
	public OutputFile(Object out, File saveDirectory)
	{
		output = out;
		save = new File(saveDirectory.getAbsolutePath() + "/accounts.ser");
	}
	
	/**
	 * Stores the object in a .ser file. 
	 * @return true on success
	 */
	public boolean store()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(save);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(output);
			oos.flush();
			oos.close();
			
			return true;
		}
		
		catch(Exception e)
		{
			return false;
		}

		
	}
	

}
