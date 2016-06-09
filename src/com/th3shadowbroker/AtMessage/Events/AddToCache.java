package com.th3shadowbroker.AtMessage.Events;

import com.th3shadowbroker.AtMessage.Cache.AtMessagePlayer;
import com.th3shadowbroker.AtMessage.Cache.SpectatorCache;
import com.th3shadowbroker.AtMessage.Exceptions.AlreadyInCacheException;
import com.th3shadowbroker.AtMessage.Loaders.Events;
import com.th3shadowbroker.AtMessage.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AddToCache implements Listener {
    
    private final Events loader;
    private final main plugin;
    
    //Construction
    public AddToCache( Events loaderClass )
    {
        
        this.loader = loaderClass;
        
        this.plugin = loaderClass.plugin;
        
    }
    
    //Add players to cache on join
    @EventHandler( priority = EventPriority.MONITOR )
    public void addPlayerToCache( PlayerJoinEvent ev )
    {
        
        Player p = ev.getPlayer();
        
        try {
            
            SpectatorCache cache = plugin.getSpecCache();
            
            cache.createCacheEntry( new AtMessagePlayer( p ) );
            
            if ( plugin.debugModeIsEnabled() )
            {
                System.out.println( plugin.ConsolePrefix + "Player " + p.getUniqueId().toString() + " loaded to cache !" );
            }
            
        } catch (AlreadyInCacheException ex) {
            
            System.out.println( plugin.ConsolePrefix + "Error while caching !" );
            ex.printStackTrace();
            
        }
        
    }
    
}
