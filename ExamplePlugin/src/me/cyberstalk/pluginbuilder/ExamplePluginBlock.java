package me.cyberstalk.pluginbuilder;

import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.block.GenericCubeCustomBlock;

public class ExamplePluginBlock extends GenericCubeCustomBlock {

    public ExamplePluginBlock(){
        super(ExamplePlugin.getInstance(), "Swirly", "http://pluginbuilder.cyberstalk.me/assets/plugin/swirl.png", 32);
        this.setFriction(MaterialData.stone.getFriction());
        this.setHardness(MaterialData.obsidian.getHardness());
        this.setLightLevel(MaterialData.glowstoneBlock.getLightLevel());
    }

}
