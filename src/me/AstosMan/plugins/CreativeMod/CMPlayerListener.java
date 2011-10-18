package me.AstosMan.plugins.CreativeMod;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.Plugin;

public class CMPlayerListener extends PlayerListener 
{
	private Plugin plugin;
	
	public CMPlayerListener(Plugin p)
	{
		this.plugin = p;
	}
}
