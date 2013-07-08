import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
 
public class AaronCoins extends JavaPlugin{
        int counter = 10000;
       
        public void onDisable(){
               
        }
       
        public void onEnable(){
               
        }
       
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
                if(args.length < 1) {
                        getLogger().info("it works");
                }
                Player player = (Player)sender;
                Player targetPlayer = player.getServer().getPlayer(args [0]);
                if(commandLabel.equalsIgnoreCase("aaroncoins")){
                        if(player.hasPermission("AaronCoins.balance")){
                                player.sendMessage(ChatColor.GREEN + "You have: " + counter + " AaronCoins!");
                        }      
                }if(args.length == 0){
                                if(player.isOp()){
                                        player.sendMessage(ChatColor.BLACK + "====" + ChatColor.GREEN + "AaronCoins Help (Admins)");
                                        player.sendMessage(ChatColor.RED + "THE REGULAR PLAYERS DON'T HAVE ANY HELP SCREEN, SO DONT WORRY ABOUT THEM!");
                                        player.sendMessage("Command | Use");
                                        player.sendMessage("/AaronCoins give (number) (Player's name) | Give another player AaronCoins");
                                        player.sendMessage("/AaronCoins | Check your AaronCoin Balance");
                        }
                }if(player.isOp()){
                        if(args[0].equalsIgnoreCase("give")){
                                player.sendMessage(ChatColor.RED + "You can't give players above 10,000 AaronCoins unless you ask noraaron1!");
                        }if(args[1].equalsIgnoreCase("1")){
                                        if(player.getServer().getPlayer(args [0]) != null){
                                                counter++;
                                                targetPlayer.sendMessage(ChatColor.GREEN + "You have been given 10,000 AaronCoins from" + player.getName() + "or" + player.getCustomName() + "!");
                                                player.sendMessage("Added 10,000 to" + targetPlayer.getName() + "'s account of AaronCoins!");
                                        }
                                }if(args[2].equalsIgnoreCase("2")){
                                        counter++;
                                        counter++;
                                        targetPlayer.sendMessage(ChatColor.GREEN + "You have been given 20,000 AaronCoins from " + player.getName() + " or " + player.getCustomName() + "!");
                                        player.sendMessage("Added 20,000 to" + targetPlayer.getName() + "'s account of AaronCoins!");
                                }if(args[3].equalsIgnoreCase("3")){
                                        counter++;
                                        counter++;
                                        counter++;
                                        targetPlayer.sendMessage(ChatColor.GREEN + "You have been given 30,000 AaronCoins from " + player.getName() + " or " + player.getCustomName() + "!");
                                        player.sendMessage("Added 30,000 to" + targetPlayer.getName() + "'s account of AaronCoins!");
                                }
                        }if(commandLabel.equalsIgnoreCase("NoraaronCoins")){
                                if(args.length == 0){
                                        if(player.getName().equals("noraaron1")){
                                                if(player.isOp() == false){
                                                        player.sendMessage(ChatColor.RED + "YOU ARE NOT AN OP, YOU ARE NOT THE REAL NORAARON1!");
                                                }else{
                                               
                                                player.sendMessage("Hello noraaron1, How was your day?");{
                                                        if(args[4].equalsIgnoreCase("good")){
                                                                player.sendMessage(ChatColor.GREEN + "That is great! I fell happy for you!");                                           player.sendMessage(ChatColor.GREEN + "Okay! Now for buisness! What do you want to see: Your ToDo list or your Coins?");{
                                                                        if(args[6].equalsIgnoreCase("ToDo")){
                                                                                player.sendMessage(ChatColor.GREEN + "Okay! So your ToDo(s) for the plugin, AaronCoins, are: " +
                                                                                                " //ToDo: Put in more Money Selections" +
                                                                                                "//ToDo: Add More Commands For Admins");                                                       
                                                                                }
                                                }if(args[7].equalsIgnoreCase("bad")){
                                                        player.sendMessage(ChatColor.RED + "Aww! I fell Sorry for you!");
                                                        player.sendMessage(ChatColor.GREEN + "Okay! Now for buisness! What do you want to see: Your ToDo list or your Coins?");{
                                                                if(args[8].equalsIgnoreCase("ToDo")){
                                                                        player.sendMessage(ChatColor.GREEN + "Okay! So your ToDo(s) for the plugin, AaronCoins, are: " +
                                                                                        " //ToDo: Put in more Money Selections" +
                                                                                        "//ToDo: Add More Commands For Admins");                                                       
                                                                        }if(args[9].equalsIgnoreCase("Coins")){
                                                                                player.sendMessage("You Have " + counter + " AaronCoins in your account!");
                                                                        }
                                                        }
                                                }
                                                               
                                                }
                                        }
                                }
                                        }
                        return false;
                }      
                        }
                        return false;
        }
}
