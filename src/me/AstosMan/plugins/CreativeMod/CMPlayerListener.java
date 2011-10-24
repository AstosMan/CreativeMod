package me.AstosMan.plugins.CreativeMod;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
		Player p = pme.getPlayer();
		for(CMArea cma : ((CreativeMod)plugin).getAreas())
		{
			if(cma.getWorld() == p.getWorld() && cma.contains(p.getLocation()) && p.getGameMode() != GameMode.CREATIVE)
			{
					ItemStack[] pi = p.getInventory().getContents();
					cma.addInventory(p.getName(), pi);
					for(ItemStack is : pi)
					{
						p.getInventory().remove(is);
					}
					p.setGameMode(GameMode.CREATIVE);
			}
		}
	}
}