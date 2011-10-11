package me.AstosMan.plugins.CreativeMod;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.*;

public class CreativeMod extends JavaPlugin
{
	protected Logger log = Logger.getLogger("Minecraft");
	
    public void onEnable()
    { 	    	
        log.info(this + " is now enabled!");
    }

    public void onDisable()
    {
        log.info(this + " is now disabled!");
    }
}
