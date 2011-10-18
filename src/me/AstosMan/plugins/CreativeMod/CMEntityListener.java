package me.AstosMan.plugins.CreativeMod;

import org.bukkit.event.entity.EntityListener;
import org.bukkit.plugin.Plugin;

public class CMEntityListener extends EntityListener
{
	private Plugin plugin;
	
	public CMEntityListener(Plugin p)
	{
		this.plugin = p;
	}
}
