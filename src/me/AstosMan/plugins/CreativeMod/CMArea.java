package me.AstosMan.plugins.CreativeMod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.bukkit.Location;
import org.bukkit.World;

@SuppressWarnings("serial")
public class CMArea implements Serializable {
	private Location l1, l2;

	@SuppressWarnings("unused")
	private CMArea(){}
	
	public CMArea(World w,int x1, int z1,int x2,int z2) {
		//Constructor defaults height to an extremely big area.
		l1 = new Location(w, x1, 0,z1);
		l2 = new Location(w, x2, 1000, z2);
	}

	public CMArea(World w, int x1,int y1, int z1, int x2, int y2, int z2) {
		//Constructor requires a height variable.
		l1 = new Location(w, x1, y1,z1);
		l2 = new Location(w, x2, y2, z2);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		//Object serializer
		out.defaultWriteObject();
		out.writeObject(l1);
		out.writeObject(l2);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		//Object deserializer
		in.defaultReadObject();
	}
	
	public int getBottom() {
		//Returns bottom int
		if (l1.getBlockY() <= l2.getBlockY())
			return (int) l1.getBlockY();
		return (int) l2.getBlockY();
	}
	
	public int getTop() {
		//Returns top int
		if (l1.getBlockY() >= l2.getBlockY())
			return (int) l1.getBlockY();
		return (int) l2.getBlockY();
	}
	
	public int getNorth() {
		//Returns North int
		if (l1.getBlockX() <= l2.getBlockX())
			return (int) l1.getBlockX();
		return (int) l2.getBlockX();
	}
	
	public int getSouth() {
		//Returns South int
		if (l1.getBlockX() >= l2.getBlockX())
			return (int) l1.getBlockX();
		return (int) l2.getBlockX();
	}
	
	public int getEast() {
		//Returns East int
		if (l1.getBlockZ() <= l2.getBlockZ())
			return (int) l1.getBlockZ();
		return (int) l2.getBlockZ();
	}
	
	public int getWest() {
		//Returns West int
		if (l1.getBlockZ() >= l2.getBlockZ())
			return (int) l1.getBlockZ();
		return (int) l2.getBlockZ();
	}
	
	
	public void setBottom(int y) {
		//Sets bottom int
		if (l1.getBlockY() <= l2.getBlockY())
			l1.setY(y);
		else
			l2.setY(y);
	}
	
	public void setTop(int y) {
		//Sets top int
		if (l1.getBlockY() >= l2.getBlockY())
			l1.setY(y);
		else
			l2.setY(y);
	}
	
	public void setNorth(int x) {
		//Sets North int
		if (l1.getBlockX() <= l2.getBlockX())
			 l1.setX(x);
		else
			l2.setX(x);
	}
	
	public void setSouth(int x) {
		//Sets South int
		if (l1.getBlockX() >= l2.getBlockX())
			 l1.setX(x);
		else
			l2.setX(x);
	}
	
	public void setEast(int z) {
		//Sets East int
		if (l1.getBlockZ() <= l2.getBlockZ())
			 l1.setZ(z);
		else
			l2.setZ(z);
	}
	
	public void setWest(int z) {
		//Sets West int
		if (l1.getBlockZ() >= l2.getBlockZ())
			 l1.setZ(z);
		else
			l2.setZ(z);
	}
	
	public boolean Contains(Location l) {
		if (getNorth() <= l.getBlockX() && getSouth() >= l.getBlockX() && getBottom() <= l.getBlockY() && getTop() >= l.getBlockY() && getEast() <= l.getBlockZ() && getWest() >= l.getBlockZ())
			return true;
		return false;
	}
	
}
