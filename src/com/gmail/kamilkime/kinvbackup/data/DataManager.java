package com.gmail.kamilkime.kinvbackup.data;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.kamilkime.kinvbackup.Main;

public abstract class DataManager {
	
	private static File mainDir = Main.getInst().getDataFolder();
	private static File users = new File(mainDir, "users");
	
	public File getPlayerFile(UUID uuid){
		for(File f : users.listFiles()){
			String toCheck = f.getName().split("@")[0];
			if(toCheck.equalsIgnoreCase(uuid.toString())) return f;
		}
		return null;
	}
	
	public File getPlayerFile(String name){
		for(File f : users.listFiles()){
			String toCheck = f.getName().split("@")[1].replace(".yml", "");
			if(toCheck.equalsIgnoreCase(name.toLowerCase())) return f;
		}
		return null;
	}
	
	public File getPlayerFile(Player p){
		File f = new File(users, p.getUniqueId().toString() + "@" + p.getName().toLowerCase() + ".yml");
		if(!f.exists())	return null;
		return f;
	}
	
	public File getOrCreatePlayerFile(Player p){
		File f = new File(users, p.getUniqueId().toString() + "@" + p.getName().toLowerCase() + ".yml");
		try {
			if(!f.exists()) f.createNewFile();
		} catch(IOException e){
			e.printStackTrace();
		}
		return f;
	}
	
	public abstract void createBackup(Player p, String worldName);
	public abstract Map<Integer, ItemStack> getBackupedItems(File file, String worldName);
	public abstract long loadBackup(Player p, String worldName);
	public abstract void convert(File file);
	public abstract boolean needsConversion(File file);
}