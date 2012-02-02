package me.AstosMan.plugins.CreativeMod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
    	
    	pm.registerEvents(new CMPlayerListener(this), this);
    	pm.registerEvents(new CMEntityListener(this), this);
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
					if(args[0].equals("create"))
					{
						if (args.length == 6) {
							for (CMArea a:areas) {
								if (a.getName().equals(args[1])) {
									sender.sendMessage(a.getName() + "is already an area.");
									return false;
								}
							}
							try {
								areas.add(new CMArea(sender.getServer().getPlayer(sender.getName()).getWorld(), args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5])));
							}
							catch (NumberFormatException exp){
								sender.sendMessage("Error create command requires numbers.");
								return false;
							}
							sender.sendMessage(args[1] + " has been created with the points (" + args[2] + ", 0, " + args[3] +"); (" + args[4] + ", 1000, " + args[5] +").");
							return true;
						}
						if (args.length == 8) {
							for (CMArea a:areas) {
								if (a.getName().equals(args[1])) {
									sender.sendMessage(a.getName() + "is already an area.");
									return false;
								}
							}
							try {
								areas.add(new CMArea(sender.getServer().getPlayer(sender.getName()).getWorld(), args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7])));
							}	
							catch (NumberFormatException exp){
								sender.sendMessage("Error create command requires numbers.");
								return false;
							}
							sender.sendMessage(args[1] + " has been created with the points (" + args[2] + ", " + args[3] + ", " + args[4] +"); (" + args[5] + ", " + args[6]+ ", " + args[7] +").");
							return true;
						}
						sender.sendMessage("Oops that was not a name.");
						return false;
					}
					
					else if(args[0].equals("tnt"))
					{
						if (args.length == 2) {
							for (CMArea a:areas) {
								if (a.getName().equals(args[1])) {
									a.setTrinitrotolueneState(!a.getTrinitrotolueneState());
									if (a.getTrinitrotolueneState() == false)
										sender.sendMessage(a.getName() + "'s Tnt state has been succesfully turned off.");
									else
										sender.sendMessage(a.getName() + "'s Tnt state has been succesfully turned on.");
									return true;
								}
							}
							sender.sendMessage("Oops that name is not defined.");
							return false;
						}
						sender.sendMessage("Oops that was not a name.");
						return false;
					}
					else if(args[0].equals("mobs"))
					{
						if (args.length == 2) {
							for (CMArea a:areas) {
								if (a.getName().equals(args[1])) {
									a.setMobEntranceState(!a.getMobEntranceState());
									if (a.getMobEntranceState() == false)
										sender.sendMessage(a.getName() + "'s Mob state has been succesfully turned off");
									else
										sender.sendMessage(a.getName() + "'s Mob state has been succesfully turned on");
									return true;
								}
							}
							sender.sendMessage("Oops that name is not defined.");
							return false;
						}
						sender.sendMessage("Oops that was not a name.");
						return false;
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
						if (args.length == 2) {
							for (CMArea a:areas) {
								if (a.getName().equals(args[1])) {
									a.clearArea();
									sender.sendMessage(a.getName() + " has been cleared.");
									return true;
								}
							}
							sender.sendMessage("Oops that name is not defined");
							return false;
						}
						sender.sendMessage("Oops that was not a name.");
						return false;
					}
					else if(args[0].equals("resize"))
					{
						if (args.length == 4) {
							for (CMArea a:areas) {
								if (a.getName().equals(args[1])) {
									if (args[2].equals("north") || args[2].equals("n")) {
										try {
											a.setNorth(Integer.parseInt(args[3]));
										}	
										catch (NumberFormatException exp){
											sender.sendMessage("Error resize command requires numbers.");
											return false;
										}
										sender.sendMessage(args[1] + "'s " + args[2] + " side has been changed to " + args[3] + ".");
										return true;
									}
									else if (args[2].equals("south") || args[2].equals("s")) {
										try {
											a.setSouth(Integer.parseInt(args[3]));
										}	
										catch (NumberFormatException exp){
											sender.sendMessage("Error resize command requires numbers.");
											return false;
										}
										sender.sendMessage(args[1] + "'s " + args[2] + " side has been changed to " + args[3] + ".");
										return true;
									}
									else if (args[2].equals("east") || args[2].equals("e")) {
										try {
											a.setEast(Integer.parseInt(args[3]));
										}	
										catch (NumberFormatException exp){
											sender.sendMessage("Error resize command requires numbers.");
											return false;
										}
										sender.sendMessage(args[1] + "'s " + args[2] + " side has been changed to " + args[3] + ".");
										return true;
									}
									else if (args[2].equals("west") || args[2].equals("w")) {
										try {
											a.setWest(Integer.parseInt(args[3]));
										}	
										catch (NumberFormatException exp){
											sender.sendMessage("Error resize command requires numbers.");
											return false;
										}
										sender.sendMessage(args[1] + "'s " + args[2] + " side has been changed to " + args[3] + ".");
										return true;
									}
									else if (args[2].equals("top") || args[2].equals("t")) {
										try {
											a.setTop(Integer.parseInt(args[3]));
										}	
										catch (NumberFormatException exp){
											sender.sendMessage("Error resize command requires numbers.");
											return false;
										}
										sender.sendMessage(args[1] + "'s " + args[2] + " side has been changed to " + args[3] + ".");
										return true;
									}
									else if (args[2].equals("bottom") || args[2].equals("b")) {
										try {
											a.setBottom(Integer.parseInt(args[3]));
										}	
										catch (NumberFormatException exp){
											sender.sendMessage("Error resize command requires numbers.");
											return false;
										}
										sender.sendMessage(args[1] + "'s " + args[2] + " side has been changed to " + args[3] + ".");
										return true;
									}
									else {
										sender.sendMessage("resize requires a direction");
										return false;
									}
								}
							}
							sender.sendMessage("Oops that name is not defined.");
							return false;
						}
						sender.sendMessage("Oops that was not a name.");
						return false;
					}
					else if(args[0].equals("rn"))
					{
						if (args.length == 3) {
							for (CMArea a:areas) {
								if (a.getName().equals(args[1])) {
									a.setName(args[2]);
									sender.sendMessage(args[1] + " has been renamedd to " + args[2] + ".");
									return true;
								}
							}
							sender.sendMessage("Oops that name is not defined.");
							return false;
						}
						sender.sendMessage("Oops that was not a name.");
						return false;
					}
					else if(args[0].equals("rights"))
					{
						//if
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
