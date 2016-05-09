package com.th3shadowbroker.AtMessage.Updater;

import com.th3shadowbroker.AtMessage.main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.bukkit.entity.Player;

public class GitHubUpdater {
    
    protected main plugin;
    protected String versionSource;
    
    protected String onlineVersion;
    protected String localVersion;
    
    //Construction
    public GitHubUpdater( main plugin , String gitVersionFile )
    {
        
        this.plugin = plugin;
        this.versionSource = gitVersionFile;
        
        this.localVersion = plugin.getDescription().getVersion();

    }
    
    //Check for update
    public boolean updateAvailable()
    {
        try {
        
            URL sourceURL = new URL( this.versionSource );
            InputStreamReader stream = new InputStreamReader( sourceURL.openConnection().getInputStream() );
            BufferedReader br = new BufferedReader( stream );
            this.onlineVersion = br.readLine();
            
            if ( !localVersion.equals( this.onlineVersion ) )
            {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch ( Exception ex ) { 
            
            ex.printStackTrace(); 
            return false; 
        
        }
    }
 
    //Send update-notification to console
    public void sendNotification()
    {
        
        if ( updateAvailable() == true )
        {
            
            //Send TRUE notification
            System.out.println( plugin.ConsolePrefix + "A new version of @Message is available..." );
            System.out.println( plugin.ConsolePrefix + "Check the plugin site on http://bit.ly/AtMessage" );
            
        } else {
            
            //Send FALSE notification
            System.out.println( plugin.ConsolePrefix + "No updates available..." );
            
        }
        
    }
    
    //Send update-notification to player
    public void sendInGameNotification( Player p )
    {
        if ( p.isOp() | p.hasPermission( "AtMsg.admin" ) )
        {
            
            p.sendMessage( plugin.PluginPrefix + "§2A new version of §9@Message§2 is available." );
            
            p.sendMessage( plugin.PluginPrefix + "§2Check the plugin site on §9http://bit.ly/AtMessage" );
            
        }
    }
    
    //Updates enabled ?
    public boolean updateNotificationEnabled()
    {
        if ( plugin.config.getBoolean("CheckForUpdates") == true )
        {
            
            return true;
            
        } else {
            
            return false;
            
        }
    }

    
}
