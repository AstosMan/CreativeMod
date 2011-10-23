package me.AstosMan.plugins.CreativeMod;

import org.bukkit.GameMode;
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
			if(cma.getWorld() == pme.getPlayer().getWorld())
			{
				if((x < (cma.getX1() > cma.getX2() ? cma.getX1() : cma.getX2()) && x > (cma.getX1() < cma.getX2() ? cma.getX1() : cma.getX2())) && (z < (cma.getZ1() > cma.getZ2() ? cma.getZ1() : cma.getZ2()) && z > (cma.getZ1() < cma.getZ2() ? cma.getZ1() : cma.getZ2())) && (y > cma.getBottom() && y < cma.getTop()))
				{
					pme.getPlayer().setGameMode(GameMode.CREATIVE);
				}
			}
		}
	}
}
