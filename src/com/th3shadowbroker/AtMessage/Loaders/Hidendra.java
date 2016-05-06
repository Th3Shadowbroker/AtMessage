package com.th3shadowbroker.AtMessage.Loaders;

import com.th3shadowbroker.AtMessage.main;
import com.th3shadowbroker.tokensystem.metrics.Metrics;
import java.io.IOException;

public class Hidendra {

    public Hidendra( main plugin )
    {
        
        runMetrics( plugin );
        
    }
    
    private void runMetrics( main plugin )
    {
         try {
            Metrics metrics = new Metrics( plugin );
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }
    }
    
}
