package com.th3shadowbroker.AtMessage;

import com.th3shadowbroker.AtMessage.Cache.AtMessagePlayer;
import com.th3shadowbroker.AtMessage.Cache.SpectatorCache;
import com.th3shadowbroker.AtMessage.Loaders.Commands;
import com.th3shadowbroker.AtMessage.Loaders.Config;
import com.th3shadowbroker.AtMessage.Loaders.Events;
import com.th3shadowbroker.AtMessage.Loaders.Hidendra;
import com.th3shadowbroker.AtMessage.Loaders.Prefix;
import com.th3shadowbroker.AtMessage.Updater.GitHubUpdater;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
    
    public String PluginPrefix;
    public String ConsolePrefix;
    
    private static main instance;

    private SpectatorCache spectatorCache;
    
    public FileConfiguration config;
    
    public GitHubUpdater git;
    
    
    //Load it up !
    @Override
    public void onEnable()
    {
        
        instance = this;
        spectatorCache = new SpectatorCache();
        
//        try {
//            
//            OfflinePlayer testPlayer = Bukkit.getOfflinePlayer("Mataiori");
//            spectatorCache.createCacheEntry( new AtMessagePlayer( testPlayer.getPlayer() ) );
//            
//        } catch ( Exception ex ) {
//            
//            ex.printStackTrace();
//            
//        }
        
        System.out.println( "[@Message] " + "Loading/Creating config file..." );
        loadConfig(); //Load/Create config-file
        
        System.out.println( "[@Message] " + "Loading prefixes..." );
        loadLoaders(); //Load loader classes
        
        System.out.println( ConsolePrefix + "Loading updater..." );
        loadUpdater();
        
        System.out.println( ConsolePrefix + "Loading events..." );
        loadEvents(); //Load event loaders
        
        System.out.println( ConsolePrefix + "Loading commands..." );
        loadCommands(); //Load command loaders
        
        System.out.println( ConsolePrefix + "Loading metrics..." );
        loadMetrics(); //Load command loaders
        
        System.out.println( ConsolePrefix + "@Message is ready..." );
        
    }
    
    //Shut it down
    @Override
    public void onDisable()
    {
        
        saveConfig(); //Save current config
        System.out.println( ConsolePrefix + "Settings saved and plugin disabled..." );
        
    }
    
    //Load/Create the configuration file
    private void loadConfig()
    {
        Config configLoader = new Config( this );
    }
    
    //Load all loader-classes
    private void loadLoaders()
    {
        Prefix prefixLoader = new Prefix( this );
    }
    
    //Load all event-classes
    private void loadEvents()
    {
        Events eventLoader = new Events( this );
    }
    
    //Load all command-classes
    private void loadCommands()
    {
        Commands commandLoader = new Commands( this );  
    }
    
    //Load metrics
    private void loadMetrics()
    {
        Hidendra metricsLoader = new Hidendra( this );
    }
    
    //Load updater
    private void loadUpdater()
    {
        
        this.git = new GitHubUpdater( this , "https://raw.githubusercontent.com/Th3Shadowbroker/AtMessage/master/CurrentVersion.txt" );

        if ( git.updateNotificationEnabled() )
        {
            git.sendNotification();
        }

    }
    
    //Get current instance
    public static main getInstance()
    {
        
        return instance;
        
    }
    
    //Get the current instances spectator cache
    public SpectatorCache getSpecCache()
    {
        
        return spectatorCache;
        
    }
    
}
