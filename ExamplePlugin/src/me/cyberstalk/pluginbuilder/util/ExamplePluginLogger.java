package me.cyberstalk.pluginbuilder.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import me.cyberstalk.pluginbuilder.ExamplePlugin;

public class ExamplePluginLogger {

    public static final Logger log = Logger.getLogger("Minecraft");
    private static ExamplePlugin plugin = ExamplePlugin.getInstance();

    private static String PLUGIN_NAME = plugin.getDescription().getName();

    public static void Info(String message) {
        log.log(Level.INFO, "[" + PLUGIN_NAME + "] " + message);
    }

    public static void Warning(String message) {
        log.log(Level.WARNING, "[" + PLUGIN_NAME + "] " + message);
    }

    public static void Severe(String message) {
        log.log(Level.SEVERE, "[" + PLUGIN_NAME + "] " + message);
    }

    public static void Debug(String message) {
        if(plugin.isDebug()){
            log.log(Level.INFO, "[DEBUG] [" + PLUGIN_NAME + "] " + message);
        }
    }

    public static void Debug(Throwable throwable) {
        if(plugin.isDebug()){
            StackTrace(throwable);
        }
    }
    
    public static void Debug(String message, Throwable throwable) {
        Debug(message);
        Debug(throwable);
    }

    public static void StackTrace(Throwable throwable) {
        log.log(Level.SEVERE, throwable.getMessage(), throwable);
    }

    public static void chat(Player player, String message){
        player.sendMessage(message);
    }

    public static void chat(Player player, String[] lines, ChatColor[] colors){
        String message = ChatColor.DARK_GRAY+"["+ChatColor.GRAY+PLUGIN_NAME+ChatColor.DARK_GRAY+"] ";
        ChatColor color = ChatColor.WHITE;
        int i = 0;
        for(String s : lines){
            if(colors.length>(i-1))
                color = colors[i];
            message += color + s;
            i++;
        }
        chat(player,message);
    }

    public static void sendNotification(SpoutPlayer spoutPlayer, String message) {
        if (spoutPlayer.isSpoutCraftEnabled()) {
            if (message.length() < 25) {
                spoutPlayer.sendNotification(spoutPlayer.getName(), message, Material.LOCKED_CHEST);
            } else {
                spoutPlayer.sendNotification(spoutPlayer.getName(), message.substring(0, 25), Material.LOCKED_CHEST);
            }
        } else {
            spoutPlayer.sendMessage(message);
        }
    }

    public static void sendNotification(SpoutPlayer spoutPlayer, String message, Material icon) {
        if (spoutPlayer.isSpoutCraftEnabled()) {
            if (message.length() < 25) {
                spoutPlayer.sendNotification(spoutPlayer.getName(), message, icon);
            } else {
                spoutPlayer.sendNotification(spoutPlayer.getName(), message.substring(0, 25), icon);
            }
        } else {
            spoutPlayer.sendMessage(message);
        }
    }

}
