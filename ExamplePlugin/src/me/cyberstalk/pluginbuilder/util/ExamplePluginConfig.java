package me.cyberstalk.pluginbuilder.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.cyberstalk.pluginbuilder.ExamplePlugin;

public class ExamplePluginConfig {

    public ExamplePluginConfig(){
        ExamplePlugin.getInstance().getConfig().options().header(getHeader());
        ExamplePlugin.getInstance().getConfig().options().copyDefaults(true);
        ExamplePlugin.getInstance().saveConfig();
        reload();
    }

    public int getIdentifierItemId(){
        return ExamplePlugin.getInstance().getConfig().getInt("IdentifierItemId",1);
    }

    public void reload() {
        ExamplePlugin.getInstance().reloadConfig();
    }

    private String getHeader(){
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, YYYY, HH:mm aa"); 
        // http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
        date = new Date(System.currentTimeMillis());
        String header = "Config for " + ExamplePlugin.getPluginName() + " version " + ExamplePlugin.getPluginVersion() + "\n"
                + "Generated on " + dateFormat.format(date) + "\n"
                + "IdentifierItemId - [integer] - Right clicking things while holding this item will identify them\n"
                + "----------------------------\n";
        return header;
    }

}
