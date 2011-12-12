package me.AstosMan.plugins.CreativeMod;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;


public class CMArea{
	private Location l1, l2, l3, l4;
	private String name;
	private int y1, y2;
	private boolean trinitrotolueneIsOff, MobEntranceIsOff;
	//private HashMap<String, ItemStack[]> inventories; 
	HashMap<String, GameMode> modeMap;

	public CMArea(final World w, String s, int x1, int z1, int x2, int z2) {
		// Constructor defaults height to an extremely big area.
		this(w, s, x1, 1000, z1, x2, 0, z2);
		//inventories = new HashMap<String, ItemStack[]>();
		//players = new ArrayList<String>();
		modeMap = new HashMap<String, GameMode>();
	}

	public CMArea(final World w, String s, int x1, int y1, int z1, int x2,
			int y2, int z2) {
		// Constructor requires a height variable.
		//inventories = new HashMap<String, ItemStack[]>();
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
		l3 = new Location(w, x1 + 10, y1 + 10, z1 + 10);
		l4 = new Location(w, x2 - 10, y2 - 10, z2 - 10);
		name = s;
		this.y1 = y1;
		this.y2 = y2;
		trinitrotolueneIsOff = true;
		MobEntranceIsOff = true;
		//players = new ArrayList<String>();
		modeMap = new HashMap<String, GameMode>();
	}

	public Set<String> getPlayers()
	{
		return modeMap.keySet();
	}
	
	public GameMode getPlayerPrevState(Player p)
	{
		Set<String> players = modeMap.keySet();
		for(String k : players)
		{
			if(k.equals(p.getName()))
			{
				return modeMap.get(p.getName());
			}
		}
		return GameMode.SURVIVAL;
	}
	
	public boolean hasPlayer(Player p)
	{
		for(String pl : modeMap.keySet())
		{
			if(p.getName().equals(pl))
			{
				return true;
			}
		}
		return false;
	}
	
	public void addPlayer(Player p)
	{
		this.modeMap.put(p.getName(), p.getGameMode());
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


	public boolean contains(Location l) {
		// Returns true if coordinates are in this area
		if (l2.getBlockX() <= l.getBlockX() && l1.getBlockX() >= l.getBlockX() && l2.getBlockY() <= l.getBlockY() && l1.getBlockY() >= l.getBlockY()
				&& l2.getBlockZ() <= l.getBlockZ() && l1.getBlockZ() >= l.getBlockZ() && l.getWorld().equals(l1.getWorld()))
			return true;
		return false;
	}
	
	public boolean isCloseTo(Location l) {
		// Returns true if coordinates are within 10 blocks of the area and in the same world
		if (l4.getBlockX() <= l.getBlockX() && l3.getBlockX() >= l.getBlockX() && l4.getBlockY() <= l.getBlockY() && l3.getBlockY() >= l.getBlockY()
				&& l4.getBlockZ() <= l.getBlockZ() && l3.getBlockZ() >= l.getBlockZ() && l.getWorld().equals(l1.getWorld()))
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
	
	public void setTrinitrotolueneState(boolean isOff) {
		// Sets the state of trinitrotolueneIsOff to input
		trinitrotolueneIsOff = isOff;
	}
	
	public void clearArea() {
		// Changes all blocks in the area to air
		for (int i = l2.getBlockX(); i <= l1.getBlockX(); i++) {
			for (int j = l2.getBlockY(); i <= l1.getBlockY();  i++) {
				for (int k = l2.getBlockZ(); i <= l1.getBlockZ(); i++) {
					if (l1.getWorld().getBlockAt(i, j, k).getType() == Material.BEDROCK){}
					else {
						l1.getWorld().getBlockAt(i, j, k).setType(Material.AIR);
					}
				}
			}
		}
	}

	public World getWorld() {
		// TODO Auto-generated method stub
		return l1.getWorld();
	}
}