
package com.th3shadowbroker.AtMessage.Objects;

import org.bukkit.Bukkit;

public class ServerVersionCheck {

    public enum ServerVersion
    {
        R1,
        R2
    }
    
    public static ServerVersion getCurrentVersion()
    {
        if ( Bukkit.getServer().getVersion().contains( "1.9.2" ) )
        {
            return ServerVersion.R1;
        
        } else {
        
            return ServerVersion.R2;
            
        }
    }
    
}
