package me.AstosMan.plugins.CreativeMod;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class CMPlayerListener implements Listener 
{
	private Plugin plugin;
	
	public CMPlayerListener(Plugin p)
	{
		this.plugin = p;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerMove(PlayerMoveEvent pme)
	{
		Player p = pme.getPlayer();
		Location l = p.getLocation();
		if(p.getGameMode() != GameMode.CREATIVE)
		{
			for(CMArea cma : ((CreativeMod)plugin).getAreas())
			{
				if(cma.getWorld().getName().equals(p.getWorld().getName()) && !cma.contains(l) && cma.hasPlayer(p) )
				{
					p.getInventory().clear();
					p.setGameMode(cma.getPlayerPrevState(p));
				}
				else if(cma.getWorld().getName().equals(p.getWorld().getName()) && cma.contains(l))
				{
						p.setGameMode(GameMode.CREATIVE);
						cma.addPlayer(p);
				}
			}
		}
	}
}