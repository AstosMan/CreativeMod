package me.AstosMan.plugins.CreativeMod;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Monster;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.Plugin;

public class CMEntityListener implements Listener
{
	private Plugin plugin;
	
	public CMEntityListener(Plugin p)
	{
		this.plugin = p;
	}
	
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		// If Mob is spawned and within a creative area that has set Mobs to off this method will cancel the spawn.
		if (event.getEntity() instanceof Monster) {
			for(CMArea cma : ((CreativeMod)plugin).getAreas()) {
				if (cma.contains(event.getEntity().getLocation()) && cma.getMobEntranceState() && cma.getWorld() == event.getEntity().getWorld()) {
					event.setCancelled(true);
					return;
				}
			}
		}
		super.onCreatureSpawn(event);
	}
	
	@Override
	public void onEntityExplode(EntityExplodeEvent event) {
		// If Creeper explodes and within a creative area that has set TNT to off this method will cancel the explosion.
		if (event.getEntity() instanceof Creeper) {
			for (CMArea cma : ((CreativeMod)plugin).getAreas()) {
				if (cma.contains(event.getEntity().getLocation()) && cma.getTrinitrotolueneState() && cma.getWorld() == event.getEntity().getWorld()) {
					event.setCancelled(true);
					return;
				}
			}
		}
		super.onEntityExplode(event);
	}
	
	public void onExplosionPrimeEvent(ExplosionPrimeEvent event){
		// If TNT is primed and within a creative area that has set TNT to off this method will cancel the explosion.
		if (event.getEntity() instanceof TNTPrimed) {
			for (CMArea cma : ((CreativeMod)plugin).getAreas()) {
				if (cma.contains(event.getEntity().getLocation()) && cma.getTrinitrotolueneState() && cma.getWorld() == event.getEntity().getWorld()) {
					event.setCancelled(true);
					return;
				}
			}
		}
		super.onExplosionPrime(event);
	}
}
