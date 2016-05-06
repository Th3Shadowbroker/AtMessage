package com.th3shadowbroker.AtMessage.Events;

import com.th3shadowbroker.AtMessage.Loaders.Events;
import com.th3shadowbroker.AtMessage.main;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class AutoComplete implements Listener {
    
    protected Events loader;
    protected main plugin;
    
    //Construction
    public AutoComplete( Events loaderClass )
    {
        
        this.loader = loaderClass;
        this.plugin = loaderClass.plugin;
        
    }
    
    @EventHandler( priority = EventPriority.MONITOR, ignoreCancelled = true )
    public void onAutoComplete( PlayerChatTabCompleteEvent e ) //Access autocomplete sequence
    {
    
        Player p = e.getPlayer();
        
        String[] message = e.getChatMessage().split(" ");
        String currentName = message[0].replaceAll( "@" , "" );
        String raw_message = e.getChatMessage().replaceAll( message[0] , "" );
        
        String lastInput = e.getLastToken();
        Collection<String> comps = e.getTabCompletions();
        
        if ( message[0].startsWith("@") )
        {
            try {
                            
                //Still under construction
                
            } catch ( Exception ex ) {
                
                //Nothing
                
            }
        }
        
    }
    
}
