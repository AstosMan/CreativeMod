package me.AstosMan.plugins.CreativeMod;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

@SuppressWarnings("serial")
public class CMArea implements Serializable {
	private Location l1, l2;
	private String name;
	private int y1, y2;
	private boolean trinitrotolueneIsOff, MobEntranceIsOff;

	public CMArea(final World w, String s, int x1, int z1, int x2, int z2) {
		// Constructor defaults height to an extremely big area.
		this(w, s, x1, 1000, z1, x2, 0, z2);
	}

	public CMArea(final World w, String s, int x1, int y1, int z1, int x2,
			int y2, int z2) {
		// Constructor requires a height variable.
		if (x1 >= x2) {
			if (y1 >= y2) {
				if (z1 >= z2) {
				} else {
					int z = z2;
					z2 = z1;
					z1 = z;
				}
			} else {
				int y = y2;
				y2 = y1;
				y1 = y;
			}
		} else {
			int x = x2;
			x2 = x1;
			x1 = x;
		}
		l1 = new Location(w, x1, y1, z1);
		l2 = new Location(w, x2, y2, z2);
		name = s;
		this.y1 = y1;
		this.y2 = y2;
		trinitrotolueneIsOff = true;
		MobEntranceIsOff = true;
	}

	public void writeObject() throws IOException {
		// Object serializer
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name + ".dat"));
		out.defaultWriteObject();
		out.writeObject(l1);
		out.writeObject(l2);
		out.close();
	}

	public void readObject(String name) throws IOException,
			ClassNotFoundException {
		// Object deserializer
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(name + ".dat"));
		in.defaultReadObject();
		l1 = (Location)in.readObject();
		l2 = (Location)in.readObject();
		in.close();
	}

	public int getBottom() {
		// Returns bottom int
		return y2;
	}

	public int getTop() {
		// Returns top int
		return y1;
	}

	public boolean setBottom(int y) {
		// Sets bottom int returns true if possible
		if (y <= y1) {
			l2.setY(y);
			y2 = y;
			return true;
		}
		return false;
	}
	
	public int getX2() {
		// Gets Northern int
		return l2.getBlockX();
	}
	
	public int getX1() {
		// Gets Southern int
		return l1.getBlockX();
	}
	
	public int getZ2() {
		// Gets Western int
		return l2.getBlockZ();
	}
	
	public int getZ1() {
		// Gets Eastern int
		return l1.getBlockZ();
	}

	public boolean setTop(int y) {
		// Sets top int returns true if possible
		if (y >= y2) {
			l1.setY(y);
			y1 = y;
			return true;
		}
		return false;
	}

	public boolean setNorth(int x) {
		// Sets North int returns true if possible
		if (x <= l1.getBlockX()) {
			l2.setX(x);
			return true;
		}
		return false;
	}

	public boolean setSouth(int x) {
		// Sets South int returns true if possible
		if (x >= l2.getBlockX()) {
			l1.setX(x);
			return true;
		}
		return false;
	}

	public boolean setEast(int z) {
		// Sets East int returns true if possible
		if (z <= l1.getBlockZ()) {
			l2.setZ(z);
			return true;
		}
		return false;
	}

	public boolean setWest(int z) {
		// Sets West int returns true if possible
		if (z >= l2.getBlockZ()) {
			l1.setZ(z);
			return true;
		}
		return false;
	}

	public boolean Contains(Location l) {
		// Returns true if coordinates are in this area
		if (l2.getBlockX() <= l.getBlockX() && l1.getBlockX() >= l.getBlockX() && l2.getBlockY() <= l.getBlockY() && l1.getBlockY() >= l.getBlockY()
				&& l2.getBlockZ() <= l.getBlockZ() && l1.getBlockZ() >= l.getBlockZ())
			return true;
		return false;
	}
	
	public String getName() {
		// Returns name
		return name;
	}
	
	public void setName(String s) {
		// Sets name
		name = s;
	}
	
	public boolean getTrinitrotolueneState() {
		// Returns the state of trinitrotolueneIsOff to input
		return trinitrotolueneIsOff;
	}
	
	public void setMobEntranceState(boolean isOff) {
		// Sets the state of MobEntranceIsOff to input
		MobEntranceIsOff = isOff;
	}
	
	public boolean getMobEntranceState() {
		// Returns the state of MobEntranceIsOff
		return MobEntranceIsOff;
	}
	
	public void setTrinitrotolueneAsOff(boolean isOff) {
		// Sets the state of trinitrotolueneIsOff to input
		trinitrotolueneIsOff = isOff;
	}
	
	public void clearArea() {
		// Changes all blocks in the area to air
		for (int i = l2.getBlockX(); i <= l1.getBlockX(); i++) {
			for (int j = l2.getBlockY(); i <= l1.getBlockY();  i++) {
				for (int k = l2.getBlockZ(); i <= l1.getBlockZ(); i++) {
					l1.getWorld().getBlockAt(i, j, k).setType(Material.AIR);
				}
			}
		}
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return l1.getWorld();
	}
}