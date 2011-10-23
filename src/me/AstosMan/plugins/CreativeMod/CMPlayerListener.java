package me.AstosMan.plugins.CreativeMod;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class CMPlayerListener extends PlayerListener 
{
	private Plugin plugin;
	
	public CMPlayerListener(Plugin p)
	{
		this.plugin = p;
	}
	
	public void onPlayerMove(PlayerMoveEvent pme)
	{
		Location l = pme.getPlayer().getLocation();
		double x = l.getX(), y = l.getY(), z = l.getZ();
		for(CMArea cma : ((CreativeMod)plugin).getAreas())
		{
			if()
			{
				
			}
		}
	}
}
