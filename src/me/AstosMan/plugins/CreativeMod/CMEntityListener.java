package me.AstosMan.plugins.CreativeMod;

import java.util.ArrayList;

import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.Plugin;

public class CMEntityListener extends EntityListener
{
	private Plugin plugin;
	private ArrayList<CMArea> CMAreas;
	
	public CMEntityListener(Plugin p)
	{
		this.plugin = p;
	}
	
	public void addArea(CMArea CMArea) {
		CMAreas.add(CMArea);
	}
	
	public void removeArea(CMArea CMArea) {
		CMAreas.remove(CMArea);
	}
	
	public void onExplosionPrimeEvent(ExplosionPrimeEvent event){
		// If TNT is primed and within a creative area that has set TNT to off this method will cancel the explosion.
		if (event.getEntity() instanceof TNTPrimed) {
			for (int i = 0; i < CMAreas.size(); i++) {
				if (CMAreas.get(i).Contains(event.getEntity().getLocation()) && CMAreas.get(i).getTrinitrotolueneState()) {
					event.setCancelled(true);
					return;
				}
			}
		}
	}
	
}
