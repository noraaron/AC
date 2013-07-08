package craft.aaron.aaroncoins;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class AaronCoinsCommandExecutor implements CommandExecutor
{
	
    public static final String[][] COMMANDS_LIST_DESCRIPTIONS =
    	{
    		{"/aaroncoins", "Root command"},
    		{"/aaroncoins balance", "Returns how many AaaronCoins you have"},
    		{"/aaroncoins give <playername> <number>", "Gives some AaronCoins to selected player"},
    		{"/aaroncoins remove <playername> <number>", "Removes some AaronCoins from selected player"},
    		{"/aaroncoins help", "Lists all commands and their descriptions."}
    	};
    
    private AaronCoinsPlugin plugin;
    
    public AaronCoinsCommandExecutor(AaronCoinsPlugin plugin)
    {
    	this.plugin = plugin;
    }
    
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		
		if(sender instanceof Player)
		{
	         Player player = (Player) sender;
	         
	         if(cmd.getName().equalsIgnoreCase("aaroncoins") && player.isOnline())
	         {
	        	 if(args.length < 1)
	        	 {
	        		 player.sendMessage(buildHelpList());
	        	 }
	        	 
	        	 else if(args[0].equalsIgnoreCase("balance"))
	        	 {
	        		 Long coins = AaronCoinsPlugin.playerCoins.get(player.getUniqueId());
	        		 
	        		 if(coins == null)
	        		 {
	        			 AaronCoinsPlugin.playerCoins.put(player.getUniqueId(), AaronCoinsPlugin.DEFAULT_COIN_HANDOUT);
	        			 player.sendMessage(ChatColor.GREEN + "You have: " + 10000 + " AaronCoins!");
	        		 }
	        		 
	        		 else
	        		 {
		        		 player.sendMessage(ChatColor.GREEN + "You have: " + coins + " AaronCoins!");
	        		 }

	        	 }
	        	 
	        	 else if(args[0].equalsIgnoreCase("give") && player.hasPermission("AaronCoins.give"))
	        	 {
	        		 if(args.length != 3)
	        		 {
	        			 player.sendMessage("Usage: " + COMMANDS_LIST_DESCRIPTIONS[1][0]);
	        		 }
	        		 
	        		 else
	        		 {
	        			 String playerName = args[1];
	        			 Long input = Long.parseLong(args[2]);
	        			 
	        			 if(input < 0)
	        			 {
	        				 player.sendMessage("Please use the " + COMMANDS_LIST_DESCRIPTIONS[3][0] + " command.");
	        			 }
	        			 
	        			 else if(input > Long.MAX_VALUE)
	        			 {
	        				 player.sendMessage("Enter a smaller value please.");
	        			 }
	        			 
	        			 else
	        			 {
	        				 giveAaronCoins(playerName, input);
	        			 }
	        			 
	        		 }
	        	 }
	        	 
	        	 else if(args[0].equalsIgnoreCase("remove"))
	        	 {
	        		 
	        		 if(args.length != 3)
	        		 {
	        			 player.sendMessage("Usage: " + COMMANDS_LIST_DESCRIPTIONS[3][0]);
	        		 }
	        		 
	        		 else
	        		 {
	        			 String playerName = args[1];
	        			 Long input = Long.parseLong(args[2]);
	        			 	        			 
	        			 if(input < 0)
	        			 {
	        				 player.sendMessage("Please use the " + COMMANDS_LIST_DESCRIPTIONS[2][0] + " command.");
	        			 }
	        			 
	        			 else if(input < Long.MIN_VALUE)
	        			 {
	        				 player.sendMessage("Enter a larger value please.");
	        			 }
	        			 
	        			 else
	        			 {
	        				 giveAaronCoins(playerName, -input);
	        			 }
	        		 }
	        		 	        
	        	 }
	        	 
	        	 else if(args[0].equalsIgnoreCase("help"))
	        	 {
	        		 player.sendMessage(buildHelpList());
	        	 }
	         }
	         
		}
		
		else
		{
			//Actions or commands for the console to execute
		}
         
		return true;
	}
	
	/**
	 * <p>
	 * Gives target player (regardless if online) number of coins.
	 * # of coins may be negative, signaling a removal of coins.
	 *</p>
	 * @param targetName
	 * @param coins
	 * @return
	 */
	private void giveAaronCoins(String targetName, Long coins)
	{
		OfflinePlayer[] list = plugin.getServer().getOfflinePlayers();
		
		for(OfflinePlayer player : list)
		{
			if(player.getName().equalsIgnoreCase(targetName))
			{
				Player p = player.getPlayer();
				
				if(AaronCoinsPlugin.playerCoins.contains(p.getUniqueId()))
				{
					Long prevBalance = AaronCoinsPlugin.playerCoins.get(p.getUniqueId());
					
					Long newCount = prevBalance + coins;
					
					if(newCount < 0)
					{
						newCount = 0L;
					}
					
					else if(newCount >= Long.MAX_VALUE)
					{
						newCount = Long.MAX_VALUE;
					}
					
					AaronCoinsPlugin.playerCoins.put(p.getUniqueId(), newCount);

				}
				
				else
				{
					//in case some player is not in the account list, add them
					AaronCoinsPlugin.playerCoins.put(p.getUniqueId(), AaronCoinsPlugin.DEFAULT_COIN_HANDOUT + coins);
				}
			}
		}
		
		
	}
	
	private String buildHelpList()
	{
		
		StringBuilder sb = new StringBuilder();
		
		for(String[] command : COMMANDS_LIST_DESCRIPTIONS)
		{
			for(String element : command)
			{
				sb.append(element);
				sb.append(" ");
			}
			
			sb.append("\n");
			
		}
		
		return sb.toString();
	}
	
}
