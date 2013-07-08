package craft.aaron.aaroncoins;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import craft.aaron.aaroncoins.storage.OutputFile;
import craft.aaron.aaroncoins.storage.ReadFile;
 
public class AaronCoinsPlugin extends JavaPlugin
{
	public static Hashtable<UUID, Long> playerCoins;
    public static final Long DEFAULT_COIN_HANDOUT = 10000L;
    
	private AaronCoinsCommandExecutor exe;
	private NorAaronCoinsCommandExecutor nexe;
       
    public void onDisable()
    {
    	
    	getLogger().info("Storing account balances...");
    	
    	//serialize accounts
    	OutputFile of = new OutputFile(playerCoins, new File("/plugins/AaronCoinsAccounts"));
    	boolean success = of.store();
    	
    	if(success)
    	{
    		getLogger().info("Storing complete");
    	}
    	
    	else
    	{
    		getLogger().info("WARNING: Storage of data failed!");
    	}

    }
   
    public void onEnable()
    {

    	getLogger().info("Attempting to retrive account balances...");
    	
    	String accountDirectory = "/plugins/AaronCoinsAccounts";
    	
    	File save = new File(accountDirectory);
    	
    	//initial creation
    	if(!save.exists() || !save.isDirectory())
    	{
    		getLogger().info("Could not find account balances, creating directory AaronCoinsAccounts...");
    		boolean success = save.mkdirs();
    		
    		if(success)
    		{
    			getLogger().info("New folder to hold AaronCoinsAccounts has been created. DO NOT MODIFY THIS FOLDER OR ANY FILES CONTAINED INSIDE.");
    		}
    		
    		else
    		{
    			save.mkdirs();
    			getLogger().info("New folder to hold AaronCoinsAccounts has been created. DO NOT MODIFY THIS FOLDER OR ANY FILES CONTAINED INSIDE.");
    		}
    		
    		playerCoins = new Hashtable<UUID,Long>();
    	}
    	
    	//directory exists...
    	else
    	{
    		
    		File store = new File(accountDirectory + "/Aaronaccounts.ser");
    		
    		//check if anything in directory to deserialize
    		if(store.exists())
    		{
    			//deserialize
    			ReadFile rf = new ReadFile(store);
    			
    			try 
    			{
					playerCoins = rf.getAccounts();
					getLogger().info("Retrieval successful.");
				} 
    			
    			catch (ClassNotFoundException e) 
    			{
					getLogger().info("ClassNotFoundException @ line 74 during deserialization. Contact the dev!");
				}
    			
    			catch (IOException e) 
    			{
    				getLogger().info("IOException @ line 74 during deserialization. Contact the dev!");
				}
    			
    		}
    		
    		else
    		{
    			getLogger().info("No saved accounts - generating new accounts...");
    			playerCoins = new Hashtable<UUID, Long>();
    			getLogger().info("Complete.");
    		}
    		
    	}
    	
    	exe = new AaronCoinsCommandExecutor(this);
    	nexe = new NorAaronCoinsCommandExecutor(this);
		this.getCommand("aaroncoins").setExecutor(exe);
		this.getCommand("noraaron").setExecutor(nexe);
    }

}
