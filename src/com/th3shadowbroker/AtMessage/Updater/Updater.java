package com.th3shadowbroker.AtMessage.Updater;

import com.th3shadowbroker.AtMessage.main;

public class Updater {
    
    protected main plugin;
    protected String versionSource;
    
    //Construction
    public Updater( main plugin , String versionSource )
    {
        
        this.plugin = plugin;
        this.versionSource = versionSource;
        
    }
    
}
