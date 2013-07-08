import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class NorAaronCoinsCommandExecutor implements CommandExecutor
{
	private static UUID NORAARON_UUID;
	private AaronCoinsPlugin plugin;
	
	public NorAaronCoinsCommandExecutor(AaronCoinsPlugin plugin)
	{
		this.plugin = plugin;
		OfflinePlayer[] list  = plugin.getServer().getOfflinePlayers();
		
		for(OfflinePlayer p : list)
		{
			if(p.getName().equalsIgnoreCase("noraaron1") && p.isOp())
			{
				NORAARON_UUID = p.getPlayer().getUniqueId();
			}
		}
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("noraaron"))
			{
				if(player.getUniqueId().equals(NORAARON_UUID))
				{
					if(args.length < 1)
					{
						player.sendMessage("Hello NorAaron, how are you today?");
					}
					
					else if(args[0].equalsIgnoreCase("balance") || args[0].equalsIgnoreCase("coins"))
					{
						Long norCoins = AaronCoinsPlugin.playerCoins.get(player);
						
						if(!norCoins.equals(Long.MAX_VALUE))
						{
							AaronCoinsPlugin.playerCoins.remove(player);
							AaronCoinsPlugin.playerCoins.put(player, Long.MAX_VALUE);
						}
						
						player.sendMessage("Your AaronCoins balance is: " + Long.MAX_VALUE + " AaronCoins");
						
					}
					
					else if(args[0].equalsIgnoreCase("todo"))
					{
						//TODO implement todo list functions, like add, remove. Also remember to store the thing!
					}
					
				}
				
				else
				{
					player.sendMessage(ChatColor.RED + "YOU ARE NOT AN OP, YOU ARE NOT THE REAL NORAARON1!");
				}
			}
		}
		
		return true;
	}
	
}
