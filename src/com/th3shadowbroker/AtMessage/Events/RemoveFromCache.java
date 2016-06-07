package com.th3shadowbroker.AtMessage.Events;

import com.th3shadowbroker.AtMessage.Cache.AtMessagePlayer;
import com.th3shadowbroker.AtMessage.Cache.SpectatorCache;
import com.th3shadowbroker.AtMessage.Exceptions.NotInCacheException;
import com.th3shadowbroker.AtMessage.Loaders.Events;
import com.th3shadowbroker.AtMessage.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class RemoveFromCache implements Listener {
    
    private final Events loader;
    private final main plugin;
    
    //Construction
    public RemoveFromCache( Events loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.plugin = loaderClass.plugin;
        
    }
    
    //Remove players from cache on quit
    @EventHandler( priority = EventPriority.MONITOR )
    public void removePlayerFromCache( PlayerQuitEvent ev )
    {
        
        try{
            
            SpectatorCache cache = plugin.getSpecCache();
            
            cache.removeCacheEntry( new AtMessagePlayer(ev.getPlayer()) );
            
        }   catch( NotInCacheException ex ) {
            
            System.out.println( plugin.ConsolePrefix + "Error while caching !" );
            
            ex.printStackTrace();
            
        }
        
    }
    
}
