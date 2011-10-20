package me.AstosMan.plugins.CreativeMod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.bukkit.Location;

public class CMArea implements Serializable {
	//Intended instance variables not sure that if it needs plugin
	//but better safe then sorry.
	private static final long serialVersionUID = 1L;
	private Location l1, l2;
	private int h0, h1;

	public CMArea() {
		//Blank Constructor
	}

	public CMArea(Location l1, Location l2) {
		//Constructor defaults height to an extremely big area.
		this.l1 = l1;
		this.l2 = l2;
		h0 = 0;
		h1 = 1000;
	}

	public CMArea(Location l1, Location l2, int h0, int h1) {
		//Constructor requires a height variable.
		this.l1 = l1;
		this.l2 = l2;
		this.h0 = h0;
		this.h1 = h1;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		//Object serializer
		out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		//Object deserializer
		in.defaultReadObject();
	}

	public Location GetLoc1() {
		//Returns first corner
		return l1;
	}

	public Location GetLoc2() {
		//Returns second corner
		return l2;
	}
	
	public int bottom() {
		//Returns bottom level
		return h0;
	}
	
	public int top() {
		//Returns top level
		return h1;
	}
	
	public void setBottom(int h0) {
		//Sets Bottom level
		this.h0=h0;
	}
	
	public void setTop(int h1) {
		//Sets top level
		this.h1=h1;
	}
	
	public void setLoc1(Location l1) {
		//Sets first corner
		this.l1=l1;
	}
	
	public void setLoc2(Location l2) {
		//Sets second corner
		this.l2=l2;
	}
}
