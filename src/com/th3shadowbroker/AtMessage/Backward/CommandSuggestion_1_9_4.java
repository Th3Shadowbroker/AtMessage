package com.th3shadowbroker.AtMessage.Backward;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CommandSuggestion_1_9_4 {
    
    protected String message = "";
    protected String action = "";
    protected String m = "\"";
    
    //Construction
    public CommandSuggestion_1_9_4(String message, String command)
    {
        
        this.message = "{\"text\":\"%MSG% \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"%CMD%\"}}";
        this.message = this.message.replaceAll("%MSG%", message.replaceAll("&", "§"));
        this.message = this.message.replaceAll("%CMD%", command);
        
    }
    
    //Send as a package
    public void sendToPlayer(Player p)
    {
        
        try {
            
                IChatBaseComponent comp = ChatSerializer.a(message);
                
                PacketPlayOutChat packet = new PacketPlayOutChat(comp);
                
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
                
            
        } catch ( Exception ex ) {
            
            ex.printStackTrace();
            
        }
        
    }
}
