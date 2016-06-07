package com.th3shadowbroker.AtMessage.Loaders;

import com.th3shadowbroker.AtMessage.Backward.WhisperMessage_1_9_2;
import com.th3shadowbroker.AtMessage.Events.AddToCache;
import com.th3shadowbroker.AtMessage.Events.RemoveFromCache;
import com.th3shadowbroker.AtMessage.Events.UpdateNotification;
import com.th3shadowbroker.AtMessage.Events.WhisperMessage;
import com.th3shadowbroker.AtMessage.Objects.ServerVersionCheck;
import com.th3shadowbroker.AtMessage.Objects.ServerVersionCheck.ServerVersion;
import com.th3shadowbroker.AtMessage.main;

public class Events {
    
    public main plugin;
    
    //Construction
    public Events( main loaderClass )
    {
        
        this.plugin = loaderClass;
        
        registerEvents();
        
    }
    
    //LoadUp sequence
    private void registerEvents()
    {
        try {
            
            //Setup the AtMessage-Message listener
            if ( ServerVersionCheck.getCurrentVersion() == ServerVersion.R1 ) //If version is 1.9.2
            {
                
                plugin.getServer().getPluginManager().registerEvents( new WhisperMessage_1_9_2(this) , plugin);
                System.out.println( plugin.ConsolePrefix + "Backward compatibility activated for 1.9.2" );
                
            } else {                                                          //If version is 1.9.4
                
                plugin.getServer().getPluginManager().registerEvents( new WhisperMessage(this) , plugin);
                
            }
            
            //Send update notification if update is available
            plugin.getServer().getPluginManager().registerEvents( new UpdateNotification(this) , plugin);
            
            //Add joined players to cache
            plugin.getServer().getPluginManager().registerEvents( new AddToCache(this) , plugin);
            
            //Remove already cached players on quit
            plugin.getServer().getPluginManager().registerEvents( new RemoveFromCache(this) , plugin);
            
            //Send spy-messages to spectators
            //plugin.getServer().getPluginManager().registerEvents( new SendToSpectator(this) , plugin);
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
}
