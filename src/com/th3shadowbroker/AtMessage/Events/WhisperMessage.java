package com.th3shadowbroker.AtMessage.Events;

import com.th3shadowbroker.AtMessage.Cache.AtMessagePlayer;
import com.th3shadowbroker.AtMessage.Cache.PlayerState;
import com.th3shadowbroker.AtMessage.Cache.SpectatorCache;
import com.th3shadowbroker.AtMessage.Etc.AtMessageSendEvent;
import com.th3shadowbroker.AtMessage.Loaders.Events;
import com.th3shadowbroker.AtMessage.Objects.CommandSuggestion;
import com.th3shadowbroker.AtMessage.Objects.MultipleTargets;
import com.th3shadowbroker.AtMessage.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class WhisperMessage implements Listener {
    
    protected Events loader; //Loader-class
    protected main plugin; //Plugin main-class
    
    protected FileConfiguration config; //Plugin-config
    
    protected String ToSender; //Text format ToSender
    protected String ToTarget; //Text format ToTarget
    
    protected String Color; //Text color
    protected String Separator; //Text Separator
    
    public WhisperMessage( Events loaderClass )
    {
        
        this.loader = loaderClass;
        this.plugin = loaderClass.plugin;
        
        this.config = loaderClass.plugin.getConfig();
        
        this.ToSender = loaderClass.plugin.getConfig().getString( "MessageToSender" ).replaceAll( "&" , "§" );
        this.ToTarget = loaderClass.plugin.getConfig().getString( "MessageToTarget" ).replaceAll( "&" , "§" );
        
        this.Color = loaderClass.plugin.getConfig().getString( "MessageColor" ).replaceAll( "&" , "§" );
        this.Separator = loaderClass.plugin.getConfig().getString( "MessageSeparator" ).replaceAll( "&" , "§" );
        
        this.loadFormat();
        
    }
    
    //Load text-format
    private void loadFormat()
    {
        
        String ToSender = this.ToSender;
        ToSender = ToSender.replaceAll( "SEPARATOR" , Separator );
        this.ToSender = ToSender;  
        
        String ToTarget = this.ToTarget;
        ToTarget = ToTarget.replaceAll( "SEPARATOR" , Separator );
        this.ToTarget = ToTarget;
        
    }
    
    @EventHandler
    public void messageSend( AsyncPlayerChatEvent e )
    {
     
        String[] message = e.getMessage().split(" "); //Message in arguments
        String raw_message = String.valueOf( e.getMessage().replaceAll( message[0] , "" ) ); //Message without @<Playername>
        Player player = e.getPlayer(); //Message sender
        
        if ( player.hasPermission( "AtMsg.use" ) )
        {
            
            if ( ! MultipleTargets.isMultiple( message[0] ) )
            {
            
                try{
            
                        if ( message[0].startsWith("@") ) { //Check for requirements

                            if ( Bukkit.getPlayer( message[0].replaceFirst("@", "") ) != null ) //Try to get target
                            { 

                                if ( message.length > 1 ) {

                                    Player target = Bukkit.getPlayer( message[0].replaceFirst("@", "") ); //Player's whisper target

                                    CommandSuggestion target_cmdS = new CommandSuggestion( this.ToTarget.replaceAll( "TARGET" , player.getName() ) + this.Color + raw_message , "@" + player.getName() + " " ); 
                                    target_cmdS.sendToPlayer(target); //Send message to target

                                    CommandSuggestion sender_cmdS = new CommandSuggestion( this.ToSender.replaceAll( "TARGET" , target.getName() ) + this.Color + raw_message , "@" + target.getName() + " " );
                                    sender_cmdS.sendToPlayer(player); //Send message to sender
                                                                       
                                    Bukkit.getServer().getPluginManager().callEvent( new AtMessageSendEvent( player , target , this.Color + raw_message ) );
                                    
                                    //Send send message to spectators
                                    try {
                                        
                                        SpectatorCache spectators = plugin.getSpecCache();
                                    
                                        for ( AtMessagePlayer spectator : spectators.getCachedPlayersWithState( PlayerState.SPECTATOR ) )
                                        {

                                            Player specPlayer = spectator.getPlayer();

                                            specPlayer.sendMessage( plugin.PluginPrefix + plugin.getConfig().getString( "MessageToSpectator" ).replaceAll( "&" , "§" )
                                                                                                                                               .replaceAll( "TARGET" , target.getName() )
                                                                                                                                               .replaceAll( "SENDER" , player.getName() )
                                                                                                                                               .replaceAll( "SEPARATOR" , plugin.getConfig().getString( "MessageSeparator" ) )
                                            + this.Color + raw_message        
                                            );

                                        }
                                        
                                    }   catch   ( Exception ex )    {
                                        
                                        //Do nothing
                                        
                                    }
                                    
                                    e.setCancelled(true);

                                } else {

                                    player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Please use " + ChatColor.AQUA + "@<Player> <message>");
                                    e.setCancelled(true);

                                }

                            } else {

                                player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Player not found/online"); //If player is offline
                                e.setCancelled(true);
                            }

                    }
            
                } catch ( Exception ex ) {

                    //Something wrong happend ;(
                    player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Something went wrong :(");
                    player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Please use " + ChatColor.AQUA + "@<Player> <message>");
                    e.setCancelled(true);
                    
                }
                
            } else { //Multiple targets given
               
                try {
                    
                    if ( message.length > 1 )
                    {
                        
                        MultipleTargets targets = new MultipleTargets( message[0] );
                    
                        String[] targetList = targets.getTargets();

                        for ( int i = 0; i != targetList.length; i++ )
                        {
                            if ( Bukkit.getServer().getPlayer( targetList[i] ) != null )
                            {

                                Player target = Bukkit.getPlayer( targetList[i] ); //Player's whisper target

                                CommandSuggestion target_cmdS = new CommandSuggestion( this.ToTarget.replaceAll( "TARGET" , player.getName() ) + this.Color + raw_message , "@" + player.getName() + " " ); 
                                target_cmdS.sendToPlayer(target); //Send message to target

                                CommandSuggestion sender_cmdS = new CommandSuggestion( this.ToSender.replaceAll( "TARGET" , target.getName() ) + this.Color + raw_message , "@" + target.getName() + " " );
                                sender_cmdS.sendToPlayer(player); //Send message to sender

                                //Send send message to spectators
                                try {
                                        
                                        SpectatorCache spectators = plugin.getSpecCache();
                                    
                                        for ( AtMessagePlayer spectator : spectators.getCachedPlayersWithState( PlayerState.SPECTATOR ) )
                                        {

                                            Player specPlayer = spectator.getPlayer();

                                            specPlayer.sendMessage( plugin.PluginPrefix + plugin.getConfig().getString( "MessageToSpectator" ).replaceAll( "&" , "§" )
                                                                                                                                               .replaceAll( "TARGET" , target.getName() )
                                                                                                                                               .replaceAll( "SENDER" , player.getName() )
                                                                                                                                               .replaceAll( "SEPARATOR" , plugin.getConfig().getString( "MessageSeparator" ) )
                                            + this.Color + raw_message        
                                            );

                                        }
                                        
                                }   catch   ( Exception ex )    {
                                        
                                        //Do nothing
                                        
                                }
                                
                                e.setCancelled(true);
                                
                                Bukkit.getServer().getPluginManager().callEvent( new AtMessageSendEvent( player , target , this.Color + raw_message ) );

                            }else {

                                player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Player " + targetList[i] + " not found/online"); //If player is offline
                                e.setCancelled(true);
                                break;

                            }
                        }
                        
                    } else {

                        player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Please use " + ChatColor.AQUA + "@<Player> <message>");
                        e.setCancelled(true);

                    }
                    
                    
                } catch ( Exception ex ) {
                    
                    //Something wrong happend ;(
                    player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Something went wrong :(");
                    player.sendMessage(plugin.PluginPrefix + ChatColor.RED + "Please use " + ChatColor.AQUA + "@<Player> <message>");
                    e.setCancelled(true);
                    
                }
                
            }//End of multiple targets
            
        } else {
            
            /*
                DO NOTHING
            */
            
        }

    }
    
}
