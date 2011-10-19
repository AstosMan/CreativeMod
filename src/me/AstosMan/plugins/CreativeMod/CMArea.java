package me.AstosMan.plugins.CreativeMod;

import java.io.Serializable;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class CMArea implements Serializable{
	//Intended instance variables not sure that if it needs plugin
	//but better safe then sorry.
	private Location a, b;
	private Plugin p;
	private int h1, h2;
	public CMArea(Plugin p, Location a,Location b) {
		//Constructor defaults height to an extremely big area.
		//TODO: Implement Serializable
		this.p=p;
		this.a=a;
		this.b=b;
		h1=0;
		h2=1000;
	}
	public CMArea(Plugin p, Location a, Location b, int h1, int h2) {
		//Constructor requires a height variable.
		//TODO: Implement Serializable
		this.p=p;
		this.a=a;
		this.b=b;
		this.h1=h1;
		this.h2=h2;
	}
}
