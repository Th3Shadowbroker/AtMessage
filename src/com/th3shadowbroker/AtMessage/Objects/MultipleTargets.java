package com.th3shadowbroker.AtMessage.Objects;

public class MultipleTargets {
    
    protected String[] targets;
    
    //Construction
    public MultipleTargets( String targets )
    {
        
        String tmp = targets;
        
        tmp = tmp.replaceFirst( "@" , "" );
        
        this.targets = tmp.split( "," );
        
    }
    
    //Get all targets
    public String[] getTargets()
    {
        
        return targets;
        
    }
    
    //Contains multiple targets
    public static boolean isMultiple( String targetsToCheck )
    {
        if ( targetsToCheck.contains( "," ) )
        {
            
            return true;
            
        } else {
            
            return false;
            
        }
    }
    
}
