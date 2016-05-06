package com.th3shadowbroker.AtMessage.Loaders;

import com.th3shadowbroker.AtMessage.Events.WhisperMessage;
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
            
            plugin.getServer().getPluginManager().registerEvents( new WhisperMessage(this) , plugin);
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
}
