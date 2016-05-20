package com.th3shadowbroker.AtMessage.Loaders;

import com.th3shadowbroker.AtMessage.Backward.WhisperMessage_1_9_2;
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
            
            if ( ServerVersionCheck.getCurrentVersion() == ServerVersion.R1 ) //If version is 1.9.2
            {
                
                plugin.getServer().getPluginManager().registerEvents( new WhisperMessage_1_9_2(this) , plugin);
                System.out.println( plugin.ConsolePrefix + "Backward compatibility activated for 1.9.2" );
                
            } else {                                                          //If version is 1.9.4
                
                plugin.getServer().getPluginManager().registerEvents( new WhisperMessage(this) , plugin);
                
            }
            
            plugin.getServer().getPluginManager().registerEvents( new UpdateNotification(this) , plugin);
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
}
