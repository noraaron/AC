import java.util.Hashtable;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
 
public class AaronCoinsPlugin extends JavaPlugin
{
	public static Hashtable<Player, Long> playerCoins;
    public static final Long DEFAULT_COIN_HANDOUT = 10000L;
    
	private AaronCoinsCommandExecutor exe;
	private NorAaronCoinsCommandExecutor nexe;
       
    public void onDisable()
    {
    	//TODO Serialize Hashtable
    }
   
    public void onEnable()
    {
    	//TODO Deserialize the hashtable from external file.
    	exe = new AaronCoinsCommandExecutor(this);
		this.getCommand("aaroncoins").setExecutor(exe);
		this.getCommand("noraaron").setExecutor(nexe);
    }

}
