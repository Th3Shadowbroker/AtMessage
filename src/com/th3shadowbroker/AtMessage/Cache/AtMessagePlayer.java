package com.th3shadowbroker.AtMessage.Cache;

import java.util.UUID;
import org.bukkit.entity.Player;

public class AtMessagePlayer {
  
    private final Player player;
    private final UUID uid;
    private final String name;
    private PlayerState state;
    
    //Construction
    public AtMessagePlayer( Player p )
    {
     
        this.player = p;
        
        this.uid = p.getUniqueId();
        
        this.name = p.getName();
        
        this.state = PlayerState.NORMAL;
        
    }
    
    //Get players current cache-state
    public PlayerState getState()
    {
        
        return state;
        
    }
    
    //Set players state
    public void setState( PlayerState state )
    {
        
        this.state = state;
        
    }
    
    //Get players uuid
    public UUID getUUID()
    {
        
        return uid;
        
    }
    
    //Get players name
    public String getPlayerName()
    {
        
        return name;
        
    }
    
    //Get the whole player object
    public Player getPlayer()
    {
        
        return player;
        
    }
    
}
