package me.AstosMan.plugins.CreativeMod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.*;

public class CreativeMod extends JavaPlugin
{
	protected Logger log = Logger.getLogger("Minecraft");
	protected ArrayList<CMArea> areas;
	
    public void onEnable()
    { 	    	
    	PluginManager pm = this.getServer().getPluginManager();
    	areas = new ArrayList<CMArea>();
    	
    	pm.registerEvent(Event.Type.PLAYER_MOVE, new CMPlayerListener(this), Event.Priority.Lowest, this);
    	pm.registerEvent(Event.Type.EXPLOSION_PRIME, new CMEntityListener(this), Event.Priority.Highest, this);
    	pm.registerEvent(Event.Type.CREATURE_SPAWN, new CMEntityListener(this), Event.Priority.Highest, this);
    	pm.registerEvent(Event.Type.ENTITY_EXPLODE, new CMEntityListener(this), Event.Priority.Highest, this);
        log.info(this + " is now enabled!");
    }

    public void onDisable()
    {
        log.info(this + " is now disabled!");
    }
    
    public ArrayList<CMArea> getAreas()
    {
    	return areas;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if(cmd.getName().equalsIgnoreCase("cm"))
    	{
			if(sender.hasPermission("CreativeMod.cm"))
			{
				if(args.length > 2)
				{
					if(args[0].equals("make"))
					{
						//TODO: construct a new volume
					}
					else if(args[0].equals("tnt"))
					{
						//TODO: toggle tnt on specified volume
					}
					else if(args[0].equals("mobs"))
					{
						//TODO:toggle mobs on specified volume
					}
					else if(args[0].equals("alert"))
					{
						//TODO: toggle whether user is alerted to volume before entering
					}
					else if(args[0].equals("delete"))
					{
						//TODO: delete specified volume
					}
					else if(args[0].equals("clear"))
					{
						//TODO: Replaces specified volume with air
					}
					else if(args[0].equals("resize"))
					{
						//TODO: Resizes specified volume to specifications
					}
					else if(args[0].equals("rn"))
					{
						//TODO Renames specified volume
					}
					else if(args[0].equals("rights"))
					{
						//TODO: alters who has permission to use the commands 
					}
				}
			}
    	}
    	return false;
    }
}
