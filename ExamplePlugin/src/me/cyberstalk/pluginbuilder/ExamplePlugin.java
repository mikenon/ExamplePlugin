package me.cyberstalk.pluginbuilder;

import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;
import me.cyberstalk.pluginbuilder.util.ExamplePluginLogger;
import me.cyberstalk.pluginbuilder.util.ExamplePluginConfig;
import org.getspout.spoutapi.SpoutServer;

public class ExamplePlugin extends JavaPlugin{

    private static ExamplePlugin instance;
    private boolean printDebugMessages = true;
    private static ExamplePluginConfig conf;
    private static SpoutServer spoutserver;
    private static ExamplePluginBlock myBlock;

    public void onEnable(){
        instance = this;
        conf = new ExamplePluginConfig();
        myBlock = new ExamplePluginBlock();
        spoutserver = new SpoutServer();
        getServer().getPluginManager().registerEvents(new ExamplePluginPlayerListener(), this);

        ExamplePluginLogger.Info("v" + getPluginVersion() + " by " + formatAuthors(getDescription().getAuthors()) + " enabled.");
    }

    public void onDisable(){
        ExamplePluginLogger.Info("Disabled");
    }

    public static ExamplePlugin getInstance(){
        return instance;
    }

    public boolean isDebug(){
        return printDebugMessages;
    }

    private String formatAuthors(List<String> authors){
        switch(authors.size()){
        case 0: return "unknown";
        case 1: return authors.get(0);
        case 2: return authors.get(0) + " and " + authors.get(1);
        default:
            String toReturn = "(";
            for(int i=0; i<authors.size(); i++){
                if(i==authors.size()-1){
                    toReturn = toReturn.substring(0,toReturn.lastIndexOf(','));
                    toReturn += " and "+authors.get(i);
                } else {
                    toReturn += authors.get(i)+", ";
                }
            }
            toReturn += ")";
            return toReturn;
        }
    }

    public static ExamplePluginConfig getConf(){
        return conf;
    }

    public static ExamplePluginBlock getExamplePluginBlock(){
        return myBlock;
    }

    public static SpoutServer getSpoutServer(){
        return spoutserver;
    }

    public static String getPluginName(){
        return getInstance().getDescription().getName();
    }

    public static String getPluginVersion(){
        return getInstance().getDescription().getVersion();
    }
}
