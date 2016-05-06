package com.th3shadowbroker.AtMessage.Loaders;

import com.th3shadowbroker.AtMessage.main;
import org.bukkit.configuration.file.FileConfiguration;

public class Prefix {
    
    public main plugin;
    public FileConfiguration config;
    
    //Construction
    public Prefix( main loaderClass )
    {
        
        this.plugin = loaderClass;
        
        this.config = loaderClass.getConfig();
        
        this.plugin.PluginPrefix = getPrefix();
        
        this.plugin.ConsolePrefix = getConsolePrefix();
        
    }
    
    //Get the prefix as String
    private String getPrefix()
    {
        
        String prefix = config.getString("ChatPrefix");
        prefix = prefix.replaceAll("&", "§");
        
        return prefix + " ";
        
    }
    
    //Get the console-prefix as String
    private String getConsolePrefix()
    {
        
        String prefix = config.getString("ConsolePrefix");
        
        return prefix + " ";
        
    }
    
}
