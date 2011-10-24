package me.AstosMan.plugins.CreativeMod;

import java.util.ArrayList;

import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Monster;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.CreatureSpawnEvent;
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
		// Adds a CMArea to CMAreas
		CMAreas.add(CMArea);
	}
	
	public void removeArea(CMArea CMArea) {
		// Removes a CMArea from CMAreas
		CMAreas.remove(CMArea);
	}
	
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		// If Mob is spawned and within a creative area that has set Mobs to off this method will cancel the spawn.
		if (event.getEntity() instanceof Monster) {
			for(CMArea area : CMAreas) {
				if (area.Contains(event.getEntity().getLocation()) && area.getMobEntranceState()) {
					event.setCancelled(true);
					return;
				}
			}
		}
		super.onCreatureSpawn(event);
	}
	
	
	
	public void onExplosionPrimeEvent(ExplosionPrimeEvent event){
		// If TNT is primed and within a creative area that has set TNT to off this method will cancel the explosion.
		if (event.getEntity() instanceof TNTPrimed || event.getEntity() instanceof Creeper) {
			for (CMArea area : CMAreas) {
				if (area.Contains(event.getEntity().getLocation()) && area.getTrinitrotolueneState()) {
					event.setCancelled(true);
					return;
				}
			}
		}
		super.onExplosionPrime(event);
	}
}
