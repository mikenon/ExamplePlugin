package me.cyberstalk.pluginbuilder;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.getspout.spoutapi.block.SpoutBlock; 
import org.getspout.spoutapi.player.EntitySkinType;
import org.getspout.spoutapi.player.SpoutPlayer;

import me.cyberstalk.pluginbuilder.util.ExamplePluginLogger;

public class ExamplePluginPlayerListener implements Listener{
    
    public ExamplePluginPlayerListener(){ }
    
    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event){
        if(event.getNewGameMode().equals(GameMode.CREATIVE)){
            event.getPlayer().chat("I switched to Creative mode!");
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){

        if(!isPlayerHoldingRightItem(event.getPlayer()))
            return;
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            String blockname = "";
            SpoutBlock spoutblock = (SpoutBlock)event.getClickedBlock();
            if(spoutblock.isCustomBlock()){
                blockname = spoutblock.getCustomBlock().getName();
            } else {
                blockname = spoutblock.getName();
            }
            ExamplePluginLogger.chat(event.getPlayer(), 
                    new String[] {"That is ", blockname}, 
                    new ChatColor[] {ChatColor.GRAY, ChatColor.DARK_GREEN});
        } else if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
            Block target = event.getPlayer().getTargetBlock(null, 100);
            int distance = (int) target.getLocation().distance(event.getPlayer().getLocation());
            ExamplePluginLogger.chat(event.getPlayer(), 
                    new String[] {"That is ", String.valueOf(distance), " blocks away"}, 
                    new ChatColor[] {ChatColor.GRAY, ChatColor.DARK_GREEN});
        }
    }
    
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        if(!isPlayerHoldingRightItem(event.getPlayer()))
            return;
        if(event.getRightClicked().getType().equals(EntityType.PLAYER)){
            String playername = ((Player)event.getRightClicked()).getName();
            ExamplePluginLogger.chat(event.getPlayer(), 
                    new String[] {"That is ", playername}, 
                    new ChatColor[] {ChatColor.GRAY, ChatColor.DARK_AQUA});
        } else {
            if(event.getRightClicked().getType().equals(EntityType.ZOMBIE)){
                LivingEntity zombie = (LivingEntity) event.getRightClicked();
                ExamplePlugin.getSpoutServer().setEntitySkin(zombie, 
                        "http://pluginbuilder.cyberstalk.me/assets/plugin/zombie.png", 
                        EntitySkinType.DEFAULT);
                ExamplePluginLogger.sendNotification((SpoutPlayer) event.getPlayer(), "You skinned a zombie!", Material.BLAZE_ROD);
            } else {
                String entityname = event.getRightClicked().getType().name().toLowerCase().replace('_', ' ');
                ExamplePluginLogger.chat(event.getPlayer(), 
                        new String[] {"That is a ", entityname}, 
                        new ChatColor[] {ChatColor.GRAY, ChatColor.DARK_BLUE});
            }
        }
    }

    
    private boolean isPlayerHoldingRightItem(Player player){
        if(player.getItemInHand().getTypeId() == ExamplePlugin.getConf().getIdentifierItemId()){
            return true;
        } else {
            return false;
        }
    }
}
