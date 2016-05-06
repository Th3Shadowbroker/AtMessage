package com.th3shadowbroker.AtMessage.Commands;

import com.th3shadowbroker.AtMessage.Loaders.Commands;
import com.th3shadowbroker.AtMessage.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAMsg implements CommandExecutor {

    protected Commands loader;
    protected main plugin;
    
    public CommandAMsg( Commands loaderClass )
    {
        
        this.loader = loaderClass;
        this.plugin = loaderClass.plugin;
        
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        
        if ( sender instanceof Player ) {
        
            
            
            Player p = (Player) sender;
        
            if ( cmd.getName().equalsIgnoreCase("AMsg") ) { //If command is /amsg
                if ( p.hasPermission( "AtMsg.admin" ) ){ //Player has permission ?
                    
                    if ( args.length == 0 ) { //No arguments
                    
                        //Send plugin info
                        p.sendMessage( plugin.PluginPrefix + ChatColor.BLUE + "@Message created by " + plugin.getDescription().getAuthors() );
                        p.sendMessage( plugin.PluginPrefix + ChatColor.BLUE + "Current version " + plugin.getDescription().getVersion() );
                        
                        return true;
                        
                    } else if( args.length == 1 ) { //One argument
                        
                        if ( args[0].equalsIgnoreCase("reload") )
                        {
                            
                            //If argument is reload
                            plugin.saveConfig();
                            plugin.reloadConfig();
                            p.sendMessage( plugin.PluginPrefix + ChatColor.GREEN + "Config reloaded successfully" );
                         
                            return true;
                            
                        } else { 
                            
                            //Invalid arguments
                            p.sendMessage( plugin.PluginPrefix + ChatColor.RED + "Invalid arguments" );
                            p.sendMessage( plugin.PluginPrefix + ChatColor.RED + "Please use " + ChatColor.AQUA + "/amsg [reload]" );
                            
                            return true; 
                        
                        }
                        
                    }
                    
                  //No permission  
                } else { p.sendMessage( plugin.PluginPrefix + ChatColor.RED + "You don't have permission to do that!" ); }
            }
            
            
            
        } else {
        
          //If sender != Player
          System.out.println( plugin.ConsolePrefix + "Please use the /amsg command inGame" );
            
        }
        
        return false;
        
    }
    
}
