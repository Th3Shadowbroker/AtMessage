
package com.th3shadowbroker.AtMessage.Objects;

import org.bukkit.Bukkit;

public class ServerVersionCheck {

    public enum ServerVersion
    {
        SERVER_1_9_2,
        SERVER_1_9_4,
        SERVER_1_10_0;
    }
    
    public static ServerVersion getCurrentVersion()
    {
        if ( Bukkit.getServer().getVersion().contains( "1.9.2" ) )
        {
            return ServerVersion.SERVER_1_9_2;
        } 
        
        if ( Bukkit.getServer().getVersion().contains( "1.9.4" ) )
        {
            return ServerVersion.SERVER_1_9_4;
        }
        
        if ( Bukkit.getServer().getVersion().contains( "1.10" ) )
        {
            return ServerVersion.SERVER_1_10_0;
        }
        
        return null;
    }
    
}
