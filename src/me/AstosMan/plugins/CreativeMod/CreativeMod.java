package me.AstosMan.plugins.CreativeMod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
