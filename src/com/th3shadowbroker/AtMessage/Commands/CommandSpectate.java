package com.th3shadowbroker.AtMessage.Commands;

import com.th3shadowbroker.AtMessage.Cache.AtMessagePlayer;
import com.th3shadowbroker.AtMessage.Cache.PlayerState;
import com.th3shadowbroker.AtMessage.Cache.SpectatorCache;
import com.th3shadowbroker.AtMessage.Exceptions.NotInCacheException;
import com.th3shadowbroker.AtMessage.Loaders.Commands;
import com.th3shadowbroker.AtMessage.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpectate implements CommandExecutor {
    
    private final Commands loader;
    private final main plugin;
    
    //Construction
    public CommandSpectate( Commands loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.plugin = loaderClass.plugin;
        
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        if ( sender instanceof Player )
        {   
            
            Player p = (Player) sender;
            if ( p.hasPermission( "AtMsg.spectate" ) )
            {
                if ( cmd.getName().equalsIgnoreCase( "spectate" ) )
                {
                    
                    try {
                        
                        SpectatorCache cache = plugin.getSpecCache();
                        
                        if ( cache.getCacheEntry( new AtMessagePlayer( p ) ).getState() == PlayerState.NORMAL )
                        {
                            
                            cache.updateCacheEntry( new AtMessagePlayer( p ) , PlayerState.SPECTATOR );
                            p.sendMessage( plugin.PluginPrefix + ChatColor.GREEN + "Spectating activated" );
                            return true;
                            
                        }  else  {
                           
                            cache.updateCacheEntry( new AtMessagePlayer( p ) , PlayerState.NORMAL );
                            p.sendMessage( plugin.PluginPrefix + ChatColor.RED + "Spectating disabled"  );
                            return true;
                            
                        }
                        
                    } catch (NotInCacheException ex) {
                        
                        p.sendMessage( plugin.PluginPrefix + "§cThere was an exception. Please check console." );
                        
                        ex.printStackTrace();
                        
                    }
                    
                }
            }   
            
        }   else    {
            
            System.out.println( plugin.ConsolePrefix + "Please use inGame commands !" );
            return false;
            
        }
        
        return false;
        
    }
    
}
