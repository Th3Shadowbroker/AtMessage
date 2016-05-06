package com.th3shadowbroker.AtMessage.Loaders;

import com.th3shadowbroker.AtMessage.main;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    
    public main plugin;
    protected FileConfiguration config;
    
    //Construction
    public Config ( main plugin )
    {
        
        this.plugin = plugin;
        this.config = plugin.getConfig();
        
        loadDefaults();
        
    }
    
    //Load defaults config
    private void loadDefaults()
    {
        
        config.addDefault("ChatPrefix", "&e[@Message]");
        config.addDefault("ConsolePrefix", "[@Message]");
        
        config.addDefault("MessageColor","&5");
        config.addDefault("MessageSeparator","►");
        
        config.addDefault("MessageToSender", "&5[You SEPARATOR @TARGET]");
        config.addDefault("MessageToTarget", "&5[@TARGET SEPARATOR You]");
        
        config.options().copyDefaults(true);
        
        plugin.saveConfig();
        
    }
    
}
