package com.th3shadowbroker.AtMessage.Cache;

import com.th3shadowbroker.AtMessage.Exceptions.AlreadyInCacheException;
import com.th3shadowbroker.AtMessage.Exceptions.NotInCacheException;
import java.util.ArrayList;
import java.util.List;

public class SpectatorCache 
{
   
    private List<AtMessagePlayer> CachedPlayers = new ArrayList<AtMessagePlayer>();
    
    //Construction
    public SpectatorCache()
    {
        
        // Nothing to do here

    }
    
    //Create a new entry
    public void createCacheEntry( AtMessagePlayer p ) throws AlreadyInCacheException
    {
        
        if ( !CachedPlayers.contains( p ) )
        {
            
            CachedPlayers.add( p );
            
        }   else    {
            
            throw new AlreadyInCacheException();
            
        }
        
    }
    
    //Remove an existing entry from cache
    public void removeCacheEntry( AtMessagePlayer p ) throws NotInCacheException
    {

            for( AtMessagePlayer player : CachedPlayers )
            {
                if ( player.getUUID().equals( p.getUUID() ) )
                {
                    CachedPlayers.remove( player );
                    return;
                }
            }
  
            throw new NotInCacheException();
        
    }
    
    //Is player in cache ?
    public boolean cacheEntryExists( AtMessagePlayer p )
    {
        
        return CachedPlayers.contains( p );
        
    }
    
    //Get an specific entry
    public AtMessagePlayer getCacheEntry( AtMessagePlayer p )
    {
        
        for( AtMessagePlayer player : CachedPlayers )
        {
            if ( player.getUUID().equals( p.getUUID() ) )
            {

                return player;
                
            }
        }
        
        return null;
        
    }
    
    //Update cache object
    public void updateCacheEntry( AtMessagePlayer p , PlayerState newState ) throws NotInCacheException
    {

            for( AtMessagePlayer player : CachedPlayers )
            {
                
                if ( player.getUUID().equals( p.getUUID() ) && player.getState() != newState )
                {
                    
                    player.setState( newState );
                    return;
                    
                }
                
            }

            throw new NotInCacheException();
        
    }
    
    //Get the whole cache as array
    public ArrayList<AtMessagePlayer> getCachedPlayersWithState( PlayerState state )
    {
        
        ArrayList<AtMessagePlayer> playersInState = new ArrayList<AtMessagePlayer>();
        
        for ( AtMessagePlayer player : CachedPlayers )
        {
            if ( player.getState() == state )
            {
                playersInState.add( player );
            }
        }
        
        return playersInState;
        
    }
    
}
