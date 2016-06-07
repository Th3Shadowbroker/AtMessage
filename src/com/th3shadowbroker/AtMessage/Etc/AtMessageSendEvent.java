package com.th3shadowbroker.AtMessage.Etc;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AtMessageSendEvent extends Event {

    //Sender as player
    private final Player sender;
    
    //Target as Player
    private final Player target;
    
    //Message as String
    private final String message;
    
    //Construction and setup
    public AtMessageSendEvent( Player sender, Player target, String message )
    {
        
        this.sender = sender;
        
        this.target = target;
        
        this.message = message;
        
    }
    
    //==    Sender information functions    ==//
    
        //Get sender as player
        public Player getSender()
        {
            
            return sender;
            
        }
        
        //Get sender's name
        public String getSenderName()
        {
            
            return sender.getName();
            
        }
        
    //==    Target information functions    ==//
        
        //Get target as player
        public Player getTarget()
        {
            
            return target;
            
        }
        
        //Get target's name
        public String getTargetName()
        {
            
            return target.getName();
            
        }
    
    //==    Message information functions   ==//
        
        //Get message as string
        public String getMessage()
        {
            
            return message;
            
        }
    
    //From here: Required stuff only
    @Override
    public HandlerList getHandlers() {
        
            return new HandlerList();
            
    }
    
    public static HandlerList getHandlerList() {
        
            return new HandlerList();
            
    }
    
}
