package com.th3shadowbroker.AtMessage.Events;

import com.th3shadowbroker.AtMessage.Loaders.Events;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateNotification  implements Listener {
 
    protected Events loader;
    
    //Construction
    public UpdateNotification( Events loaderClass )
    {
        
        this.loader = loaderClass;
        
    }
    
    //If update available
    @EventHandler
    public void updateCheckOnJoin( PlayerJoinEvent e )
    {
        if ( loader.plugin.config.getBoolean("CheckForUpdates") )
        {
            if ( loader.plugin.git.updateAvailable() )
            {
                
                Player p = e.getPlayer();
        
                if ( p.isOp() | p.hasPermission( "AtMsg.admin" ) )
                {

                    loader.plugin.git.sendInGameNotification( p );

                }
                
            }
        }
    }
    
}
