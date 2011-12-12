package me.AstosMan.plugins.CreativeMod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;

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
    	pm.registerEvent(Event.Type.CREATURE_SPAWN, new CMEntityListener(this), Event.Priority.Normal, this);
    	pm.registerEvent(Event.Type.ENTITY_EXPLODE, new CMEntityListener(this), Event.Priority.Highest, this);
        log.info(this + " is now enabled!");
    }

    public void onDisable()
    {
        log.info(this + " is now disabled!");
    }
    
    public void saveAreas() throws IOException
    {
    	PrintStream bw = new PrintStream("CreativeMod/areas.cmas");
    	for(CMArea aa : areas)
    	{
    		String ps = "";
    		for(String sss: aa.getPlayers())
    		{
    			ps += sss + " ";
    		}
    		bw.println(aa.getWorld() + " " + aa.getName() + " " + aa.getX1() + " " + aa.getTop() + " " + aa.getZ1() + " " + aa.getX2() + " " + aa.getBottom() + " " + aa.getZ2() + " " + aa.getMobEntranceState() + " " + aa.getTrinitrotolueneState() + " " + ps + "\n");
    	}
    }
    
    public void readAreas() throws IOException
    {
    	BufferedReader br = new BufferedReader(new FileReader("CreativeMod/areas.cmas"));
    	String line = br.readLine();
    	while(line != null)
    	{
    		String[] s = line.split(" ");
    		try
    		{
    			CMArea cc = new CMArea(getServer().getWorld(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]), Integer.parseInt(s[5]), Integer.parseInt(s[6]), Integer.parseInt(s[7]));
    		}catch(NumberFormatException nfe){log.severe("Could not read areas from file.");}
    		line = br.readLine();
    	}
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
				if(args.length > 1)
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
					else if(args[0].equals("delete"))
					{
						if(args.length >= 2)
						{
							for(int j = 0; j < areas.size(); j++)
							{
								for(int i = 1; i < args.length; i++)
								{
									if(areas.get(j).getName().equals(args[i]))
									{
										areas.remove(j);
										break;
									}
								}
							}
						}
						return true;
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
					else if(args[0].equals("list"))
					{
						String s = "";
						for(CMArea aa : areas)
						{
							s += aa.getWorld().getName() + ":" + aa.getName() + " ";
						}
						sender.sendMessage(s);
						return true;
					}
				}
			}
    	}
    	return false;
    }
}
