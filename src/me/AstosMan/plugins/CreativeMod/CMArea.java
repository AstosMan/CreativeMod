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
		if (l1.getY() <= l2.getY())
			return (int) l1.getY();
		else
			return (int) l2.getY();
	}
	
	public int getTop() {
		//Returns top int
		if (l1.getY() >= l2.getY())
			return (int) l1.getY();
		else
			return (int) l2.getY();
	}
	
	public int getNorth() {
		//Returns North int
		if (l1.getX() <= l2.getX())
			return (int) l1.getX();
		else
			return (int) l2.getX();
	}
	
	public int getSouth() {
		//Returns South int
		if (l1.getX() >= l2.getX())
			return (int) l1.getX();
		else
			return (int) l2.getX();
	}
	
	public int getEast() {
		//Returns East int
		if (l1.getZ() <= l2.getZ())
			return (int) l1.getZ();
		else
			return (int) l2.getZ();
	}
	
	public int getWest() {
		//Returns West int
		if (l1.getZ() >= l2.getZ())
			return (int) l1.getZ();
		else
			return (int) l2.getZ();
	}
	
	
	public void setBottom(int y) {
		//Sets bottom int
		l1.setY(y);
	}
	
	public void setTop(int y) {
		//Sets top int
		l2.setY(y);
	}
	
	public void setNorth(int x) {
		//Sets North int
		if (l1.getX() <= l2.getX())
			 l1.setX(x);
		else
			l2.setX(x);
	}
	
	public void setSouth(int x) {
		//Sets South int
		if (l1.getX() >= l2.getX())
			 l1.setX(x);
		else
			l2.setX(x);
	}
	
	public void setEast(int z) {
		//Sets East int
		if (l1.getZ() <= l2.getZ())
			 l1.setZ(z);
		else
			l2.setZ(z);
	}
	
	public void setWest(int z) {
		//Sets West int
		if (l1.getZ() >= l2.getZ())
			 l1.setZ(z);
		else
			l2.setZ(z);
	}
	
}
