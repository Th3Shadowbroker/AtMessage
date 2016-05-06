package com.th3shadowbroker.AtMessage.Loaders;

import com.th3shadowbroker.AtMessage.Commands.CommandAMsg;
import com.th3shadowbroker.AtMessage.main;

public class Commands {
    
    public main plugin;
    
    //Construction
    public Commands( main loaderClass )
    {
        
        this.plugin = loaderClass;
        
        loadCommands();
        
    }
    
    //Load & register commands
    private void loadCommands()
    {
        try {
        
            plugin.getCommand("amsg").setExecutor( new CommandAMsg(this) );
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
    }
    
}
