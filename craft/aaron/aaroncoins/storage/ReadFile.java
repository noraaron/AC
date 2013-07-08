package craft.aaron.aaroncoins.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.UUID;
/**
 * This class deserializes the stored accounts and returns the Hashtable contained inside.
 * @author collielimabean
 *
 */
public class ReadFile 
{
	
	private File deserialize;
	
	public ReadFile(File ser)
	{
		deserialize = ser;
	}
	
	public Hashtable<UUID,Long> getAccounts() throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream(deserialize);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		//unavoidable warning
		Hashtable<UUID,Long> readObject = (Hashtable<UUID,Long>) ois.readObject();
		ois.close();
		
		return readObject;
		
	}
	
}
